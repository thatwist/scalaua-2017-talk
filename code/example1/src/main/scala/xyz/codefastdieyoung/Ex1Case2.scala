package xyz.codefastdieyoung

import shapeless.Generic

object Ex1Case2 {

  trait ToString[A] {

    def apply(a: A): String
  }

  object ToString {

    implicit class Any2String[A](a: A) {
      def asString(implicit str: ToString[A]): String = str(a)
    }

    implicit val stringToString: ToString[String] = (a: String) => a

    implicit val intToString: ToString[Int] = (a: Int) => a.toString

    implicit val longToString: ToString[Long] = (a: Long) => a.toString

    implicit def listToString[A: ToString]: ToString[List[A]] = (a: List[A]) => a.map(_.asString).mkString("[", ", ", "]")
  }

  import ToString._

  // println(User(1L, "asd", "asd", Nil).asString)

  // the[ToString[Long]]

}
