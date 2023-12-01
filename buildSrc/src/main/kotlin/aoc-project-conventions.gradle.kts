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
    java {
        eclipse()
    }
    kotlin {
        ktlint()
    }
}