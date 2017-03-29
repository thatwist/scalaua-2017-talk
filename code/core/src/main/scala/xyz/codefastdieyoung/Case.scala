package xyz.codefastdieyoung

case class User(
  id: Long,
  name: String,
  email: String,
  roles: List[String]
)

case class MinimalUser(
  id: Long,
  name: String,
  email: String
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

case class RichUser(
  id: Long,
  name: String,
  email: String,
  role: Role
)