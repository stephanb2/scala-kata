package kata.sudoku

object GridUtils {

  type Moves = Map[Tuple2[Int, Int], Set[Int]]


  def getAllMoves(gridSize: Int = 9): Moves = {
    val allMoves = Range(1, gridSize + 1).toSet
    val allKeys = Range(1, gridSize + 1).flatMap(x => Range(1, gridSize + 1).map(y => (x, y)))

    allKeys.map(k => (k -> allMoves)).toMap
  }

  def getRowKeys(move: Cell, gridSize: Int = 9): Set[Tuple2[Int, Int]] = {
    Range(1, gridSize + 1).map((move.key._1, _)).toSet
  }

  def getColumnKeys(move: Cell, gridSize: Int = 9): Set[Tuple2[Int, Int]] = {
    Range(1, gridSize + 1).map((_, move.key._2)).toSet
  }

  //TODO: for now, hard coded for 9x9 grids
  def getSubCubeKeys(move: Cell, gridSize: Int = 9): Set[Tuple2[Int, Int]] = {
    //val base = math.sqrt(gridSize)
    val base = 3
    val rowOffset = base * ((move.key._1 - 1) / base) + 1
    val colOffset = base * ((move.key._2 - 1) / base) + 1

    Range(rowOffset, rowOffset + base).flatMap(x =>
      Range(colOffset, colOffset + base).map(y => (x, y))).toSet
  }


  def getNewMoves(move: Cell, oldMoves: Moves): Moves = {
    val affectedKeys = getRowKeys(move)
      .union(getColumnKeys(move))
      .union(getSubCubeKeys(move))

    val elimination = oldMoves.filterKeys(affectedKeys.contains(_)).transform((key, set) => set - move.value) ++
      oldMoves.filterKeys(! affectedKeys.contains(_))

    // Finally, remove empty sets.
    elimination.filter((t) => t._2.nonEmpty)
  }

}
