package com.ylqz.dmp.tags

import org.apache.commons.lang.StringUtils
import org.apache.spark.sql.Row

/**
  * @ClassName TagsAd.scala
  * @author yimting
  * @version 1.0.0
  * @Description 广告标签，数据标签化 1，3，
  * @createTime 2019年04月24日 11:22:00
  */
object TagsAd extends Tags {
  /**
    * 数据标签接口
    */
  override def makeTags(anys: Any*): List[(String, Int)] = {
    // 空集合 用于返回
    var list = List[(String,Int)]()
    // 参数的解析,转换为Row类型的参数
    val row = anys.asInstanceOf[Row]

    // Row类型的参数是一个具有结构化数据，直接提取列名称
    val adType = row.getAs[Int]("adspacetype")
    // 进行模式匹配，（标签格式： LC03->1 或者 LC16->1）
    adType match {
        case value if value > 9 => list :+= ("LC" + value,1)
        case value if value > 0 => list :+= ("LC0" + value,1 )
    }
    // 广告位类型名称
    val adTypeName = row.getAs[String]("adspacetypename")
    if(StringUtils.isNotEmpty(adTypeName)){
      list :+= ("LN"+adTypeName,1)
    }

    // 广告渠道 = 广告平台商ID 	( >= 100000: rtb )
    //获得值类型 需要根据字段类型去获取
    val channel = row.getAs[Int]("")
    list :+= ("CN"+channel,1)

    list
  }
}
