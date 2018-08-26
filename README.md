# mill-docker

[![Build Status](https://travis-ci.org/vic/mill-docker.svg?branch=master)](https://travis-ci.org/vic/mill-docker)

A [Mill][mill] module for building minimal [distroless][distroless] docker images from your java applications.

The generated docker image contains only your application's _assembly_ jar and the java runtime, but no linux
distribution to maintain. That means the image size is small as it can get.

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
