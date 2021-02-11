// -*- mode: scala -*-

import mill._, scalalib._

import $ivy.`com.github.vic::mill-docker:latest`
import mill.docker._


object hello extends ScalaModule with DockerModule {

  override def scalaVersion = scala.util.Properties.versionNumberString
  def dockerTag = "hello:latest"

  override def finalMainClass = T("example.hello")

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
