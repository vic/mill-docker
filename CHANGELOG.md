# Changelog

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## 0.7.0

- Upgrade to mill 0.11.6
- Upgrade to scala 2.13.12

## 0.6.0

- Upgrade to mill 0.10.0
- Upgrade to scala 2.13.8

## 0.5.4

- Upgrade to mill 0.9.7

## 0.5.1

- Upgrade to mill 0.8.0

## [0.5.0]

### Changed
- Change to CrossScalaModule for 2.12 and 2.13
- Update ci script to use coursier

## [0.4.0]

### Changed
- Upgrade to Mill 0.7.x and Scala 2.12.11

## [0.2.0] - 2019-09-05

### Changed
- Upgrade to Mill 0.5.1 and Scala 2.12.9

## [0.1.0] - 2019-06-27

### Changed
- Upgrade to Mill 0.4.1 and Scala 2.12.8

## [0.0.7] - 2019-04-09

### Changed
- Upgrade to Mill 0.3.5 and compile with scala 2.12.8

## [0.0.6] - 2018-12-05

### Changed
- Dont use mill's `assembly` (as sometimes it generated an executable) instead make `dockerJar` create its own fat jar. Fixes #5.

## [0.0.5] - 2018-11-20

### Added
- This CHANGELOG file

### Changed
- Upgrade to Mill 0.3.5
- Use `os-lib` instead of `ammonite.ops._`
- Make build time versions easier to manage.
  Scala and mill versions are managed from `.tool-versions` file.
  The VERSION specifies this project's version number.

[Unreleased]: https://github.com/vic/mill-docker/compare/0.4.0...HEAD
[0.2.0]: https://github.com/vic/mill-docker/compare/0.1.0...0.2.0
[0.1.0]: https://github.com/vic/mill-docker/compare/0.0.7...0.1.0
[0.0.7]: https://github.com/vic/mill-docker/compare/0.0.6...0.0.7
[0.0.6]: https://github.com/vic/mill-docker/compare/0.0.5...0.0.6
[0.0.5]: https://github.com/vic/mill-docker/compare/0.0.4...0.0.5
