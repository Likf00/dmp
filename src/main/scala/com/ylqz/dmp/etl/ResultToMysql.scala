package com.ylqz.dmp.etl

import com.typesafe.config.ConfigFactory
import com.ylqz.dmp.tools.Save
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.{DataFrame, SparkSession}

/**
  * @ClassName ResultToMysql.scala
  * @author yimting
  * @version 1.0.0
  * @Description 指标计算
  * @createTime 2019年04月23日 16:43:00
  */
object ResultToMysql {

  val load = ConfigFactory.load()

  def main(args: Array[String]): Unit = {
    if(args.length != 2){
      println("请检查输入的参数！")
      sys.exit(0)
    }

    //设置 两个参数
    val Array(inputPath,outputPath) = args
    //设置 SparkSession
    val sparkSession = SparkSession.builder()
      .appName(this.getClass.getName)
      .master("local[*]")
      .config("spark.serializer","org.apache.spark.serializer.KryoSerializer")
      .getOrCreate()

    //转成DF类型，进行处理
    val readDF: DataFrame = sparkSession.read.parquet(inputPath)


    //调用指标一
    indicator(sparkSession,outputPath,readDF)
    //调用报表一
    report(sparkSession,readDF)


  }

  /**
    *  指标一
    * @param ssc  SparkSession
    * @param outputPath  输出 JSON 指定路径
    * @param dataFrame  所需要处理的 DF
    */
  def indicator(ssc:SparkSession,outputPath:String,dataFrame: DataFrame): Unit ={
  //创建视图， 方面后面 引用
  dataFrame.createOrReplaceTempView("logInfo")
  // 查询出结果
  val result = ssc.sql("select count(1),provincename,cityname from logInfo group by provincename,cityname")
  //根据 省份分区 写出为 Json 文件
  result.write.partitionBy("provincename").json(outputPath)
  //保存到 Mysql 中
  Save.saveToMysql(dataFrame,"dmp",load.getString("jdbc.tablename"))

}


  /**
    *  报表计算 （1）
    * @param ssc    SparkSession
    * @param dataFrame  所需要处理的DF
    */
  def report (ssc:SparkSession,dataFrame: DataFrame): Unit ={
    dataFrame.createOrReplaceTempView("logInfo")

    val res = ssc.sql(
      """
        select
        provincename,
        cityname,
        sum(case when requestmode=1 and processnode >= 1 then 1 else 0 end) `请求量`,
        sum(case when requestmode=1 and processnode >= 2 then 1 else 0 end) `有效请求`,
        sum(case when requestmode=1 and processnode=3 then 1 else 0 end)  `广告请求`,
        sum(case when iseffective=1 and isbilling=1 and isbid=1 and adorderid != 0 then 1 else 0 end)  `竞价次数`,
        sum(case when iseffective=1 and isbilling=1 and iswin=1 then 1 else 0 end)  `成功次数`,
        sum(case when requestmode=2 and iseffective=1 then 1 else 0 end)  `展示数量`,
        sum(case when requestmode=3 and iseffective=1 then 1 else 0 end)  `点击数量`,
        sum(case when iseffective=1 and isbilling=1 and iswin=1 then 1.0*adpayment/1000 else 0 end)  `消费`,
        sum(case when iseffective=1 and isbilling=1 and iswin=1 then 1.0*winprice/1000 else 0 end)  `成本`
        from logInfo
        group by provincename,cityname
      """)

    Save.saveToMysql(res,"dmp",load.getString("jdbc.tablename2"))
    ssc.stop()



  }

}
