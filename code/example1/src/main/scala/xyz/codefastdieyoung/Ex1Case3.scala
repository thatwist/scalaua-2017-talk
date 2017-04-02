package xyz.codefastdieyoung

import scala.annotation.inductive

object Ex1Case3 extends App {

  @inductive
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

    implicit def listToString[A](implicit aToString: ToString[A]): ToString[List[A]] = (a: List[A]) => a.map(_.asString).mkString("[", ", ", "]")

    implicit val userToString: ToString[User] = (user: User) => s"User id${user.id.asString}, name=${user.name.asString}"
  }

  import ToString._

  println(User(1L, "asd", "asd", Nil).asString)

  import shapeless._

  // the[ToString[Long]]

}
