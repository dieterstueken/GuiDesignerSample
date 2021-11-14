import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project

class GuiBuilderPlugin implements Plugin<Project> {

    def version = '212.5080.55'

    void apply(Project project) {

        project.repositories {
            mavenCentral()
            maven { url = 'https://www.jetbrains.com/intellij-repository/releases' }
            maven { url = 'https://cache-redirector.jetbrains.com/intellij-dependencies' }
        }

        project.configurations  {
            instrument
        }

        project.dependencies {
            instrument "com.jetbrains.intellij.java:java-compiler-ant-tasks:$version"
            runtimeOnly "com.jetbrains.intellij.java:java-gui-forms-rt:$version"
        }

        project.compileJava {

            doLast {
                ant.taskdef(
                        name: 'instrument',
                        classname: 'com.intellij.ant.InstrumentIdeaExtensions',
                        classpath:  project.configurations.instrument.asPath
                )
                ant.instrument(
                        srcdir: project.sourceSets.main.java.srcDirs.join(':'),
                        classpath: project.sourceSets.main.compileClasspath.asPath,
                        destdir: project.sourceSets.main.output.classesDirs[0],
                        source: JavaVersion.VERSION_11,
                        target: JavaVersion.VERSION_11,
                        includeAntRuntime: false)

            }
        }
    }
}