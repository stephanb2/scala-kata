package kata.euler

import scala.annotation.tailrec

case class SparseVec(data: Map[Long, Long] = Map()) {

  def inc(index: Long): SparseVec = {
    if (data.contains(index))
      SparseVec( data + (index -> (data(index) + 1L)) )
    else
      SparseVec( data + (index -> 1L))
  }

  def toLong: Long = {
    data.foldLeft(1L)((x, y) => x * math.pow(y._1, y._2).toLong)
  }
}

object SparseVec {
  type MapLong = Map[Long, Long]

  @tailrec
  def mapMax(left: MapLong, right: MapLong, result: MapLong = Map()): MapLong = {
    left.keys.toList match {
      case k::ks => if (right.contains(k))
        mapMax(left - k, right - k, result + (k -> math.max(left(k), right(k))) )
      else
        mapMax(left - k, right, result + (k -> left(k)) )
      case Nil => result ++ right
    }
  }

  def max(left: SparseVec, right: SparseVec): SparseVec = {
    SparseVec(mapMax(left.data, right.data))
  }
}

