object Ex1Case1 extends App {

  trait ToString[A] {

    def apply(a: A): String
  }

  object ToString {

    implicit class Any2String[A](a: A) {
      def asString(implicit str: ToString[A]): String = str(a)
    }

    implicit val longToString: ToString[Long] = (a: Long) => a.toString

    implicit val strToString: ToString[String] = (a: String) => a.toString

    /* what about generic data types? */
    implicit def listToString[A: ToString]: ToString[List[A]] = (a: List[A]) => a.map(_.asString).mkString(", ")

    /* what about ADTs? */
    implicit def userToString[T <: User]: ToString[T] = (u: User) => s"User(${u.id.asString})....."


  }

}
