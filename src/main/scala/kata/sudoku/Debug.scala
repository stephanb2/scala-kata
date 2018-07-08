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
      |------------------
      |. . 9|. 6 4|. . .|
      |. . 3|. . 1|8 2 .|
      |. 5 8|. . .|. 9 1|
      |------------------
      |. 4 .|9 . .|1 . .|
      |8 . .|. 7 .|2 . .|
      |6 . .|1 4 .|. . 3|
    """

  //val game1 = Solver.gameStart(kidsGrid)
  //println(Solver.naiveSolver(game1))

  val game2 = Solver.gameStart(easyGrid)
  val solution2 = Solver.btSolver(game2)
  println(solution2.drop(game2.cells.size))
  println(GridUtils.cellsToString(solution2))

}
