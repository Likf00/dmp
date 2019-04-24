package com.ylqz.dmp.tags

import org.apache.spark.sql.Row

/**
  * @ClassName TagDevice.scala
  * @author yimting
  * @version 1.0.0
  * @Description 设备标签 (操作系统 -> 1) (联网方 -> 1) (运营商 -> 1)
  * @createTime 2019年04月24日 14:33:00
  */
object TagDevice extends Tags {
  /**
    * 数据标签接口
    */
  override def makeTags(anys: Any*): List[(String, Int)] = {
    var list = List[(String,Int)]()

    val row:Row = anys(0).asInstanceOf[Row]

    //client: Int,	设备类型 （1：android 2：ios 3：wp）
    val osType = row.getAs[Int]("client")
    osType match {
      case 1 => list :+= ("D00010001",1)
      case 2 => list :+= ("D00010002",1)
      case 3 => list :+= ("D00010003",1)
      case _ => list :+= ("D00010004",1)
    }

    //设备联网方式 : networkmannername:String,	联网方式名称
    val network = row.getAs[String]("networkmannername")
    network match {
      case "WIFI" => list :+=("D00020001",1)
      case "4G" => list :+=("D00020002",1)
      case "3G" => list :+=("D00020003",1)
      case "2G" => list :+=("D00020004",1)
      case  _   => list :+=("D00020005",1)
    }

    //ispname: String,	运营商名称
    val ispname = row.getAs[String]("ispname")
    ispname match {
      case "移动" => list:+= ("D00030001",1)
      case "联通" => list:+= ("D00030002",1)
      case "电信" => list:+= ("D00030003",1)
      case   _   => list:+= ("D00030004",1)
    }

    list
  }
}
