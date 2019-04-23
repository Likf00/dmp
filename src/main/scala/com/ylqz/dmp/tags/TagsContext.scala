package com.ylqz.dmp.tags

import com.typesafe.config.ConfigFactory
import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession

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

    val conf = new SparkConf()
      .setAppName(this.getClass.getName)
      .setMaster("local[*]")
      .set("spark.serializer","org.apache.spark.serializer,KryoSerializer")

    val sparkSession = SparkSession.builder()
      .config(conf)
      .getOrCreate()

    val sc = sparkSession.sparkContext

    //配置Hbase的基本信息
    val load = ConfigFactory.load()



  }
}
