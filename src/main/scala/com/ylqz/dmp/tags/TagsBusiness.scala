package com.ylqz.dmp.tags

import org.apache.spark.sql.Row

/**
  * @ClassName TagsBusiness.scala
  * @author yimting
  * @version 1.0.0
  * @Description TODO
  * @createTime 2019年04月24日 20:56:00
  */
object TagsBusiness extends Tags {
  /**
    * 数据标签接口
    */
  override def makeTags(anys: Any*): List[(String, Int)] = {
    /**
      * long: String,	设备所在经度
      * lat: String,	设备所在纬度
      * 商圈标签？
      * 根据
      */
    var list = List[(String, Int)]()
    val row = anys.asInstanceOf[Row]
    if(row.getAs[String]("long").toDouble >= 73.66 && row.getAs[String]("long").toDouble <= 135.05){

    }


    list
  }
}
