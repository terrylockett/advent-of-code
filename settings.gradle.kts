rootProject.name = "AdventOfCode"

includeSubprojects("2022")
includeSubprojects("2023")
includeSubprojects("common")

fun includeSubprojects(dirName: String) {
    val file = File("$rootDir/src/$dirName")

    file.walk().filter { it.isDirectory }.forEach { subDir ->
        val kotlinFile = File(subDir.path, "build.gradle.kts")

        if (kotlinFile.exists()) {
            include("$dirName:${subDir.name}")
            project(":$dirName:${subDir.name}").projectDir = File("$rootDir/src/$dirName/${subDir.name}")
        }
    }
}