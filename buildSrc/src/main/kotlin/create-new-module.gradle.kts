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
		val srcBenchJava = createDir("${projectDir.path}/src/jmh/kotlin/ca/terrylockett/aoc$year/$projectName")
		createAndPopulateFile("$srcMainJava/${capitalizeFirstChar(projectName)}Runner.kt", year, projectName, mainClassContentsKotlin)
		createAndPopulateFile("$srcTestJava/Test${capitalizeFirstChar(projectName)}.kt", year, projectName, testClassContentsKotlin)
		createAndPopulateFile("$srcBenchJava/Bench${capitalizeFirstChar(projectName)}.kt", year, projectName, benchContentsKotlin)
	}
	if(lang == "java") {
		createAndPopulateFile("${projectDir.path}/build.gradle.kts", year, projectName, buildDotGradleContentsJava)
		val srcMainJava = createDir("${projectDir.path}/src/main/java/ca/terrylockett/aoc$year/$projectName")
		val srcTestJava = createDir("${projectDir.path}/src/test/java/ca/terrylockett/aoc$year/$projectName")
		createAndPopulateFile("$srcMainJava/${capitalizeFirstChar(projectName)}Runner.java", year, projectName, mainClassContentsJava)
		createAndPopulateFile("$srcTestJava/Test${capitalizeFirstChar(projectName)}.java", year, projectName, testClassContentsJava)
	}

	createDir("${projectDir.path}/src/main/resources/")
	val srcTestResource = createDir("${projectDir.path}/src/test/resources/")
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

import ca.terrylockett.aoccommon.resources.Resources;

public class %3${'$'}sRunner {

	static final String INPUT_FILE_NAME = "input.txt";

	public static void main(String[] args) throws Exception {
		String input = Resources.getInput(INPUT_FILE_NAME).orElseThrow();

		//System.out.println("%1${'$'}s %2${'$'}s part1: " + part1(input));
		//System.out.println("%1${'$'}s %2${'$'}s part2: " + part2(input));
	}
}
""".replaceFirst("\n", "")


val mainClassContentsKotlin = """
package ca.terrylockett.aoc%1${'$'}s.%2${'$'}s;

import ca.terrylockett.aoccommon.resources.Resources;

val input: String = Resources.getInput("input.txt").orElseThrow()

fun main() {
	//println("%1${'$'}s %2${'$'}s part1: ${'$'}{part1(input)}")
	//println("%1${'$'}s %2${'$'}s part2: ${'$'}{part2(input)}")
}

fun part1(input: String): Int {
	TODO()
}

fun part2(input: String): Int {
	TODO()
}

""".replaceFirst("\n", "")



val testClassContentsJava = """
package ca.terrylockett.aoc%1${'$'}s.%2${'$'}s;

import ca.terrylockett.aoccommon.resources.Resources;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Test%3${'$'}s {

	static final String TEST_FILE_NAME = "test-input.txt";

	static String testFilePath = "";

	@BeforeAll
	static void setup() {
		testFilePath = Resources.getInput(TEST_FILE_NAME).orElseThrow();
	}

	@Test
	void test%3${'$'}spart1() {
		assertEquals(true, true);
	}
}
""".replaceFirst("\n", "")


val testClassContentsKotlin = """
package ca.terrylockett.aoc%1${'$'}s.%2${'$'}s;

import ca.terrylockett.aoccommon.resources.Resources;
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class Test%3${'$'}s {

	@Test
	fun part1() {
		val input = Resources.getInput("test-input.txt").orElseThrow()
		assertEquals(0, 0)
	}
	
//	@Test
//	fun part2() {
//		val input = Resources.getInput("test-input.txt").orElseThrow()
//		assertEquals(0, 0)
//	}
}
""".replaceFirst("\n", "")

val benchContentsKotlin = """
package ca.terrylockett.aoc%1${'$'}s.%2${'$'}s

import ca.terrylockett.aoccommon.resources.Resources
import org.openjdk.jmh.annotations.Benchmark
import org.openjdk.jmh.annotations.Scope
import org.openjdk.jmh.annotations.Setup
import org.openjdk.jmh.annotations.State

@State(Scope.Benchmark)
open class Bench%3${'$'}s {
	private var input: String = ""
	
	@Setup
	fun init() {
		input = Resources.getInput("input.txt").orElseThrow()
	}
	
	@Benchmark
	fun part1(): Any {
		return part1(input)
	}

	@Benchmark
	fun part2(): Any {
		return part2(input)
	}
}
""".replaceFirst("\n", "")
