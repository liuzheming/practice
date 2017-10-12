package scala

/**
  * Description:
  *
  * Created by lzm on 2017/9/24.
  */
object HelloWorld {

  def main(args: Array[String]): Unit = {

    println("This is my first scala practice !")

    val aa = min2(2, 3)
    println(aa)
  }


  def adder(m: Int, n: Int) = m & n + 3

  def min(m: Int, n: Int): Int = {
    var a = m + 1
    if (m > n) a = n + 1
    a
  }


  def min2(m: Int, n: Int) = {
//    m = m + 1  // m、n都是常量,不能再次被赋值

  }


}
