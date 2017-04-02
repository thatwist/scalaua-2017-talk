package xyz.codefastdieyoung

import org.scalatest.FlatSpec
import xyz.codefastdieyoung.Example3.Migration
import org.scalactic._

class Example3Spec extends FlatSpec {

  "migration" should "migrate" in {

    import cats.instances.all._
    import MonoidHListInstances._
    import Migration._


    val migrated = User(1L, "name1", "email@com", List("2")).migrateTo[Employee]

    assert(migrated === Employee(1L, "", "email@com", 0L))

    println(migrated)
  }
}
