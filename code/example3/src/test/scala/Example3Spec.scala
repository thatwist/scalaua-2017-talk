import Example3.Migration
import org.scalatest.FlatSpec

class Example3Spec extends FlatSpec {

  "migration" should "migrate" in {

    import Migration._


    val migrated = User(1L, "name1", "email@com", List("2")).migrateTo[Employee]

    assert(migrated === Employee(1L, "", "email@com", 0L))

    Nil.sum
    println(migrated)
  }
}
