package com.ylqz.dmp.tools

import org.apache.commons.lang.StringUtils
import org.apache.spark.sql.Row

/**
  * @ClassName TagUtils.scala
  * @author yimting
  * @version 1.0.0
  * @Description TODO
  * @createTime 2019年04月23日 20:55:00
  */
object TagUtils {
def getAnyOneUserId(row:Row): Unit ={
  row match {
  case v if StringUtils.isNotBlank(v.getAs[String]("imei"))=> "IM"+v.getAs[String]("imei")

  }

}
}
