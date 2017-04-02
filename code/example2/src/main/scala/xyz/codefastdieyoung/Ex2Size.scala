package xyz.codefastdieyoung

import shapeless._

object Ex2Size extends App {

  trait Size[H] {
    def apply(h: H): Int
  }

  object Size {
    def apply[H](h: H)(implicit size: Size[H]): Int = size(h)

    implicit val hNilSize: Size[HNil] = (_: HNil) => 0

    implicit def hConsSize[H, T <: HList](implicit size: Size[T]): Size[::[H, T]] = (h: ::[H, T]) => 1 + size(h.tail)

    implicit def productSize[P <: Product, Repr](implicit
      generic: Generic.Aux[P, Repr],
      size: Size[Repr]
    ): Size[P] = (p: P) => size(generic.to(p))
  }

  import Size._

  println(Size((1, "1", true))) // == 3

  println(Size(HNil)) // == 0

  println(Size(1 :: "1" :: true :: HNil)) // == 3

}

/*

 << be prepared with type class and test printlns >>

 - define apply

 - define HNil size

 - define HCons size
 - <AUDITORY> ADVICE WITH generic.AUX?????

 - ?? what else - we can derive instance of this class for all Products (case classes)
 - example (tuple is a Product itself )

 - Java8 functional interfaces -  I think its really great, makes cleaner syntax
 <<<<<  PROBABLY THE FIRST THING I LIKE ABOUT JAVA8  >>>>>

 - preferrable style would be to put implicits into companion object - because this is default scope of where
 */