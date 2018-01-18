package dataStructure.sort


/**
  * Description:堆排序
  *
  * Created by lzm on 2018/1/18.
  */
object SHeapSort {


  def sort(arr: Array[Int]): Unit = {
    var size = arr.length - 1
    var i = size / 2
    while (i > 0) {
      sink(i, arr, size)
      i = i - 1
    }
    while (size > 0) {
      exch(1, size, arr)
      size = size - 1
      sink(1, arr, size)
    }

  }

  def exch(i: Int, j: Int, arr: Array[Int]): Unit = {
    val temp = arr(i)
    arr(i) = arr(j)
    arr(j) = temp
  }

  def sink(idx: Int, arr: Array[Int], size: Int): Unit = {
    var n = idx
    while (2 * n <= size) {
      var j = 2 * n
      if (j < size && arr(j) < arr(j + 1)) j = j + 1
      if (arr(n) > arr(j)) return
      exch(n, j, arr)
      n = j
    }
  }


  def main(args: Array[String]): Unit = {

    //    val sorter: SHeapSort = new SHeapSort
    val arr = Array(-1, 22, 33, 2, 1, 5, 7, 200, 43, 234, 15, 2008, 543, 342, 314)
    SHeapSort.sort(arr)
    for (i <- arr) {
      println(i)
    }
  }


}
