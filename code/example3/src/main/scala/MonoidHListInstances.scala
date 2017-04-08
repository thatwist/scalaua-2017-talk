import shapeless.labelled.{FieldType, field}
import shapeless.{::, HList, HNil}

object MonoidHListInstances {
  /*
    trait Monoid[K] {
      def empty: K
      def combine(x: K, y: K): K
    }*/

  import cats.kernel.Monoid

  implicit val hnilMonoid: Monoid[HNil] = new Monoid[HNil] {
    override def empty: HNil = HNil
    override def combine(x: HNil, y: HNil): HNil = HNil
  }

  // do we derived instance of monoid for
  implicit def hConsMonoid[K, H, T <: HList](
    implicit
    monoidH: Monoid[H],
    monoidT: Monoid[T]
  ): Monoid[FieldType[K, H] :: T] = new Monoid[FieldType[K, H] :: T] {

    override def empty: FieldType[K, H] :: T = field[K](monoidH.empty) :: monoidT.empty

    override def combine(x: FieldType[K, H] :: T, y: FieldType[K, H] :: T): FieldType[K, H] :: T =

      field[K](monoidH.combine(x.head, y.head)) :: monoidT.combine(x.tail, y.tail)
  }
}
