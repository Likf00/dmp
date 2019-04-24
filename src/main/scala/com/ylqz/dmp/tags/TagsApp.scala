package com.ylqz.dmp.tags

import org.apache.commons.lang.StringUtils
import org.apache.spark.broadcast.Broadcast
import org.apache.spark.sql.Row

/**
  * @ClassName TagsApp.scala
  * @author yimting
  * @version 1.0.0
  * @Description App的标签化
  * @createTime 2019年04月24日 12:54:00
  */
object TagsApp extends Tags {
  /**
    * 数据标签接口
    */
  override def makeTags(anys: Any*): List[(String, Int)] = {
    var list = List[(String,Int)]()
    val row = anys(0).asInstanceOf[Row]
    //获取APP字典文件，使用缓存文件 appname_dict 进行名称转换
    val appnamedic = anys(1).asInstanceOf[Broadcast[Map[String,String]]]
    //获取appname: String,	应用名称
    val appname = row.getAs[String]("appname")
    //获取appid ：String
    val appid = row.getAs[String]("appid")

    if(StringUtils.isNotBlank(appname)){
      list :+= ("APP"+appname,1)
    }else if(StringUtils.isNotBlank(appid)){
      list :+= ("APP"+appnamedic.value.getOrElse(appid,appid),1)
    }
    list
  }
}
