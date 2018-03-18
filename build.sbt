name := """play-project1"""
organization := "knoldus"
version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.12.3"

libraryDependencies  += guice
libraryDependencies += ehcache
libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "3.1.2" % Test


// Adds additional packages into Twirl
//TwirlKeys.templateImports += "knoldus.controllers._"

// Adds additional packages into conf/routes
// play.sbt.routes.RoutesKeys.routesImport += "knoldus.binders._"
