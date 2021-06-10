name := "kaminari"
ThisBuild / scalaVersion := "2.13.6"

// PROJECTS

lazy val global = project
  .in(file("."))
  .settings(settings)
  .disablePlugins(AssemblyPlugin)
  .aggregate(
    ifsc,
    notionPurge
  )

lazy val ifsc = (project in file("ifsc-cli"))
  .settings(
    name := "ifsc-cli",
    settings,
    assemblySettings,
    libraryDependencies ++= Seq(
      dependencies.requests,
      dependencies.upickle
    )
  )

lazy val notionPurge = (project in file("notion-purge"))
  .settings(
    name := "notion-purge",
    settings,
    assemblySettings,
    libraryDependencies ++= Seq(
      dependencies.requests,
      dependencies.upickle
    )
  )

// DEPENDENCIES

lazy val dependencies =
  new {
    val requests = "com.lihaoyi" %% "requests" % "0.6.9"
    val upickle = "com.lihaoyi" %% "upickle" % "1.3.15"
  }

// SETTINGS

lazy val assemblySettings = Seq(
  assembly / assemblyJarName := name.value + ".jar",
  assembly / assemblyOutputPath := file("target/jars/" + name.value + ".jar")
)

lazy val compilerOptions = Seq(
  "-encoding",
  "utf8"
)

lazy val commonSettings = Seq(
  scalacOptions ++= compilerOptions
)

lazy val settings =
  commonSettings
