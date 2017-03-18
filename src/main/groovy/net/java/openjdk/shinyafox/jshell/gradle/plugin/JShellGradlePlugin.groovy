package net.java.openjdk.shinyafox.jshell.gradle.plugin

import java.io.File
import jdk.internal.jshell.tool.JShellToolProvider

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.compile.JavaCompile

import java.nio.file.Files
import java.nio.file.Paths

class JShellGradlePlugin implements Plugin<Project> {

    @Override
    void apply(Project project) {
        project.tasks.create('jshell')
        .dependsOn("classes")
        .doLast {
            def path
            project.tasks.withType(JavaCompile) {
                path = classpath.asPath
            }

            Thread.currentThread().setContextClassLoader(ClassLoader.getSystemClassLoader()) // promote class loader
            JShellToolProvider.main((String[])["--class-path", path].toArray());
        }
    }

}
