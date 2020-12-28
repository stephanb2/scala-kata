import scala.annotation.tailrec


def periods_sparse(periods_input: String) = periods_input
  .split(",").zipWithIndex
  .filter(x => x._1 != "x")
  .map(x => (x._1.toInt, x._2 % x._1.toInt))

@tailrec
def find_match_product(start_ts: Long, inc: Long, limit: Long,
                       periods: Array[(Int, Int)], count:Int = 0): Long = {
  if (count > limit) -1
  else periods match {
    case Array() => start_ts
    case arr if (start_ts % arr.head._1 == arr.head._2) =>
      find_match_product(start_ts, inc*periods.head._1, limit, periods.tail, count+1)
    case _ =>
      find_match_product(start_ts+inc, inc, limit, periods, count+1)
  }
}


def inverse_mod(periods: Array[(Int, Int)]): Array[(Int, Int)] = {
  periods.map(x => if (x._2 > 0) (x._1, x._1 - x._2) else (x._1, 0))
}

def find_match_fast(periods: Array[(Int, Int)]): Long = {
  val limit = periods.map(_._1).sum
  find_match_product(0L, periods.head._1, limit, inverse_mod(periods.tail))
}

val periods_01 = periods_sparse("17,x,13,19")
val find_01 = find_match_fast(periods_01)
assert(find_01 == 3417)

val periods_test = Seq("67,7,59,61", "67,x,7,59,61", "67,7,x,59,61", "1789,37,47,1889")
val find_results = periods_test.map(input => find_match_fast(periods_sparse(input)))

assert(find_results == Seq(754018L, 779210L, 1261476L, 1202161486L))

val periods_x = "29,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,41,x,x,x,x,x,x,x,x,x,577,x,x,x,x,x,x,x,x,x,x,x,x,13,17,x,x,x,x,19,x,x,x,23,x,x,x,x,x,x,x,601,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,37"
periods_sparse(periods_x)
val result_x = find_match_fast(periods_sparse(periods_x))




