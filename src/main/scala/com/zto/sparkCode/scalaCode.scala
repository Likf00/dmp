package com.zto.sparkCode

import org.apache.spark.streaming.dstream.{DStream, ReceiverInputDStream}
import org.apache.spark.streaming.{Seconds, StreamingContext}


object scalaCode {
  def main(args: Array[String]): Unit = {
    val ssc = new StreamingContext("local[*]",this.getClass.getName, Seconds(5))
    //    val lines = ssc.sparkContext.textFile("C:\\tmp\\input\\Wc")
//    val lines: DStream[String] = ssc.textFileStream("file:///C:\\tmp\\input")
    val lines: ReceiverInputDStream[String] = ssc.socketTextStream("localhost",9999)
//    val res= lines.flatMap(_.split(" ")).map(x => (x,1)).reduceByKey(_ + _)
    val res1 = lines.flatMap(_.split(" ")).countByValue()
//    "res"+res.print()
    "res1"+res1.print()

    ssc.start()
    ssc.awaitTermination()



  }
}
