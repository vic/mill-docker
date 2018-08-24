# mill-docker

A [mill](Mill) module for building minimalist [distroless](distroless) docker images
for your java applications.

Images 

## Usage

Let your project extend `DockerModule`, define a `dockerMain` and `dockerTag`.

See the example project buildfile: [`example/build.sc`](example)

Then invoke the `dockerBuild` task on your project.

[mill]: https://www.lihaoyi.com/mill
[distroless]: https://github.com/GoogleContainerTools/distroless/tree/master/java
[example]: https://github.com/vic/mill-docker/blob/master/example/build.sc
