package kata.bowling

import org.scalatest.FunSuite

class ScoreTest extends FunSuite {

  test("trivial game, simple frames only") {
    val game = List(NormalFrame(3, 4), NormalFrame(5, 1))

    assertResult(List(7, 6)) {
      ScoreUtils.getBaseScore(game)
    }
  }

  test("spare frame") {
    val game = List(NormalFrame(3, 4), SpareFrame(3), NormalFrame(5, 1))

    assertResult(List(7, 10, 6)) {
      ScoreUtils.getBaseScore(game)
    }

    assertResult(List(0, 5, 0)) {
      ScoreUtils.getBonus(game)
    }

    assertResult(List(7, 15, 6)) {
      ScoreUtils.getScore(game)
    }
  }

  test("strike") {
    val game = List(NormalFrame(3, 4), StrikeFrame(), NormalFrame(5, 1))

    assertResult(List(0, 6, 0)) {
      ScoreUtils.getBonus(game)
    }

    assertResult(List(7, 16, 6)) {
      ScoreUtils.getScore(game)
    }

    assertResult(List(7, 23, 29)) {
      ScoreUtils.getTotals(game)
    }
  }

  // https://programmingpraxis.com/2009/08/11/uncle-bobs-bowling-game-kata/
  test("Uncle Bob's example"){
    val game = List(NormalFrame(1, 4), NormalFrame(4, 5), SpareFrame(6),  SpareFrame(5), StrikeFrame(),
      NormalFrame(0, 1), SpareFrame(7), SpareFrame(6), StrikeFrame(), SpareFrame(2), NormalFrame(6, 0)
    )

    assertResult(List(5, 14, 29, 49, 60, 61, 77,  97, 117, 133)) {
      ScoreUtils.getTotals(game)
    }
  }

}
