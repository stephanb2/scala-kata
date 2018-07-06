package kata.sudoku

import org.scalatest.FlatSpec

class GridUtilsTest extends FlatSpec {

  "getAllMoves" should "return all moves on all cells" in {
    val allMoves = GridUtils.getAllMoves()
    val expectedSet = Range(1, 10).toSet

    assert(allMoves.size == 81)
    assert(allMoves((1, 1)) == expectedSet)
    assert(allMoves((9, 9)) == expectedSet)
  }

  "getRowKeys" should "return all the keys the row of a given cell" in {
    val cell = Cell((3, 5), 1)
    val expectedKeys = Set((3, 1), (3, 2), (3, 3), (3, 4), (3, 5), (3, 6), (3, 7), (3, 8), (3, 9))

    assertResult(expectedKeys) { GridUtils.getRowKeys(cell) }
  }

  "getSubCubeKeys" should "return all the keys for the sub-cube of a given cell" in {
    val cell = Cell((3, 5), 1)
    val expectedKeys = Set((1, 4), (1, 5), (1, 6), (2, 4), (2, 5), (2, 6), (3, 4), (3, 5), (3, 6))

    assertResult(expectedKeys) { GridUtils.getSubCubeKeys(cell) }
  }


  behavior of "getPossibleMoves"

  it should "eliminate value in the same row" in {
    val cell = Cell((3, 5), 1)
    val currentMoves = Map((3, 1) -> Set(1, 2, 3), (3, 2) -> Set(4, 5, 6), (3, 7)-> Set(1))
    val expectedMoves = Map((3, 1) -> Set(2, 3), (3, 2) -> Set(4, 5, 6))

    assertResult(expectedMoves) { GridUtils.getNewMoves(cell, currentMoves) }
  }

  it should "eliminate value in the sub-cube" in {
    val cell = Cell((3, 5), 1)
    val currentMoves = Map((1, 4) -> Set(1, 2, 3), (2, 5) -> Set(4, 5, 6), (2, 6)-> Set(1))
    val expectedMoves = Map((1, 4) -> Set(2, 3), (2, 5) -> Set(4, 5, 6))

    assertResult(expectedMoves) { GridUtils.getNewMoves(cell, currentMoves) }
  }

  it should "eliminate value in the ropw, column, and sub-cube" in {
    val cell = Cell((3, 5), 1)
    val currentMoves = Map((1, 4) -> Set(1, 2, 3), (2, 5) -> Set(4, 5, 6), (2, 6)-> Set(1),
      (3, 1) -> Set(1, 3, 4),
      (8, 5) -> Set(4, 5), (9, 5) -> Set(1, 9))

    val expectedMoves = Map((1, 4) -> Set(2, 3), (2, 5) -> Set(4, 5, 6),
      (3, 1) -> Set(3, 4),
      (8, 5) -> Set(4, 5), (9, 5) -> Set(9))

    assertResult(expectedMoves) { GridUtils.getNewMoves(cell, currentMoves) }
  }

}
