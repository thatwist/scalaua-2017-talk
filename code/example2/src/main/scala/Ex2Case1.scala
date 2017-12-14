import shapeless._

case class Account(id: Long, user: User)

case class User(
  id: Long,
  name: String,
  active: Boolean,
  score: Double = 1.0d
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

  implicit val booleanSerializer: CSVSerializer[Boolean] =
    (t: Boolean) => List(t.toString)

  implicit val stringSerializer: CSVSerializer[String] = new CSVSerializer[String] {
    override def serialize(t: String): List[String] = List(t)
  }

  implicit val doubleSerializer: CSVSerializer[Double] = new CSVSerializer[Double] {
    override def serialize(t: Double): List[String] = List("%.2f".format(t))
  }

  implicit val hnilSerializer: CSVSerializer[HNil] = (t: HNil) => Nil

  /* implicit def hConsSerializer1[H, T <: HList](
   *   implicit hS: CSVSerializer[H],
   *   tS: CSVSerializer[T]
   * ): CSVSerializer[H :: T] = (t: H :: T) =>
   *   hS.serialize(t.head) ::: tS.serialize(t.tail) */

  implicit def hConsSserializer[H, T <: HList](
    implicit hS: Lazy[CSVSerializer[H]],
    tS: CSVSerializer[T]
  ): CSVSerializer[H :: T] =
    (h: H :: T) => hS.value.serialize(h.head) ::: tS.serialize(h.tail)

  implicit def genSerializer[T, Repr0](
    implicit gen: Lazy[Generic.Aux[T, Repr0]],
    serializer: CSVSerializer[Repr0]
  ): CSVSerializer[T] = (t: T) => serializer.serialize(gen.value.to(t))
}
import CSVSerializer._

object Ex2Case1 extends App {


  val user = User(1L, "josh", active = true)

  println(user.toCSV)

  Account(1L, user)
}
