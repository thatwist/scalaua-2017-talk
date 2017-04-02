package xyz.codefastdieyoung

object Ex2Second {
  import shapeless._

  trait Second[H <: HList] {
    type Out
    def apply(h: H): Out
  }

  object Second {

    def apply[H <: HList](h: H)(implicit second: Second[H]): second.Out = second(h)

    implicit def hdoubleSecond[A, B, H <: A :: B :: HNil]: Second[H] = new Second[H] {

      override type Out = B

      override def apply(h: H): Out = h.tail.head
    }

    implicit def hConsSecond[A, B <: HList, H <: A :: B](
      implicit bSecond: Second[B]
    ): Second[H] = new Second[H] {

      override type Out = bSecond.Out

      override def apply(h: H): Out = bSecond(h.tail)
    }
  }


}
