package kata.euler

object Euler09 extends App {

  val sumSquares = ((t: Tuple2[Int, Int]) => t._1*t._1 + t._2*t._2)

  val maxVal = 1000
  val targetVal = 1000

  //Quick and inefficient solution
  val pairsList = List.range(1, maxVal).flatMap(x => List.range(x+1, maxVal).map(y => (x, y)))

  val triplets = pairsList.map(p => (p._1, p._2, math.sqrt(sumSquares(p))))
    .filter(x => x._3 % 1 == 0) // is square
    .filter(x => x._1 + x._2 + x._3 == targetVal)
  println(triplets.head)


  //Iterator solution
  def pairs(maxVal: Int) =
    for (i <- Iterator.range(1, maxVal);
         j <- Iterator.range(i, maxVal) )
      yield (i, j)

  val solution2 = pairs(maxVal).map(p => (p._1, p._2, math.sqrt(sumSquares(p))))
    .filter(x => x._3 % 1 == 0) // is square
    //.takeWhile(x => x._1 + x._2 + x._3 != targetVal)

  // doesn't behave as expected. need to implement takeUntil...
  var x = (0, 0, 0.0)
  do { x = solution2.next() }
  while (x._1 + x._2 + x._3 != targetVal && solution2.hasNext)
  println(x)

}
