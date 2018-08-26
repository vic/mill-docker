package mill.docker

import mill._
import ammonite.ops._
import ImplicitWd._
import pprint._

trait DockerModule extends Module {

  def finalMainClass: T[String]
  def assembly: T[PathRef]

  def dockerTag: T[String]
  def dockerJar = T[PathRef] { assembly() }
  def dockerMain = T[String] { finalMainClass() }
  def dockerJavaOpts = T[Seq[String]] { Seq() }
  def dockerMainArgs = T[Seq[String]] { Seq() }

  def dockerBaseImage: T[String] = "gcr.io/distroless/java:latest"

  def dockerFile: T[String] = s"""
    |FROM ${dockerBaseImage()}
    |COPY app.jar /app.jar
    |ENV JAVA_OPTS ""
    |ENTRYPOINT ["java", "-cp", "/app.jar", ${joinEscaped(dockerJavaOpts(), " ,")} "${dockerMain()}"]
    |CMD [${joinEscaped(dockerMainArgs())}]
    """.stripMargin


  private def joinEscaped(strings:Seq[String], trailing: String = ""):String = {
    if (strings.isEmpty) {
      ""
    } else {
      strings.mkString("\"", "\", \"", "\"") ++ trailing
    }
  }

  def dockerBuild = T {
    val dest = T.ctx().dest
    val tag = dockerTag()

    val file:Path = dest / "Dockerfile"
    write(file, dockerFile())

    val jar:PathRef = dockerJar()
    cp(jar.path, dest/"app.jar")

    %('docker, 'build, "-f", file, "-t", tag, dest)
    (jar, file, tag)
  }

  def dockerPush = T {
    val (_, _, tag) = dockerBuild()
    %('docker, 'push, tag)
  }

}
