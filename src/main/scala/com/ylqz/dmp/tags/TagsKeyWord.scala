package com.ylqz.dmp.tags

import org.apache.spark.broadcast.Broadcast
import org.apache.spark.sql.Row

/**
  * @ClassName TagsKeyWord.scala
  * @author yimting
  * @version 1.0.0
  * @Description 关键字标签类
  * @createTime 2019年04月24日 14:59:00
  */
object TagsKeyWord extends Tags {
  /**
    * 数据标签接口
    */
    override def makeTags(anys: Any*): List[(String, Int)] = {
      //keywords: String,	关键字
      var list = List[(String, Int)]()
      //将传送过来的文件，转换为 Row 类型的
      //p.asInstanceOf[XX] 把 p 转换成 XX 对象的实例
      val row = anys(0).asInstanceOf[Row]
      val stopDic = anys(1).asInstanceOf[Broadcast[Map[String,Int]]]

      val str: String = row.getAs[String]("keywords")
      val words: Array[String] = str.split("|")

      words.filter(x=>{
        x.length >= 3 && x.length <= 8 && !stopDic.value.contains(x)
      }).foreach(word =>{
        list :+= ("K" + word,1)
      })
      list
    }
}
