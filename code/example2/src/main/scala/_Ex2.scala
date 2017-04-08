/*
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

  implicit val hnilSerializer: CSVSerializer[HNil] = (t: HNil) => Nil

  implicit def hconsSerializer[H, T <: HList](
    implicit serialH: Lazy[CSVSerializer[H]],
    serialT: CSVSerializer[T]
  ): CSVSerializer[H :: T] =
    (t: H :: T) => serialH.value.serialize(t.head) ::: serialT.serialize(t.tail)

  implicit def genSerializer[T, Repr0](
    implicit gen: Generic.Aux[T, Repr0],
    reprSerializer: Lazy[CSVSerializer[Repr0]]
  ): CSVSerializer[T] = (t: T) => reprSerializer.value.serialize(gen.to(t))
}

import CSVSerializer._

object Ex2Case1 extends App {

  CSVSerializer[Long :: String :: HNil]

  val user = User(1L, "josh", active = true)
  val userCSV = user.toCSV
  println(userCSV)
  assert(userCSV == "1,josh,true")

  println(CSVSerializer[Account].serialize(Account(2L, user)))

  implicitly[User :: HNil]
}







/*

 x define a type class
 x define instances for primitive types
 - define hnil instance
 - define hcons instance
 - define generic instance
 - define fallback implicit
 - Coproduct instances?
 - debugging problem


 */*/
