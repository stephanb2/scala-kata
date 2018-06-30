package kata.mastermind

import org.scalatest._

class SolverTest extends FlatSpec {

  behavior of "enumerateHits"

  it should "have 14 lists for mastermind (base 6, 4 symbols)" in {
    assert(Solver.enumerateHits(4).length == 14)
  }

  //it should "have a sum of white and black keys less or equal to numSymbols"

  behavior of "enumerateCodes"

  it should "enumerate all possible codes in base 3, for 2 symbols" in {
    val expected = List(Vector(1, 1), Vector(1, 2), Vector(1, 3),
      Vector(2, 1), Vector(2, 2), Vector(2, 3),
      Vector(3, 1), Vector(3, 2), Vector(3, 3) )

    assertResult(expected) {
      Solver.enumerateCodes(3, 2)
    }
  }

  behavior of "getRemainingCodes"

  it should "match the first two steps in D. Knuth example" in {
    val possibleCodes = Solver.enumerateCodes()
    val codesStep01 = Solver.getRemainingCodes(Vector(1, 1, 2, 2), List(1, 0), possibleCodes)
    assert(codesStep01.length == 256)
    val codesStep02 = Solver.getRemainingCodes(Vector(1, 3, 4, 4), List(0, 1), codesStep01)
    assert(codesStep02.length == 44)
  }


  behavior of "countEliminatedCodes"

  it should "count eliminated codes in (base 3, 2 symbols) example" in {
    val possibleCodes = Solver.enumerateCodes(3, 2)
    // one black hit
    assertResult(5) {
      Solver.countEliminatedCodes(Vector(1, 1), List(1, 0), possibleCodes)
    }
    // no hits
    assertResult(8) {
      Solver.countEliminatedCodes(Vector(1, 2), List(0, 0), possibleCodes)
    }
  }

  it should "match the first step in D. Knuth example" in {
    val possibleCodes = Solver.enumerateCodes()

    assertResult(1296 - 256) {
      Solver.countEliminatedCodes(Vector(1, 1, 2, 2), List(1, 0), possibleCodes)
    }
  }


  behavior of "makeNextGuess"

  it should "match the first steps in D. Knuth example" in {
    val possibleCodes = Solver.enumerateCodes()
    val possibleGuess = possibleCodes

    val codes01 = Solver.getRemainingCodes(Vector(1, 1, 2, 2), List(1, 0), possibleCodes)
    val guess01 = possibleGuess.filterNot(_ == Vector(1, 1, 2, 2))

    assertResult(Vector(1, 3, 4, 4)) {
      Solver.makeNextGuess(guess01, codes01)
    }
  }

  it should "follow D. Knuth example" in {
    var codes = Solver.enumerateCodes()
    var guesses = Solver.enumerateCodes()

    codes = Solver.getRemainingCodes(Vector(1, 1, 2, 2), List(1, 0), codes)
    guesses = guesses.filterNot(_ == Vector(1, 1, 2, 2))
    assertResult(Vector(1, 3, 4, 4)) {
      Solver.makeNextGuess(guesses, codes)
    }

    codes = Solver.getRemainingCodes(Vector(1, 3, 4, 4), List(0, 1), codes)
    guesses = guesses.filterNot(_ == Vector(1, 3, 4, 4))
    assertResult(Vector(3, 5, 2, 6)) {
      Solver.makeNextGuess(guesses, codes)
    }

    codes = Solver.getRemainingCodes(Vector(3, 5, 2, 6), List(1, 2), codes)
    guesses = guesses.filterNot(_ == Vector(3, 5, 2, 6))
    assertResult(Vector(1, 4, 6, 2)) {
      Solver.makeNextGuess(guesses, codes)
    }
  }


}
