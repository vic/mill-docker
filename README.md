# mill-docker

[![Build Status](https://travis-ci.org/vic/mill-docker.svg?branch=master)](https://travis-ci.org/vic/mill-docker)

A [Mill][mill] module for building minimal [distroless][distroless] docker images from your java applications.

The generated docker image contains only your application's _assembly_ jar and the java runtime, but no linux
distribution to maintain. That means the image size is small as it can get.

## Usage

The following code comes from the example project [`example/build.sc`][example]:

```scala
// add mill-docker artifact repo
import mill._
interp.repositories() =
  interp.repositories() ++ Seq(coursier.MavenRepository("https://jitpack.io"))

@

import mill._, scalalib._

// import mill-docker
import $ivy.`io.github.vic::mill-docker:0.0.1`, mill.docker._

// let your project extend DockerModule
object hello extends ScalaModule with DockerModule {

  def scalaVersion = "2.12.6"
  
  // the only required tasks are the docker tag and main class to run
  def dockerMain = "example.hello"
  def dockerTag = "hello:latest"

}
```

The default `dockerBaseImage` is `gcr.io/distroless/java:latest`, you can override it if needed or
altogether override the `dockerFile` task that creates the Dockerfile contents. See the `DockerModule.scala`
code if you feel the need.


### Building

The main task added by this module is `dockerBuild`. *Note* if you would like to see the output produced by
the docker command, be sure to use `mill --interactive` mode.

```shell
$ cd example
$ mill --interactive hello.dockerBuild
$ docker run -ti hello:latest world
Hello world
```

### Pushing your docker image

For using the `dockerPush` task, your `dockerTag` task should return the full image tag 
including the repository, for example: `"gcr.io/your-project/app:latest"`.

Don't forget to use `mill --interactive` if you need to look at docker push output.

[mill]: https://www.lihaoyi.com/mill
[distroless]: https://github.com/GoogleContainerTools/distroless
[example]: https://github.com/vic/mill-docker/blob/master/example/build.sc
[jitpack]: https://jitpack.io
