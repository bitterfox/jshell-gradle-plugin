package net.java.openjdk.shinyafox.jshell.gradle.plugin

import jdk.jshell.tool.JavaShellToolBuilder
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.JavaExec

class JShellGradlePlugin implements Plugin<Project> {

    @Override
    void apply(Project project) {
        def jshellTask = project.tasks.create('jshell')
        def classesTask = project.tasks.find { it.name == "classes" }
        if (classesTask) {
            jshellTask.dependsOn classesTask
        } else {
            // Some multi-module projects may not have the :classes task
            jshellTask.logger.warn ":jshell task :classes not found, be sure to compile the project first"
        }
        jshellTask.doLast {
            Set pathSet = []
            project.tasks.withType(JavaExec) {
                pathSet.addAll(classpath.findAll{ it.exists() })
            }
            project.subprojects.each {
                it.tasks.withType(JavaExec) {
                    pathSet.addAll(classpath.findAll{ it.exists() })
                }
            }
            Thread.currentThread().setContextClassLoader(ClassLoader.getSystemClassLoader()) // promote class loader
            def path = pathSet.join(System.properties['os.name'].toLowerCase().contains('windows') ? ';' : ':')
            jshellTask.logger.debug(":jshell executing with --class-path \"{}\"", path)
            JavaShellToolBuilder.builder().run((String[])["--class-path", path].toArray())
        }
    }
}
