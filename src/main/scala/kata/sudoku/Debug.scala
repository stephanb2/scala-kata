package kata.sudoku

object Debug extends App {

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

  val easyGrid =
    """
      |. . .|7 1 .|. 3 5|
      |9 3 6|. . 2|. . .|
      |5 . .|. . .|4 . 2|
      |-----------------|
      |. . 9|. 6 4|. . .|
      |. . 3|. . 1|8 2 .|
      |. 5 8|. . .|. 9 1|
      |-----------------|
      |. 4 .|9 . .|1 . .|
      |8 . .|. 7 .|2 . .|
      |6 . .|1 4 .|. . 3|
    """

  val hardGrid =
    """
      |4 . .|. . .|. 1 .|
      |. . .|4 7 .|. 8 .|
      |1 8 6|. . .|. 4 .|
      |- - - - - - - - -|
      |. . .|5 8 .|. . 6|
      |. 5 .|1 . .|4 2 .|
      |2 . .|. . 4|. . .|
      |- - - - - - - - -|
      |. . 1|. . .|5 . 9|
      |. 2 .|6 9 .|. . .|
      |3 . 9|. . 5|. . .|
    """

  val veryHardGrid =
    """
      |. 6 .|2 . .|. . .|
      |. . .|. . .|8 . 6|
      |3 . .|6 . 1|. . .|
      |- - - - - - - - -|
      |1 . .|. . 4|. 9 3|
      |. 4 2|5 . .|. . .|
      |. . .|. . 9|7 . .|
      |- - - - - - - - -|
      |. . 4|. 5 .|. 3 7|
      |. . 7|. . .|. 4 .|
      |6 . .|. . 7|. . .|
    """

  /*
  val game = Solver.gameStart(easyGrid)
  val solution = Solver.btSolver(game)
  println(solution.drop(game.cells.size))
  println(GridUtils.cellsToString(solution))
  */

  val game = Solver.gameStart(veryHardGrid) //hardGrid
  val solution = Solver.btSolver(game)
  println(solution.drop(game.cells.size))
  println(GridUtils.cellsToString(solution))
}
