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
  def getSubgridKeys(move: Cell, gridSize: Int = 9): Set[Tuple2[Int, Int]] = {
    //val base = math.sqrt(gridSize)
    val base = 3
    val rowOffset = base * ((move.key._1 - 1) / base) + 1
    val colOffset = base * ((move.key._2 - 1) / base) + 1

    Range(rowOffset, rowOffset + base).flatMap(x =>
      Range(colOffset, colOffset + base).map(y => (x, y))).toSet
  }


  def nextMoves(move: Cell, possibleMoves: Moves): Moves = {
    val affectedKeys = getRowKeys(move)
      .union(getColumnKeys(move))
      .union(getSubgridKeys(move))

    val elimination = possibleMoves.filterKeys(affectedKeys.contains(_)).transform((key, set) => set - move.value) ++
      possibleMoves.filterKeys(! affectedKeys.contains(_))

    // Finally, remove move itself and empty sets.
    elimination.filterKeys(_ != move.key).filter(kv => kv._2.nonEmpty)
  }


  //TODO: refactor this 90 minutes horror
  def getCells(input: String): List[Cell] = {
    val indexToRowCol = ((i: Int) => (i / 9 + 1, i % 9 + 1))
    val Digits = "[1-9]".r
    val step1 = for (c <- input) yield {
      c match {
        case '.' => 0
        case Digits() => c.toString.toInt
        case _ => -1
      }
    }
    val step2 = step1.filterNot(_ == -1) //skip unknown characters
      .zipWithIndex
      .filterNot(_._1 == 0) //remove empty cells

    step2.map(t => Cell(indexToRowCol(t._2), t._1)).toList
  }

  def cellsToString(cells: List[Cell]): String = {
    var grid = Array.ofDim[Int](9, 9)
    for (cell <- cells) {
      grid(cell.key._1 - 1)(cell.key._2 - 1) = cell.value
    }
    grid.map(_.mkString(" ")).mkString("\n")
  }

}
