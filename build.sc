// -*- mode: scala -*-

import mill._, os._, scalalib._, publish._
import scala.util.Properties

object meta {
  val crossVersions = Seq("2.13.12")

  implicit val wd: Path = pwd

  def nonEmpty(s: String): Option[String] = s.trim() match {
    case v if v.isEmpty => None
    case v => Some(v)
  }

  val MILL_VERSION = Properties.propOrNull("MILL_VERSION")
  val versionFromEnv = Properties.propOrNone("PUBLISH_VERSION")
  val gitSha = nonEmpty(proc("git", "rev-parse", "--short", "HEAD").call().out.trim())
  val gitTag = nonEmpty(proc("git", "tag", "-l", "-n0", "--points-at", "HEAD").call().out.trim())
  val publishVersion = (versionFromEnv orElse gitTag orElse gitSha).getOrElse("latest")
}

object docker extends Cross[Docker](meta.crossVersions)
trait Docker extends CrossScalaModule with PublishModule {
  def publishVersion = meta.publishVersion

  def artifactName = "mill-docker"

  def pomSettings = PomSettings(
    description = "Dockerize java applications on Mill builds",
    organization = "com.github.vic",
    url = "https://github.com/vic/mill-docker",
    licenses = Seq(License.`Apache-2.0`),
    versionControl = VersionControl.github("vic", "mill-docker"),
    developers = Seq(
      Developer("vic", "Victor Borja", "https://github.com/vic")
    )
  )

  def compileIvyDeps = Agg(
    ivy"com.lihaoyi::mill-scalalib:${meta.MILL_VERSION}"
  )
}
