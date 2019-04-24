package com.ylqz.dmp.tags

import com.ylqz.dmp.tools.TagUtils
import org.apache.spark.SparkConf
import org.apache.spark.sql.{DataFrame, SparkSession}

/**
  * @ClassName TagsContext.scala
  * @author yimting
  * @version 1.0.0
  * @Description 上下文标签，用来将所有标签进行汇总
  * @createTime 2019年04月23日 15:28:00
  */
object TagsContext {

  def main(args: Array[String]): Unit = {
    //判断输入是否合理
    if(args.length != 5){
      println(" 参数输入错误 ！ ")
      sys.exit(0)
    }

    val Array(inputPath,outputPath,dicPath,stopwords,day)=args

    val conf = new SparkConf()
      .setAppName(this.getClass.getName)
      .setMaster("local[*]")
      .set("spark.serializer","org.apache.spark.serializer,KryoSerializer")

    val sparkSession = SparkSession.builder()
      .config(conf)
      .getOrCreate()

    val sc = sparkSession.sparkContext

    /**
      * 配置 Hbase 属性
      */


    val dicMap = sc.textFile(dicPath).map(_.split("\t",-1))
      .filter(_.length >= 5)
      .map(line=>{
        //(cn.net.inch.android,乐自游)
        (line(4),line(1))
      }).collect().toMap

    val bdAppNameDic = sc.broadcast(dicMap)

    //获取停用的字典，广播出去
    val stopWordsDir = sc.textFile(stopwords).map((_,0)).collect().toMap
    val bdStropWordDic = sc.broadcast(stopWordsDir)

    //读取 Parquet 信息
    val df: DataFrame = sparkSession.read.parquet(inputPath)

    df.filter(TagUtils)

  }
}
