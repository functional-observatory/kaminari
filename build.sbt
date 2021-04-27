ThisBuild / scalaVersion := "2.13.4"
ThisBuild / version := "0.1.0-alpha.1"
ThisBuild / organizationName := "ifsc-cli"

lazy val root = (project in file("."))
  .settings(
    name := "ifsc-cli",
    mainClass in assembly := Some("ifsc.Main"),
    libraryDependencies ++= Seq(
      "com.lihaoyi" %% "requests" % "0.6.5",
      "com.lihaoyi" %% "upickle" % "1.3.8"
    )
  )
