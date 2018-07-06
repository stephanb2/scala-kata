import java.security.KeyStore.TrustedCertificateEntry

type DokSet = Map[Tuple2[Int, Int], Set[Int]]

def initDokSet(): DokSet = {
  val allSet = Set(1)

  Map((1, 1) -> allSet)
}

Range(1, 10).flatMap(x => Range(1, 10).map(y => (x, y)))

Range(1, 10).toSet

12

val mm = Range(0, 2).map(x => x -> 0).toMap
mm.size
mm(1)

mm ++ Map(6 -> 2)

val ll = List(1, 2, 3)
ll.contains(2)
! true

ll.toSet - 1
