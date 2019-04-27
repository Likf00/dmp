package com.ylqz.dmp.tags

import ch.hsr.geohash.GeoHash
import com.ylqz.dmp.tools.{BaiduLBSHandler, JedisConnectionPool}
import org.apache.commons.lang.StringUtils
import org.apache.spark.SparkConf
import org.apache.spark.sql.{DataFrame, SparkSession}

import scala.tools.scalap.scalax.util.StringUtil

/**
  * @ClassName ExtractLongandLat2Business.scala
  * @author yimting
  * @version 1.0.0
  * @Description 生成商圈标签 存储到Redis
  * @createTime 2019年04月24日 23:43:00
  */
object ExtractLongandLat2Business {
  def main(args: Array[String]): Unit = {
    if(args.length!=1){
      println("Input Arguments Error !!")
      sys.exit(0)
    }

    val Array(inputPath) = args
    val conf = new SparkConf()
      .setAppName(s"${this.getClass.getName}")
      .set("spark.serializer","org.apache.spark.serializer.KryoSerializer")
      .setMaster("local[*]")

    val spark = SparkSession.builder()
      .config(conf)
      .getOrCreate()

    val df: DataFrame = spark.read.parquet(inputPath)
    val logAndLat = df.select("long","lat")
      .filter("cast(long as double) >= 73.66 and cast(long as double) <= 135.05 and cast(lat as double) >= 3.86 and cast(last as double) <= 53.55")
      .distinct()

    // 将商圈信息持久化到redis中，便于以后的查找
    logAndLat.foreachPartition(x=>{
      var jedis = JedisConnectionPool.getConnection()
      x.foreach(x=>{
        val long = x.getAs[String]("long")
        val lat = x.getAs[String]("lat")
        if(StringUtils.isNotBlank(long) && StringUtils.isNotBlank(lat)){
          //通过 百度的逆向地址解析，获取到商圈信息
          val geoHashs:String = GeoHash.geoHashStringWithCharacterPrecision(lat.toDouble,long.toDouble,8)
          // 进行SN验证
          val business:String = BaiduLBSHandler.paraseBusinessTagBy(long,lat)
          if(StringUtils.isNotBlank(business))
            jedis.set(geoHashs,business)
        }
      })
      jedis.close()
    })



  }
}
