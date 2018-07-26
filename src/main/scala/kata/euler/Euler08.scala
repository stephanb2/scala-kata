package kata.euler

object Euler08 extends App {

  val sequenceString = """73167176531330624919225119674426574742355349194934
                   |96983520312774506326239578318016984801869478851843"""

  val zeroCode = '0'.toInt
  val sequence = sequenceString.toCharArray.map(c => c.toInt - zeroCode).toList

  println(sequence)
}
