import org.gradle.accessors.dm.LibrariesForLibs

plugins {
    java
}

group = "ca.terrylockett.aoc"
version = "1.0-SNAPSHOT"


val libs = the<LibrariesForLibs>()

dependencies {
    testImplementation(libs.junitJupiterCore)
}

tasks.named<Test>("test") {
    useJUnitPlatform()
}

repositories {
    mavenCentral()
}
