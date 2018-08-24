// -*- mode: scala -*- 

import mill._, scalalib._, publish._

object docker extends ScalaModule with PublishModule {

  def scalaVersion = "2.12.6"

  def publishVersion = "0.0.1"

  def artifactName = "mill-docker"

  def pomSettings = PomSettings(
    description = "Docker packaging support for Mill builds",
    organization = "io.github.vic",
    url = "https://github.com/vic/mill-docker",
    licenses = Seq(License.`Apache-2.0`),
    versionControl = VersionControl.github("vic", "mill-docker"),
    developers = Seq(
      Developer("vic", "Victor Borja", "https://github.com/vic")
    )
  )

  def compileIvyDeps = Agg(
    ivy"com.lihaoyi::mill-scalalib:0.2.3",
  )

}
