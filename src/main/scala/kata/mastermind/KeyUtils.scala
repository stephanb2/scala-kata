package kata.mastermind

object KeyUtils {

  def dropExactMatches(guess: Vector[Int], secret: Vector[Int]): Tuple2[Vector[Int], Vector[Int]] = {
    val result = guess.zip(secret).filter(x => x._1 != x._2)
    //now unzip the vector of tuples to a tuple of vectors
    (result.map(_._1), result.map(_._2))
  }

  def countFuzzyMatches(guess: Vector[Int], secret: Vector[Int], result: Int = 0): Int = {
    guess match {
      case x +: xs => {
        val found = secret.indexWhere(_ == x)
        if (-1 != found)
          countFuzzyMatches(xs, secret.take(found) ++ secret.drop(found + 1), result + 1)
        else
          countFuzzyMatches(xs, secret, result)
      }
      // Vector doesn't have a Nil matcher
      case _ => result
    }
  }

  def blackWhiteHits(guess: Vector[Int], secret: Vector[Int]): List[Int] = {
    val (fuzzyGuess, fuzzySecret) = dropExactMatches(guess, secret)
    val blacks = guess.length - fuzzyGuess.length
    val whites = countFuzzyMatches(fuzzyGuess, fuzzySecret)
    List(blacks, whites)
  }

  def stringToVec(entry: String, skip: String = " "): Vector[Int] = {
    entry.replaceAll(" ","")
      .toCharArray.map(_.toInt).toVector
  }

}
