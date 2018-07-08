package kata.sudoku

import kata.sudoku.GridUtils.Moves

//TODO: the overloaded constructor doesn't work
case class Cell(key: Tuple2[Int, Int], value: Int) {
  def this(i: Int, value: Int) = this((i / 9 + 1, i % 9 + 1), value)
}

case class Game(cells: List[Cell], moves: Moves)

