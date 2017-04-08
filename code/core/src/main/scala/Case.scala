case class User(
  id: Long,
  name: String,
  active: Boolean
)

case class RichUser(
  id: Long,
  name: String,
  role: Role
)

case class Employee(
  id: Long,
  name: String,
  personId: String,
  email: String,
  cardNumber: Long
)

case class Role(
  id: Long,
  name: String
)

case class Account(
  id: Long,
  user: User
)