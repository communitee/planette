
name := "planette"

organization := "communitee"

version := "0.1.0-SNAPSHOT"

scalaVersion := "2.12.1"

libraryDependencies ++= Seq(

"com.typesafe.scala-logging" %% "scala-logging" % "3.5.0",
  "ch.qos.logback" % "logback-classic" % "1.1.2",
  //"com.mayo" % "what-is_2.12" % "0.1.0-SNAPSHOT", until we'll really need it
  "org.scalaz" % "scalaz-core_2.12" % "7.3.0-M10",
  "org.specs2" %% "specs2-core" % "3.8.6" % Test,
  "com.chuusai" %% "shapeless" % "2.3.2"
)