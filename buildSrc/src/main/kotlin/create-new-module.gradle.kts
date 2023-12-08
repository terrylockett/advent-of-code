import java.util.*


tasks.register("newModule") {
    group = "Advent of Code"

    val moduleName: String by project
    val lang: String by project

    if (!project.hasProperty("moduleName") || !moduleName.contains(":") || !project.hasProperty("lang")) {
        throw GradleException("Missing moduleName paramter. specify newModule by using: './gradlew newModule -PnewModule=<year>:<name> -Plang=kotlin'")
    }
    if("kotlin" != lang && "java" != lang) {
        throw GradleException("lang: $lang is not supported. valid languages are: [java|kotlin]")
    }
    
    
    val moduleTokens = moduleName.split(":")
    val year = moduleTokens[0]
    val projectName = moduleTokens[1]

    if (!File("$rootDir/src/$year").exists()) {
        throw GradleException("$rootDir/src/$year does not exist.")
    }

    val projectDir = createDir("$rootDir/src/$year/$projectName/")
    
    if(lang == "kotlin") {
        createAndPopulateFile("${projectDir.path}/build.gradle.kts", year, projectName, buildDotGradleContentsKotlin)
        val srcMainJava = createDir("${projectDir.path}/src/main/kotlin/ca/terrylockett/aoc$year/$projectName")
        val srcTestJava = createDir("${projectDir.path}/src/test/kotlin/ca/terrylockett/aoc$year/$projectName")
        createAndPopulateFile("$srcMainJava/${capitalizeFirstChar(projectName)}Runner.kt", year, projectName, mainClassContentsKotlin)
        createAndPopulateFile("$srcTestJava/Test${capitalizeFirstChar(projectName)}.kt", year, projectName, testClassContentsKotlin)
    } 
    if(lang == "java") {
        createAndPopulateFile("${projectDir.path}/build.gradle.kts", year, projectName, buildDotGradleContentsJava)
        val srcMainJava = createDir("${projectDir.path}/src/main/java/ca/terrylockett/aoc$year/$projectName")
        val srcTestJava = createDir("${projectDir.path}/src/test/java/ca/terrylockett/aoc$year/$projectName")
        createAndPopulateFile("$srcMainJava/${capitalizeFirstChar(projectName)}Runner.java", year, projectName, mainClassContentsJava)
        createAndPopulateFile("$srcTestJava/Test${capitalizeFirstChar(projectName)}.java", year, projectName, testClassContentsJava)
    }

    val srcMainResource = createDir("${projectDir.path}/src/main/resources/")
    val srcTestResource = createDir("${projectDir.path}/src/test/resources/")
    File("$srcMainResource/input.txt").createNewFile()
    File("$srcTestResource/test-input.txt").createNewFile()
}

fun createDir(dirPath: String): File {
    val dir = File(dirPath)
    dir.mkdirs()
    return dir
}

fun createAndPopulateFile(filePath: String, year: String, projectName: String, fileContents: String) {
    val file = File(filePath)
    if (file.createNewFile()) {
        file.writeText(String.format(fileContents, year, projectName, capitalizeFirstChar(projectName)))
    }
}

fun capitalizeFirstChar(str: String): String {
    return str.replaceFirstChar {
        if (it.isLowerCase()) {
            it.titlecase(Locale.getDefault())
        } else {
            it.toString()
        }
    }
}

val buildDotGradleContentsJava = """
plugins {
    `aoc-project-conventions`
}

application {
    mainClass.set("ca.terrylockett.aoc%s.%s.%sRunner")
}
""".replaceFirst("\n", "")

val buildDotGradleContentsKotlin = """
plugins {
    `aoc-project-conventions`
}

application {
    mainClass.set("ca.terrylockett.aoc%s.%s.%sRunnerKt")
}
""".replaceFirst("\n", "")


val mainClassContentsJava = """
package ca.terrylockett.aoc%1${'$'}s.%2${'$'}s;

import ca.terrylockett.aoccommon.inputfilefinder.Resources;

public class %3${'$'}sRunner {

    static final String INPUT_FILE_NAME = "input.txt";

    public static void main(String[] args) throws Exception {
        String inputFilePath = Resources.getFilePath(%3${'$'}sRunner.class.getClassLoader(), INPUT_FILE_NAME);

        System.out.println("Hello");
    }
}
""".replaceFirst("\n", "")


val mainClassContentsKotlin = """
package ca.terrylockett.aoc%1${'$'}s.%2${'$'}s;

import ca.terrylockett.aoccommon.inputfilefinder.Resources;

fun main() {
    val inputFile: String = Resources.getInputFilePath("input.txt").orElseThrow()

    //println("%1${'$'}s %2${'$'}s part1: TODO")
    //println("%1${'$'}s %2${'$'}s part2: TODO")
}

""".replaceFirst("\n", "")



val testClassContentsJava = """
package ca.terrylockett.aoc%1${'$'}s.%2${'$'}s;

import ca.terrylockett.aoccommon.inputfilefinder.Resources;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Test%3${'$'}s {

    static final String TEST_FILE_NAME = "test-input.txt";

    static String testFilePath = "";

    @BeforeAll
    static void setup() throws URISyntaxException {
        testFilePath = Resources.getFilePath(Test%3${'$'}s.class.getClassLoader(), TEST_FILE_NAME);
    }

    @Test
    void test%3${'$'}spart1() {
    assertEquals(true, true);
}
}
""".replaceFirst("\n", "")


val testClassContentsKotlin = """
package ca.terrylockett.aoc%1${'$'}s.%2${'$'}s;

import ca.terrylockett.aoccommon.inputfilefinder.Resources;
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class Test%3${'$'}s {

    @Test
    fun part1() {
        val inputFilePath = Resources.getInputFilePath("test-input.txt").orElseThrow()
        assertEquals(0, 0)
    }
    
//    @Test
//    fun part2() {
//        val inputFilePath = Resources.getInputFilePath("test-input.txt").orElseThrow()
//        assertEquals(0, 0)
//    }
}
""".replaceFirst("\n", "")