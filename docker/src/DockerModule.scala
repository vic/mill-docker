package mill.docker

import mill._
import mill.scalalib.{Assembly, JavaModule}

trait DockerModule extends JavaModule {

  def finalMainClass: T[String]
  def assembly: T[PathRef]

  def dockerTag: T[String]
  def dockerMain = T[String] { finalMainClass() }
  def dockerJavaOpts = T[Seq[String]] { Seq() }
  def dockerMainArgs = T[Seq[String]] { Seq() }

  def dockerJar = T[PathRef] {
    Assembly.createAssembly(
      inputPaths = Agg.from(localClasspath().map(_.path)),
      prependShellScript = "" /* no prepend script */,
      base = Some(upstreamAssembly().path),
      assemblyRules = assemblyRules
    )
  }

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

    val file: os.Path = dest / "Dockerfile"
    os.write(file, dockerFile())

    val jar: PathRef = dockerJar()
    os.copy(jar.path, dest/"app.jar")

    os.proc('docker, 'build, "-f", file, "-t", tag, dest).call()
    (jar, file, tag)
  }

  def dockerPush = T {
    val (_, _, tag) = dockerBuild()
    os.proc('docker, 'push, tag).call()
  }

}
