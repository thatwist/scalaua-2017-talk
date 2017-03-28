name := "code"

version := "1.0"

scalaVersion := "2.12.1"
scalaOrganization in ThisBuild := "org.typelevel"

val core = project.settings(
  libraryDependencies ++= Seq(
    "com.chuusai" %% "shapeless" % "2.3.2",
    "org.typelevel" %% "cats" % "0.9.0"
  )
)

val example1 = project.dependsOn(core)
val example2 = project.dependsOn(core)
val example3 = project.dependsOn(core)
val example4 = project.dependsOn(core)
