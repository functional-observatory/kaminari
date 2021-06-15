name := "kaminari"
ThisBuild / scalaVersion := "3.0.0"

// PROJECTS

lazy val global = project
  .in(file("."))
  .settings(settings)
  .disablePlugins(AssemblyPlugin)
  .aggregate(
    ifscCli,
    notionPurge
  )

lazy val ifscCli = Project(id = "ifsc-cli", base = file("ifsc-cli"))
  .settings(
    name := "ifsc-cli",
    settings,
    assemblySettings,
    libraryDependencies ++= Seq(
      dependencies.requests,
      dependencies.upickle
    )
  )
  .enablePlugins(PackPlugin)

lazy val notionPurge = Project(id = "notion-purge", base = file("notion-purge"))
  .settings(
    name := "notion-purge",
    settings,
    assemblySettings,
    libraryDependencies ++= Seq(
      dependencies.requests,
      dependencies.upickle
    )
  )
  .enablePlugins(PackPlugin)

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
  scalacOptions ++= compilerOptions,
  packMain := Map(name.value -> "main")
)

lazy val settings =
  commonSettings
