package com.ylqz.dmp.tools

import java.util.Properties

import com.typesafe.config.ConfigFactory
import org.apache.spark.sql.{DataFrame, SaveMode}
import org.junit.Test

/**
  * @ClassName Save.scala
  * @author yimting
  * @version 1.0.0
  * @Description 保存到其他的工具类
  * @createTime 2019年04月23日 16:35:00
  */
object Save {

  /**
    * 保存到 MySQL ， 使用该方法
    * @param df   当前使用的 DF
    * @param database 需要保存到哪个 DB 中
    * @param table  需要保存到哪个 table 中
    */
  def saveToMysql(df:DataFrame,database:String,table:String): Unit ={
    val properties =  new Properties()
    properties.put("user","root")
    properties.put("password","root")

    df.write.mode(SaveMode.Overwrite)
      .jdbc(s"jdbc:mysql://192.168.168.14:3306/${database}?createDatabaseIfNotExist&characterEncoding=gbk",table,properties)
  }



}
