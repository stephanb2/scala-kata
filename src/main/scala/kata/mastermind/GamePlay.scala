package kata.mastermind

object GamePlay extends App {

  var codes = Solver.enumerateCodes()
  var guesses = Solver.enumerateCodes()
  var nextGuess = Vector(1, 1, 2, 2)
  var hits = List[Int]()


  do {
    print("my guess: " + nextGuess.mkString(" ") + " -- hits (B W): ")
    val answerStr = scala.io.StdIn.readLine()
    hits = answerStr.split("\\s+").map(_.toInt).toList

    codes = Solver.getRemainingCodes(nextGuess, hits, codes)
    guesses = guesses.filterNot(_ == nextGuess)
    nextGuess = Solver.makeNextGuess(guesses, codes)

  } while (hits != List() && hits != List(4, 0))

}
