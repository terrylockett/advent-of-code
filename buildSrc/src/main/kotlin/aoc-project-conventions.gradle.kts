import org.gradle.accessors.dm.LibrariesForLibs
import org.jetbrains.kotlin.gradle.internal.ensureParentDirsCreated
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse.BodyHandlers

plugins {
    application
    com.diffplug.spotless
    kotlin
    me.champeau.jmh
}

jmh {
    benchmarkMode = listOf("SingleShotTime")
    failOnError = true
    timeUnit = "ms"
    forceGC = true
    fork = 0
}
//Always rerun bench
tasks.getByName("jmh").outputs.upToDateWhen { false }

val libs = the<LibrariesForLibs>()
dependencies {
    implementation(project(":common"))
    jmh(project(":common"))
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


tasks.register<Delete>("cleanPuzzleInput") {
    group = "Advent of Code"
    delete(project.projectDir.path + "/src/main/resources/input.txt")
}

val downloadPuzzleInputTask: TaskProvider<Task> = tasks.register("downloadPuzzleInput") {
    group = "Advent of Code"

    val puzzleInputFilePath = project.projectDir.path + "/src/main/resources/input.txt"
    val puzzleInputFile = File(puzzleInputFilePath)

    outputs.file(puzzleInputFile)
    onlyIf { !puzzleInputFile.exists() }

    if (!project.hasProperty("AOC_TOKEN")) {
        throw GradleException("Missing property: AOC_TOKEN. See README for configuration information.")
    }
    val aocToken = project.property("AOC_TOKEN")

    val urlBase = project.property("aoc-url")

    val projectNameTokens = project.path
        .split(":")
        .filter { it.isNotEmpty() }

    if (projectNameTokens.size != 2) {
        throw GradleException("Not enough tokens in project path.")
    }

    doLast {
        puzzleInputFile.ensureParentDirsCreated()
        puzzleInputFile.createNewFile()

        val year = projectNameTokens[0]
        var day = projectNameTokens[1].substring("day".length)
        if (day.startsWith("0")) {
            day = day.substring(1)
        }

        val url = "${urlBase}/${year}/day/${day}/input"

        val request = HttpRequest.newBuilder()
            .GET()
            .uri(URI.create(url))
            .setHeader("COOKIE", "session=${aocToken}")
            .build()

        val response = HttpClient.newHttpClient().send(request, BodyHandlers.ofString())

        if (response.body().isNotEmpty()) {
            puzzleInputFile.writeText(response.body())
        } else {
            throw GradleException("Empty body when downloading input file from $url")
        }
    }
}

tasks.getByName("processResources"){
    dependsOn(downloadPuzzleInputTask)
}