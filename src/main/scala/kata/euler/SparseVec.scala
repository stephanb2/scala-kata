package kata.euler

case class SparseVec(data: Map[Long, Long] = Map())


object  SparseVec{

  def inc(vector: SparseVec,  index: Long): SparseVec = {
    if (vector.data.isDefinedAt(index)) {
      SparseVec( vector.data + (index -> (vector.data(index) + 1L)) )
    }
    else SparseVec( vector.data + (index -> 1L))
  }
}

