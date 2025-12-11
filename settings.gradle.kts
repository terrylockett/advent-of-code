rootProject.name = "AdventOfCode"

include("common")
project(":common").projectDir = File("$rootDir/src/common")

includeSubprojects("2015")
includeSubprojects("2022")
includeSubprojects("2023")
includeSubprojects("2024")
includeSubprojects("2025")

fun includeSubprojects(dirName: String) {
    val file = File("$rootDir/src/$dirName")

    file.walk().filter { it.isDirectory }.forEach { subDir ->
        val buildDotGradleFile = File(subDir.path, "build.gradle.kts")

        if (buildDotGradleFile.exists()) {
            include("$dirName:${subDir.name}")
            project(":$dirName:${subDir.name}").projectDir = File("$rootDir/src/$dirName/${subDir.name}")
        }
    }
}