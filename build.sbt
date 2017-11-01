name := "akka-http-example"

version := "0.1-SNAPSHOT"

scalaVersion := "2.11.11"

val akkaVersion = "2.4.16"
val akkaHttpVersion = "10.0.3"
val slf4jVersion = "1.7.25"
val sprayJsonVersion = "1.3.3"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-http-spray-json" % akkaHttpVersion,
  "com.typesafe.akka" %% "akka-http" % akkaHttpVersion,
  "com.typesafe.akka" %% "akka-stream" % akkaVersion,
  "com.typesafe.akka" %% "akka-actor" % akkaVersion,
  "com.typesafe.akka" %% "akka-http-testkit" % akkaHttpVersion % "test",
  "io.spray" %% "spray-json" % sprayJsonVersion,
  "org.slf4j" % "slf4j-simple" % slf4jVersion
)

lazy val copyJar = taskKey[Unit]("Test copy jar file")
copyJar := {
   val folder = new File("target/lib")
   (managedClasspath in Compile).value.files.foreach { f =>
     IO.copyFile(f, folder / f.getName)
   }
   val jarFile = sbt.Keys.`package`.in(Compile, packageBin).value
   IO.copyFile(jarFile, new File("target") / jarFile.getName)
}
