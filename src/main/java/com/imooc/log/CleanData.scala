package com.imooc.log

import org.apache.spark.sql.SparkSession

/**
  * 使用SparkSQL数据进行清洗
  */
object CleanData {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder()
      .appName(this.getClass.getName)
      .master("local")
      .getOrCreate()

    val access = spark.sparkContext.textFile("file:///192.168.168.14:22/data/imooc/access.20161111.log.gz")
    access.take(10).foreach(println)



    spark.stop()
  }
}
