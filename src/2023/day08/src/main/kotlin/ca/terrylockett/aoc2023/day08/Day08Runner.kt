package ca.terrylockett.aoc2023.day08

import ca.terrylockett.aoccommon.resources.Resources
import java.io.File

fun main() {
	val inputFile: String = Resources.getInputFilePath("input.txt").orElseThrow()

	println("2023 day08 part1: ${part1(inputFile)}")
	// println("2023 day08 part2: TODO")
}

fun part1(inputFilePath: String): Int {
	val instructions = File(inputFilePath).readLines().first().toCharArray()
	
	val elementsMap = HashMap<String, Element>()

	File(inputFilePath).readLines()
		.filter { it.contains('=') }
		.forEach { line ->
			val key = line.split("=")[0].trim()
			val element = createElementFromPair(line.split("=")[1].trim())
			elementsMap[key] = element
		}
	
	var currentNode = "AAA"
	var moves = 0
	while (true) {
		for (instruction in instructions) {
			moves += 1
			when (instruction) {
				'L' -> currentNode = elementsMap.getValue(currentNode).left
				'R' ->	currentNode = elementsMap.getValue(currentNode).right
			}
			if (currentNode == "ZZZ") {
				break
			}
		}
		if (currentNode == "ZZZ") {
			break
		}
	}
	
	return moves
}

private fun createElementFromPair(inputPair: String): Element {
	val tokens = inputPair.trim()
		.replace("(", "")
		.replace(")", "")
		.split(",")
		.map(String::trim)
	val left = tokens[0]
	val right = tokens[1]

	return Element(left, right)
}

class Element(val left: String, val right: String)
