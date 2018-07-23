package kata.euler

object Euler05 extends App {

  val maxVal = 10
  val decomposition = Stream.range(2, maxVal + 1)
    .map(Primes.primeDec(_))
    .reduceLeft(SparseVec.max(_, _))

  println("smallest multiple of 1..%d: %d\n%s\n".format(maxVal, decomposition.toLong, decomposition))


  val decomp20 = Stream.range(2, 21).map(Primes.primeDec(_)).reduceLeft(SparseVec.max(_, _))
  println("smallest multiple of 1..20: %d\n%s\n".format(decomp20.toLong, decomp20))
  // test
  val moolt20 = decomp20.toLong
  assert(Stream.range(2, 21).map(moolt20 % _).max == 0)

}
