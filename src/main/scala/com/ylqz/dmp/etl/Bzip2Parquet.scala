package com.ylqz.dmp.etl

import com.ylqz.dmp.beans.Log

import org.apache.spark.rdd.RDD
import org.apache.spark.sql.SparkSession



/**
  * @ClassName Bzip2Parquet.scala
  * @author yimting
  * @version 1.0.0
  * @Description 原始文件转换parquet
  * @createTime 2019年04月23日 11:27:00
  */
object Bzip2Parquet {
  def main(args: Array[String]): Unit = {
    //判断参数的个数
    if (args.length != 2) {
      /*
        参数不合法直接退出
       */
      println(
        """
          |com.ylqz.dmp.tools.Bzip2Parquet
          |参数为：
          |LogInput，Resultoutput
        """.stripMargin)
      sys.exit(0)
    }
    //接受参数
    val Array(logInputPath, resultOutputPath) = args


    val sparkSession = SparkSession.builder()
      .appName(s"${this.getClass.getSimpleName}")
      .master("local[*]")
      .config("spark.serializer", "org.apache.spark.serializer.KryoSerializer")
      .getOrCreate()

    //获取数据
    val logInfo: RDD[String] = sparkSession.sparkContext.textFile(logInputPath)

    val logInfoRDD: RDD[Log] = logInfo
      .map(line => line.split(","))
      .filter(_.length >= 85)
      .map(line => Log(line)) //直接调用Log，Object传入参数， 自动调用 apply 方法

    //创建DF
    val df = sparkSession.createDataFrame(logInfoRDD)

    //设置分区数为1 ， 之后写出去
    df.coalesce(1)
      .write
      .parquet(resultOutputPath)

    sparkSession.stop()
  }
}
