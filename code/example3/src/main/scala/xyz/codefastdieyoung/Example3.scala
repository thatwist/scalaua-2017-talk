package xyz.codefastdieyoung

import shapeless.{HList, LabelledGeneric}
import cats.Monoid
import shapeless.ops.hlist.{Align, Diff, Intersection, Prepend}

object Example3 extends App {

  trait Migration[A, B] {
    def apply(a: A): B
  }

  object Migration {

    implicit class MigrateTo[A](a: A) {
      def migrateTo[B](implicit migration: Migration[A, B]): B = migration.apply(a)
    }

    implicit def genericMigration[A, B,
      ReprA <: HList,
      ReprB <: HList,
      Intersection <: HList,
      Difference <: HList,
      Concatenated <: HList](
      implicit
      aGeneric: LabelledGeneric.Aux[A, ReprA],
      bGeneric: LabelledGeneric.Aux[B, ReprB],
      intersection: Intersection.Aux[ReprA, ReprB, Intersection],
      diff: Diff.Aux[ReprB, Intersection, Difference],
      monoid: Monoid[Difference],
      concat: Prepend.Aux[Difference, Intersection, Concatenated],
      align: Align[Concatenated, ReprB]
    ): Migration[A, B] = { a =>
      bGeneric.from(align(concat(monoid.empty, intersection(aGeneric.to(a)))))
    }
  }
}
