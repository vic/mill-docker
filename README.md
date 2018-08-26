# mill-docker

[![Build Status](https://travis-ci.org/vic/mill-docker.svg?branch=master)](https://travis-ci.org/vic/mill-docker)


A [Mill][mill] module for building minimalist [distroless][distroless] docker images
for your java applications.

## Usage

See the example project buildfile: [`example/build.sc`][example] showcasing a hello world application.

*Note*: When invoking the `dockerBuild` task be sure to use `mill --interactive` in order
to view docker's errors if something goes wrong.

[mill]: https://www.lihaoyi.com/mill
[distroless]: https://github.com/GoogleContainerTools/distroless/tree/master/java
[example]: https://github.com/vic/mill-docker/blob/master/example/build.sc
