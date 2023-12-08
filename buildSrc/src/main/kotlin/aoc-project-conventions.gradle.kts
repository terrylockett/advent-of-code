import org.gradle.accessors.dm.LibrariesForLibs

plugins {
    application
    com.diffplug.spotless
    kotlin
}

val libs = the<LibrariesForLibs>()

dependencies {
    implementation(project(":common:input-file-finder"))
    testImplementation(libs.junitJupiterCore)
}

repositories {
    mavenCentral()
}

tasks.named<Test>("test") {
    useJUnitPlatform()
}

spotless {
    ratchetFrom("origin/main")
    java {
        eclipse()
    }
    kotlin {
        ktlint()
                .editorConfigOverride(mapOf(
                        "indent_size" to "4", 
                        "parameter-wrapping" to false))
                .userData(mapOf(
                        "argument-list-wrapping" to "false"))
        indentWithTabs()
    }
    kotlinGradle {
        target("*.gradle.kts")
        ktlint()
    }
}