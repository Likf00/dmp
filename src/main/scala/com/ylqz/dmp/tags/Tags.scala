package com.ylqz.dmp.tags

/**
  * @ClassName Tags.scala
  * @author yimting
  * @version 1.0.0
  * @Description 给定Tags标签接口，使得标签格式统一
  * @createTime 2019年04月24日 11:25:00
  */
trait Tags {

  /**
    * 数据标签接口
    */
  def makeTags (anys:Any*):List[(String,Int)]
}
