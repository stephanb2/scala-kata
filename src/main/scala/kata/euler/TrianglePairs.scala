package kata.euler

class TrianglePairs(max: Int) extends Iterator[Tuple2[Int, Int]] {
  private var pos = (1, 1)
  override def hasNext: Boolean = (pos._1 == max) && (pos._2 == max)

  override def next(): (Int, Int) = {
    var result = pos
    pos._2 match {
      case max => pos = (pos._1 + 1, pos._1 + 1)
      case _ => pos = (pos._1, pos._2 + 1)
    }
    result
  }
}
