package mill.docker

import mill._
import ammonite.ops._

trait DockerModule extends Module {

  def dockerMain: T[String]
  def dockerTag: T[String]
  def assembly: T[PathRef]

  def dockerBaseImage: T[String] = "gcr.io/distroless/java:latest"

  def dockerContent:T[String] = s"""
    |FROM ${dockerBaseImage()}
    |COPY app.jar /app.jar
    |ENTRYPOINT ["java", "-cp", "/app.jar", "${dockerMain()}"]
    |CMD []
    """.stripMargin

  def dockerFile = T {
    val dockerFile = T.ctx().dest / "Dockerfile"
    write(dockerFile, dockerContent())
    PathRef(dockerFile)
  }

  def dockerBuild = T {
    import ImplicitWd._
    import pprint._
    val dest = T.ctx().dest
    val docker:PathRef = dockerFile()
    val singleJar:PathRef = assembly()
    cp(singleJar.path, dest/"app.jar")
    %('docker, 'build, "-f", docker.path, "-t", dockerTag(), dest)
    (singleJar, docker)
  }

}
