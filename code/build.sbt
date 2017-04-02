name := "code"

lazy val commonSettings = Seq(
  organization := "xyz.codefastdieyoung",
  version := "1.0",
  scalaVersion := "2.12.1",
  scalaOrganization := "org.typelevel",
  scalacOptions ++= Seq("-Yliteral-types", "-Ykind-polymorphism", "-Yinduction-heuristics")
)

lazy val dependencies = Seq(
  libraryDependencies ++= Seq(
    "com.chuusai" %% "shapeless" % "2.3.2",
    "org.typelevel" %% "cats" % "0.9.0",
    "org.scalatest" %% "scalatest" % "3.0.1" % "test",
    "org.scalactic" %% "scalactic" % "3.0.1" % "test"
  )
)

lazy val core = project.in(file("core")).settings(commonSettings)

lazy val example1 = project.dependsOn(core)
  .settings(Seq(
    mainClass in Compile := Some("xyz.codefastdieyoung.Example1")
  ) ++ commonSettings ++ dependencies)

lazy val example2 = project.dependsOn(core)
  .settings(Seq(
    mainClass in (Compile, run) := Some("xyz.codefastdieyoung.Ex2Case1")
  ) ++ commonSettings ++ dependencies)

lazy val example3 = project.dependsOn(core)
  .settings(Seq(
    mainClass in (Compile, run) := Some("xyz.codefastdieyoung.Example3")
  ) ++ commonSettings ++ dependencies)

lazy val example4 = project.dependsOn(core)
  .settings(Seq(
    mainClass in Compile := Some("xyz.codefastdieyoung.Example4")
  ) ++ commonSettings ++ dependencies)

lazy val root = (project in file("."))
  .aggregate(core, example1, example2, example3, example4)
  .settings(Seq(
    aggregate in compile := false
  ) ++ commonSettings)