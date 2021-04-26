ThisBuild / scalaVersion := "2.13.4"
ThisBuild / version := "0.1.0-SNAPSHOT"
ThisBuild / organizationName := "ifsc-cli"

lazy val root = (project in file("."))
  .settings(
    name := "IFSC Cli",
    libraryDependencies ++= Seq(
      "com.lihaoyi" %% "requests" % "0.6.5",
      "com.lihaoyi" %% "upickle" % "1.3.8"
    )
  )
