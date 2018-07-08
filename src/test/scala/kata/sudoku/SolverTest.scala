package kata.sudoku

import org.scalatest.FlatSpec

class SolverTest extends FlatSpec {

  // https://www.sudokukingdom.com/very-easy-sudoku.php
  val kidsGrid =
    """
      |1 . 7|. 8 5|. 9 .|
      |6 . .|7 . .|8 5 2|
      |8 . 5|6 3 .|. . 7|
      |------------------
      |3 5 .|. 1 6|. 7 .|
      |7 1 .|4 . 2|. . 8|
      |. 8 .|3 . .|9 6 1|
      |------------------
      |. 3 4|. . 8|7 . 5|
      |. . 8|5 7 3|1 . .|
      |. 7 1|. 2 .|6 8 .|
    """

  "gameStart" should "initialise the Game object with given grid" in {
    val game = Solver.gameStart(kidsGrid)

    assertResult(Set(2, 4)) { game.moves(1, 2) }
    assertResult(Set(2)) { game.moves(1, 4) }
    assertResult(Set(3, 4, 6)) { game.moves(1, 9)}
  }


  "naiveSolver" should "solve the kids problem" in {
    val game = Solver.gameStart(kidsGrid)
    val nextIndex = game.cells.size

    val solution = Solver.naiveSolver(game)

    assertResult(2) {solution(nextIndex)}
  }


}
