package com.scala

import org.apache.spark.Partitioner

/**
  * @ClassName Test.scala
  * @author yimting
  * @version 1.0.0
  * @Description 测试类
  * @createTime 2019年04月23日 17:42:00
  */
object Test {

  def main(args: Array[String]): Unit = {

  }
}



class UserPartition(partitionNum : Int) extends Partitioner{
  override def numPartitions: Int = partitionNum

  override def getPartition(key: Any): Int = ???

}