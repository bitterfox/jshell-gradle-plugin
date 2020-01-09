# jshell-gradle-plugin
This gradle plugin helps you to explore your code and dependency in your gradle project with in jshell -- the official Java REPL tool.

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

Currently we need to run the task with some hacks for JDK9.
Add a path for your jdk9 to `JAVA_HOME` and some options to JAVA_OPTS, and run task `jshell` with `--no-daemon --console plain` for gradle options.
Following is an example with gradlew:
```
JAVA_HOME=/path/to/your/jdk9 \
JAVA_OPTS="--add-exports jdk.jshell/jdk.internal.jshell.tool=ALL-UNNAMED --add-opens java.base/java.lang=ALL-UNNAMED"\
./gradlew --no-daemon --console plain jshell
```
