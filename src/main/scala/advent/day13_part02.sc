import scala.annotation.tailrec

val start_ts = 939
val periods_in01 = "7,13,x,x,59,x,31,19"

def periods_sparse(periods_input: String) = periods_input
  .split(",").zipWithIndex
  .filter(x => x._1 != "x")
  .map(x => (x._1.toInt, x._2 % x._1.toInt))

val periods_sparse01 = periods_sparse(periods_in01)
periods_sparse01.take(2)

def minsToNext(start_ts: Long, periods_sparse: Array[Tuple2[Int, Int]]) =
  periods_sparse.map { x =>
    val phase = start_ts % x._1
    if (phase > 0) x._1 - phase else 0
  }

minsToNext(939L, periods_sparse01)

@tailrec
def find_match(start_ts: Long, limit: Long,
               periods_sparse: Array[Tuple2[Int, Int]],
               inc: Long = 1): Long = {
  if (limit == 0) -1
  else if (minsToNext(start_ts, periods_sparse) sameElements periods_sparse.map(x => x._2)) start_ts
  else find_match(start_ts + inc, limit - inc, periods_sparse)
}


def find_match_faster(limit: Long, periods_sparse: Array[Tuple2[Int, Int]]): Long = {
  val periods_sorted = periods_sparse.sortWith((x, y) => x._1 > y._1)
  val max_period = periods_sorted(0)
  find_match(max_period._2, limit, periods_sparse, inc = max_period._1)
}

// test 01
minsToNext(1068781L, periods_sparse01)

//test 02
val example01 = find_match(1L, 2e06.toLong, periods_sparse01)

// test04
val periods_04 = periods_sparse("67,7,x,59,61")
val example04 = find_match(1L, 2e06.toLong, periods_04)
find_match_faster(2e06.toLong, periods_04)

val periods_05 = periods_sparse("1789,37,47,1889")
//val example05 = find_match(1L, 1e08.toLong, periods_05)
val example05 = find_match_faster(1e08.toLong, periods_05)

val periods_10 = periods_sparse("29,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,41,x,x,x,x,x,x,x,x,x,577,x,x,x,x,x,x,x,x,x,x,x,x,13,17,x,x,x,x,19,x,x,x,23,x,x,x,x,x,x,x,601,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,37")
//val my_example = find_match(1L, 1e08.toLong, periods_10)

/*
def find_match_fast(limit: Long, periods_sparse: Array[Tuple2[Int, Int]], start: Long = 0L): Long = {
  val find_next = find_match(start, limit, periods_sparse.take(2), inc = periods_sparse.head._1)
  val periods_next = (periods_sparse(0)._1 * periods_sparse(1)._1, )
  find_match_fast(limit,  )
}
*/
