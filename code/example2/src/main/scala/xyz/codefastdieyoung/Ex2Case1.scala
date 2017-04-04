package xyz.codefastdieyoung

import shapeless.{::, Generic, HList, HNil, Lazy}

object Ex2Case1 extends App {

  trait Encoder[T] {
    def apply(t: T): String
  }

  implicit val intEncoder: Encoder[Int] = { _.toString }
  implicit val strEncoder: Encoder[String] = { _.toString }
  implicit val longEncoder: Encoder[Long] = { _.toString }

  implicit def genericEncoder[T, Repr](
    implicit
    genT: Generic.Aux[T, Repr],
    reprEnc: Encoder[Repr]
  ) = new Encoder[T] {
    override def apply(t: T): String = reprEnc(genT.to(t))
  }

  implicit def hNilEncoder: Encoder[HNil] = (t: HNil) => ""

  implicit def HConsEncoder[H, T <: HList](implicit
    hEncoder: Encoder[H],
    tailEncoder: Encoder[T]
  ): Encoder[H :: T] = { t =>
    s"${hEncoder(t.head)}, ${tailEncoder(t.tail)}"
  }

  println(implicitly[Encoder[Account]].apply(Account(1L, User(1L, "test", true))))
}
