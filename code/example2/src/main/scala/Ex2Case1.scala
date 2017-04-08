import shapeless._

case class Account(id: Long, user: User)

case class User(
  id: Long,
  name: String,
  active: Boolean,
  score: Double = 1d
)

trait CSVSerializer[T] {
  def serialize(t: T): List[String]
}

trait LowPriorityImplici

object CSVSerializer {

  def apply[A](implicit s: CSVSerializer[A]): CSVSerializer[A] = s

  implicit class WithSerialize[A](a: A) {
    def toCSV(implicit serializer: CSVSerializer[A]): String =
      serializer.serialize(a).mkString(",")
  }

  implicit val longSerializer: CSVSerializer[Long] =
    (t: Long) => List(t.toString)
  implicit val stringSerializer: CSVSerializer[String] =
    (t: String) => List(t.toString)
  implicit val booleanSerializer: CSVSerializer[Boolean] =
    (t: Boolean) => List(t.toString)

  implicit val hnilSeializer: CSVSerializer[HNil] =
    (_: HNil) => Nil

  implcit

  implicit def hconsSerializer[H, T <: HList](
    implicit hSerial: Lazy[CSVSerializer[H]],
    tSerial: CSVSerializer[T]
  ): CSVSerializer[H :: T] =
    (t: H :: T) =>
      hSerial.value.serialize(t.head) ::: tSerial.serialize(t.tail)

  implicit def genSerializer[P, Repr0](
    implicit gen: Generic.Aux[P, Repr0],
    reprSerializer: Lazy[CSVSerializer[Repr0]]
  ): CSVSerializer[P] = (p: P) =>
    reprSerializer.value.serialize(gen.to(p))
}

import CSVSerializer._

object Ex2Case1 extends App {

  val user = User(1L, "josh", active = true)
  val userCSV = user.toCSV
  println(userCSV)
  assert(userCSV == "1,josh,true")

  implicitly[Double :: HNil]

  println(Account(2L, user).toCSV)

//
//  CSVSerializer[Account]
//  println(Account(1L, user).toCSV)
//
//  CSVSerializer[]

  // implicitly[User :: HNil]
}







/*

 x define a type class
 x define instances for primitive types
 - define hnil instance
 - define hcons instance
 - define generic instance
 - test
























 - Coproduct instances?
 - debugging problem
 - define fallback implicit

 */







