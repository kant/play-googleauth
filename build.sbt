import ReleaseTransformations._
import Dependencies._

name               := "play-googleauth"

organization       := "com.gu"

scalaVersion       := "2.12.8"

crossScalaVersions := Seq(scalaVersion.value)

resolvers += Resolver.typesafeIvyRepo("releases")

libraryDependencies ++= Seq(
  play % "provided",
  playWS % "provided",
  "com.gu.play-secret-rotation" %% "core" % "0.15",
  "org.typelevel" %% "cats-core" % "1.0.1",
  commonsCodec,
  playTest % "test",
  "org.scalatest" %% "scalatest" % "3.0.3" % "test"
) ++ googleDirectoryAPI

scalacOptions ++= Seq("-feature", "-deprecation")

description        := "Simple Google authentication module for Play 2"

licenses := Seq("Apache V2" -> url("http://www.apache.org/licenses/LICENSE-2.0.html"))

scmInfo := Some(ScmInfo(
  url("https://github.com/guardian/play-googleauth"),
  "scm:git:git@github.com:guardian/play-googleauth.git"
))

publishTo := sonatypePublishTo.value

pomExtra := {
  <url>https://github.com/guardian/play-googleauth</url>
  <developers>
    <developer>
      <id>sihil</id>
      <name>Simon Hildrew</name>
      <url>https://github.com/sihil</url>
    </developer>
  </developers>
}

releaseCrossBuild := true

releasePublishArtifactsAction := PgpKeys.publishSigned.value

releaseProcess := Seq(
  checkSnapshotDependencies,
  inquireVersions,
  runClean,
  runTest,
  setReleaseVersion,
  commitReleaseVersion,
  tagRelease,
  publishArtifacts,
  setNextVersion,
  commitNextVersion,
  releaseStepCommand("sonatypeReleaseAll"),
  pushChanges
)
