package com.ylqz.dmp.tools

import redis.clients.jedis.{Jedis, JedisPool, JedisPoolConfig}

/**
  * @ClassName JedisConnectionPool.scala
  * @author yimting
  * @version 1.0.0
  * @Description Jedis连接池
  * @createTime 2019年04月25日 00:18:00
  */
object JedisConnectionPool {
  //创建 JedisPool 的配置
  val config = new JedisPoolConfig()
  //配置最大连接数
  config.setMaxTotal(20)
  //最大空闲连接数
  config.setMaxIdle(10)
  //当调用 Borrow Object 时 ， 是否进行有效检查  --》
  config.setTestOnBorrow(true)

  val pool = new JedisPool(config,"192.168.168.14",6379,1000,"root")

  def getConnection(): Jedis ={
    pool.getResource
  }

}
