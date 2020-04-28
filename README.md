# mill-docker

[![Jitpack](https://jitpack.io/v/vic/mill-docker.svg)](https://jitpack.io/#vic/mill-docker)
![Main workflow](https://github.com/vic/mill-docker/workflows/Main%20workflow/badge.svg)

A [Mill][mill] module for building minimal [distroless][distroless] docker images from your java applications.

The generated docker image contains only your application's _assembly_ jar and the java runtime, but no linux
distribution to maintain. That means the image size is as small as it can get without actually turning your app into a native binary (with graalvm for example).

## Usage

See the annotated example buildfile at [`example/build.sc`][example]:

### Building

The main task added by this module is `dockerBuild`. *Note* if you would like to see the output produced by
the docker command, be sure to use `mill --interactive` mode.

```shell
$ cd example
$ mill --interactive hello.dockerBuild
$ docker run -ti hello:latest world
Hello world
```

[mill]: https://www.lihaoyi.com/mill
[distroless]: https://github.com/GoogleContainerTools/distroless
[example]: https://github.com/vic/mill-docker/blob/master/example/build.sc
[jitpack]: https://jitpack.io
