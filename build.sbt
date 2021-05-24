lazy val root = project
  .in(file("."))
  .settings(
    mainClass in assembly := Some("Main"),
    assemblyJarName in assembly := "notion-purge.jar",
    name := "scala3-simple",
    version := "0.1.0",
    scalaVersion := "2.13.6",
    libraryDependencies ++= Seq(
      "com.lihaoyi" %% "requests" % "0.6.9",
      "com.lihaoyi" %% "upickle" % "1.3.15"
    )
  )
