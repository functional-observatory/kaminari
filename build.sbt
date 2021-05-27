ThisBuild / scalaVersion := "2.13.4"
ThisBuild / version := "0.1.0-alpha.1"
ThisBuild / organizationName := "ifsc-cli"

enablePlugins(DockerPlugin)

lazy val root = (project in file("."))
  .settings(
    name := "ifsc-cli",
    mainClass in assembly := Some("ifsc.Main"),
    assemblyJarName in assembly := "ifsc.jar",
    libraryDependencies ++= Seq(
      "com.lihaoyi" %% "requests" % "0.6.5",
      "com.lihaoyi" %% "upickle" % "1.3.8"
    )
  )

docker / dockerfile := {
  // The assembly task generates a fat JAR file
  val artifact: File = assembly.value
  val artifactTargetPath = s"/app/${artifact.name}"

  new Dockerfile {
    from("openjdk:8-jre")
    add(artifact, artifactTargetPath)
    entryPoint("java", "-jar", artifactTargetPath)
  }
}
