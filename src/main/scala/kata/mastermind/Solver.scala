package kata.mastermind

object Solver {

  def enumerateHits(numSymbols: Int = 4): List[List[Int]] = {
    val until3 = List.range(0, numSymbols)
      .flatMap(x => (0 to numSymbols - x).map(y => List(x, y))  )

    // the combination (3, 1) doesn't exist, it would be (4, 0)
    until3.dropRight(1) :+ List(numSymbols, 0)
  }


  def enumerateCodes(base: Int = 6, decimals: Int = 4): List[Vector[Int]] = {
    val maxValue = math.pow(base, decimals).toInt
    val zeroInt = '0'.toInt - 1 // we use Knuth code values from 1 to base

    val pad = (v: Vector[Int]) =>  Vector.fill(decimals - v.length)(1) ++ v

    List.range(0, maxValue).map( x => {
      val unpadded = BigInt(x).toString(base).toVector.map(_ - zeroInt)
      pad(unpadded)
    })
  }


  def getRemainingCodes(guess: Vector[Int], hits: List[Int], possibleCodes: List[Vector[Int]]): List[Vector[Int]] = {
    possibleCodes.filter(code => KeyUtils.blackWhiteHits(guess, code) == hits)
  }


  def countEliminatedCodes(guess: Vector[Int], hits: List[Int], possibleCodes: List[Vector[Int]]): Int = {
    possibleCodes.length - getRemainingCodes(guess, hits, possibleCodes).length
  }

  //TODO: cache possibleHits list
  def minCountEliminatedCodes(guess: Vector[Int], possibleCodes: List[Vector[Int]]): Int = {
    val possibleHits = enumerateHits(possibleCodes(0).length)

    possibleHits.map(countEliminatedCodes(guess, _, possibleCodes)).min
  }


  def makeNextGuess(possibleGuess: List[Vector[Int]], possibleCodes: List[Vector[Int]]): Vector[Int] = {
    val minCounts = possibleGuess.map(minCountEliminatedCodes(_, possibleCodes)) // get all min values
    val maxValue = minCounts.max //minmax value

    val nextGuess = possibleGuess.zip(minCounts).filter(_._2 == maxValue).map(_._1)
    // is one of the next guesses in the possibleCodes set?
    val guessInCodesSet = nextGuess.find(possibleCodes.toSet.contains(_))
    // return first guess in possibleCodes, if not, first in nextGuess list.
    guessInCodesSet.getOrElse(nextGuess.head)
  }

}
