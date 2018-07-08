package kata.sudoku

import kata.sudoku.GridUtils.Moves

import scala.annotation.tailrec

object Solver {

  def gameStart(input: String): Game = {
    val givenCells = GridUtils.getCells(input)
    var moves = GridUtils.getAllMoves()

    for (cell <- givenCells) {
      moves = GridUtils.nextMoves(cell, moves)
    }

    Game(givenCells, moves)
  }

  // why can't I match on `case isEmpty` ?
  def getMinSizeMoves(game: Game) : Tuple2[Tuple2[Int, Int], Set[Int]] = {
    game.moves.size match {
      case 0 => ((0, 0), Set.empty[Int])
      case _ => {
        val sizes = game.moves.mapValues(_.size)
        val minSize = sizes.values.min
        val minIndex = sizes.filter(_._2 == minSize).keys.head
        (minIndex, game.moves(minIndex))
      }
    }
  }


  @tailrec
  def naiveSolver(game: Game, limit: Int = 0): List[Cell] = {
    if (game.moves.isEmpty)
      game.cells
    else {
      val (nextIndex, nextValues) = getMinSizeMoves(game)
      val value = nextValues.head
      val nextCell = Cell(nextIndex, value)
      val cells2 = game.cells :+ nextCell
      val moves2 = GridUtils.nextMoves(nextCell, game.moves)
      naiveSolver(Game(cells2, moves2), limit + 1)
    }
  }


  def btSolver(game: Game, depth: Int = 0): List[Cell] = {
    val (nextIndex, nextValues) = getMinSizeMoves(game)
    val nextValuesIter = nextValues.toIterator

    var currentCells = game.cells
    while (nextValuesIter.hasNext && currentCells.size < 81) {
      val nextCell = Cell(nextIndex, nextValuesIter.next())
      val cells2 = game.cells :+ nextCell
      val moves2 = GridUtils.nextMoves(nextCell, game.moves)
      currentCells = btSolver(Game(cells2, moves2), depth + 1)
    }
    currentCells
  }



}
