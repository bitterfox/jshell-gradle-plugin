# jshell-gradle-plugin

This gradle plugin helps you to explore your code and dependencies in your gradle project
with in [jshell](https://docs.oracle.com/javase/9/jshell/introduction-jshell.htm) -- the official Java REPL tool.

Hosted on https://plugins.gradle.org/plugin/net.java.openjdk.shinyafox.jshell.gradle.plugin


## Getting started

To use this plugin, add following to your `build.gradle`:

```
buildscript {
  repositories {
    maven {
      url "https://plugins.gradle.org/m2/"
    }
  }
  dependencies {
    classpath "gradle.plugin.net.java.openjdk.shinyafox:jshell-gradle-plugin:1.0.5"
  }
}

apply plugin: "net.java.openjdk.shinyafox.jshell.gradle.plugin"
```

or

```
plugins {
  id "net.java.openjdk.shinyafox.jshell.gradle.plugin" version "1.0.5"
}
```
(for gradle 2.1+)

Task `jshell` is now enabled, which execute jshell with your classes and dependencies after compiling your code.

You need to run the task `jshell` with the options `--no-daemon --console plain`.
Following is an example with gradlew:

```
./gradlew --no-daemon --console plain jshell
```

If you see this warning and the jshell console does not detect your classes:

> :jshell task :classes not found, be sure to compile the project first

Means the `classes` task needed to compile your project before launch `jshell`
does not exist, just append the task needed to compile the project,
some time is the same `classes` task but is not detected in multi-modules
projects, so you need to add it explicitly in the Gradle command:

```
./gradlew --no-daemon --console plain classes jshell
```
