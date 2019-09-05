// -*- mode: scala -*-

// add mill-docker artifact repo
import mill._
interp.repositories() =
  interp.repositories() ++ Seq(coursier.MavenRepository("https://jitpack.io"))

@

import mill._, scalalib._

// import mill-docker
import $ivy.`io.github.vic::mill-docker:0.2.0`, mill.docker._


object hello extends ScalaModule with DockerModule {

  def scalaVersion = "2.12.8"
  def dockerTag = "hello:latest"

  // optionally provide default arguments to your program
  // def dockerMainArgs = Seq("world")

  // optionally provide options for the java executable
  // def dockerJavaOpts = Seq("-esa")

  // explicitly define an explicit mainClass, otherwise its discovered from your sources
  // def dockerMain = "example.hello"

  // explictitly set the path of your application jar, by default it's the one generated
  // by the `assembly` task
  // def dockerJar = T[PathRef] { assembly() }

  // explictitly set a base docker image
  // def dockerBaseImage: T[String] = "gcr.io/distroless/java:latest"

  // altogether set the Dockerfile content
  // def dockerFile: T[String] = { "FROM bla:bla" }

}
