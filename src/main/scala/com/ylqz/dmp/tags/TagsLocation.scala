package com.ylqz.dmp.tags

import org.apache.commons.lang.StringUtils
import org.apache.spark.sql.Row

/**
  * @ClassName TagsLocation.scala
  * @author yimting
  * @version 1.0.0
  * @Description TODO
  * @createTime 2019年04月24日 20:41:00
  */
object TagsLocation extends Tags {
  /**
    * 数据标签接口
    */
  override def makeTags(anys: Any*): List[(String, Int)] = {
    var list = List[(String,Int)]()
    val row = anys(0).asInstanceOf[Row]
    // provincename: String,	设备所在省份名称
    // cityname: String,	设备所在城市名称
    val provincename = row.getAs[String]("provincename")
    val cityname = row.getAs[String]("cityname")
    if(StringUtils.isNotBlank(provincename)){
      list :+= ("ZP"+provincename,1)
    }
    if(StringUtils.isNotBlank(cityname)){
      list :+= ("ZC"+cityname,1)
    }
    list
  }
}
