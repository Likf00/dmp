/**
  * @ClassName Test.scala
  * @author yimting
  * @version 1.0.0
  * @Description 测试类
  * @createTime 2019年04月23日 17:42:00
  */
object Test {

  def main(args: Array[String]): Unit = {
    val database = "dmp"
    println  (s"jdbc:mysql://192.168.168.14:3306/${database}?createDatabaseIfNotExist&user=root&password=root&characterEncoding=gbk");

  }


}
