// -*- mode: scala -*-

import mill._, scalalib._, publish._
import ammonite.ops._
import scala.util.Properties

object meta {
  val crossVersions = Seq("2.13.2", "2.12.11")

  implicit val wd: os.Path = os.pwd

  def nonEmpty(s: String): Option[String] = s.trim match {
    case v if v.isEmpty => None
    case v => Some(v)
  }

  val versionFromEnv = Properties.propOrNone("PUBLISH_VERSION")
  val gitSha = nonEmpty(%%("git", "rev-parse", "--short", "HEAD").out.trim)
  val gitTag = nonEmpty(%%("git", "tag", "-l", "-n0", "--points-at", "HEAD").out.trim)
  val publishVersion = (versionFromEnv orElse gitTag orElse gitSha).getOrElse("latest")
}

object docker extends Cross[Docker](meta.crossVersions: _*)
class Docker(val crossScalaVersion: String) extends CrossScalaModule with PublishModule {
  def publishVersion = meta.publishVersion

  def artifactName = "mill-docker"

  def pomSettings = PomSettings(
    description = "Dockerize java applications on Mill builds",
    organization = "io.github.vic",
    url = "https://github.com/vic/mill-docker",
    licenses = Seq(License.`Apache-2.0`),
    versionControl = VersionControl.github("vic", "mill-docker"),
    developers = Seq(
      Developer("vic", "Victor Borja", "https://github.com/vic")
    )
  )

  def compileIvyDeps = Agg(
    ivy"com.lihaoyi::mill-scalalib:latest.stable"
  )
}
