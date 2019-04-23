package com.ylqz.dmp.etl

/**
  * @ClassName NBF.scala
  * @author yimting
  * @version 1.0.0
  * @Description 进行类型的转换
  * @createTime 2019年04月23日 13:56:00
  */
object NBF {

  def toInt(str:String): Int ={
    try{
      str.toInt
    }catch {
      case  _:Exception => 0
    }
  }

  def toDouble(str:String)={
    try{
      str.toDouble
    }catch{
      case _ : Exception => 0.0
    }
  }

}
