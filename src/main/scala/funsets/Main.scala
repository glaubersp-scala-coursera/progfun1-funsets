package funsets

object Main extends App {
  import FunSets._
  val sEven: Set = (elem: Int) => 2 to 10 by 2 contains elem
  val sOdd: Set = (elem: Int) => 1 to 10 by 2 contains elem
  val sEvenWithOneOdd: Set = (elem: Int) => (elem == 1) || (2 to 10 by 2 contains elem)
  val sOddWithOneEven: Set = (elem: Int) => (elem == 2) || (1 to 10 by 2 contains elem)
  val sEvenDuplicated: Set = (elem: Int) => (2 to 10 by 2) map (x => x * 2) contains elem
  val sOddDuplicated: Set = (elem: Int) => (1 to 10 by 2) map (x => x * 2) contains elem

  println(printSet(sEven))
  println(printSet(sEvenWithOneOdd))
  println(printSet(sEvenDuplicated))
  println(printSet(map(sEven, (x => x * 2))))

  println(printSet(sOdd))
  println(printSet(sOddWithOneEven))
  println(printSet(sOddDuplicated))
  println(printSet(map(sOdd, (x => x * 2))))

}
