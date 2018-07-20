package kata.euler

object LargestPrimeFac extends App {

  val euler03 = 600851475143L
  val primeDec03 = Primes.primeDec(euler03)

  println("factors of %d: %s".format(euler03, primeDec03))
  //println("factors of %d: %s".format(euler03, primeDec03.keys.toSeq.sorted) )
}
