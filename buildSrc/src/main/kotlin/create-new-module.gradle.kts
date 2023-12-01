import java.util.*


tasks.register("newModule") {
    group = "Advent of Code"

    val moduleName: String by project

    if (!project.hasProperty("moduleName") || !moduleName.contains(":")) {
        throw GradleException("Missing moduleName paramter. specify newModule by using: './gradlew newModule -PnewModule=<year>:<name>'")
    }

    val moduleTokens = moduleName.split(":")
    val year = moduleTokens[0]
    val projectName = moduleTokens[1]

    if (!File("$rootDir/src/$year").exists()) {
        throw GradleException("$rootDir/src/$year does not exist.")
    }

    val projectDir = createDir("$rootDir/src/$year/$projectName/")
    val srcMainJava = createDir("${projectDir.path}/src/main/java/ca/terrylockett/aoc$year/$projectName")
    val srcTestJava = createDir("${projectDir.path}/src/test/java/ca/terrylockett/aoc$year/$projectName")
    val srcMainResource = createDir("${projectDir.path}/src/main/resources/")
    val srcTestResource = createDir("${projectDir.path}/src/test/resources/")

    createAndPopulateFile("${projectDir.path}/build.gradle.kts", year, projectName, buildDotGradleContents)
    createAndPopulateFile("$srcMainJava/${capitalizeFirstChar(projectName)}Runner.java", year, projectName, mainClassContents)
    createAndPopulateFile("$srcTestJava/Test${capitalizeFirstChar(projectName)}.java", year, projectName, testClassContents)
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

val buildDotGradleContents = """
plugins {
    `aoc-project-conventions`
}

application {
    mainClass.set("ca.terrylockett.aoc%s.%s.%sRunner")
}
""".replaceFirst("\n", "")


val mainClassContents = """
package ca.terrylockett.aoc%1${'$'}s.%2${'$'}s;

import ca.terrylockett.aoccommon.inputfilefinder.InputFileFinder;

public class %3${'$'}sRunner {

    static final String INPUT_FILE_NAME = "input.txt";

    public static void main(String[] args) throws Exception {
        String inputFilePath = InputFileFinder.getFilePath(%3${'$'}sRunner.class.getClassLoader(), INPUT_FILE_NAME);

        System.out.println("Hello");
    }
}
""".replaceFirst("\n", "")



val testClassContents = """
package ca.terrylockett.aoc%1${'$'}s.%2${'$'}s;

import ca.terrylockett.aoccommon.inputfilefinder.InputFileFinder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Test%3${'$'}s {

    static final String TEST_FILE_NAME = "test-input.txt";

    static String testFilePath = "";

    @BeforeAll
    static void setup() throws URISyntaxException {
        testFilePath = InputFileFinder.getFilePath(Test%3${'$'}s.class.getClassLoader(), TEST_FILE_NAME);
    }

    @Test
    void test%3${'$'}spart1() {
    assertEquals(true, true);
}
}
""".replaceFirst("\n", "")