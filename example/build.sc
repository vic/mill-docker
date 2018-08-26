// -*- mode: scala -*-

// add mill-docker artifact repo
import mill._
interp.repositories() =
  interp.repositories() ++ Seq(coursier.MavenRepository("https://jitpack.io"))

@

import mill._, scalalib._
import $ivy.`io.github.vic::mill-docker:0.0.2`, mill.docker._


object hello extends ScalaModule with DockerModule {

  def scalaVersion = "2.12.6"
  def dockerMain = "example.hello"
  def dockerTag = "hello:latest"

}
