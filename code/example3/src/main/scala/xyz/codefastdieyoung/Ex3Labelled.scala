package xyz.codefastdieyoung

import shapeless._
import shapeless.labelled._

object Ex3Labelled extends App {


  import shapeless.syntax.singleton._

  val l = ("id" ->> 1) :: HNil

  val user =
      ("id" ->> 1) ::
      ("name" ->> "Josh") ::
      ("active" ->> true) ::
      ("role" ->> "admin") :: HNil

  val i: Witness.`42`.T = 42.narrow

  val i42: 42 = 42

  val k: 41 = 41

  // type nameT = Witness.`'name`.T

  // type User = ('id ->> Long) :: HNil
}
