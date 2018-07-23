package kata.euler

object Euler09 extends App {
  val sumSquares = ((t: Tuple2[Int, Int]) => t._1*t._1 + t._2*t._2)


  val maxVal = 1000
  val targetVal = 1000
  val pairs = List.range(1, maxVal).flatMap(x => List.range(x+1, maxVal).map(y => (x, y)))

  val triplets = pairs.map(p => (p._1, p._2, math.sqrt(sumSquares(p))))
    .filter(x => x._3 % 1 == 0) // is square
    .filter(x => x._1 + x._2 + x._3 == targetVal)
  println(triplets)

}
