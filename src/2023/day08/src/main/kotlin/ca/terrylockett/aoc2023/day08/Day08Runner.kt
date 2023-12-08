package ca.terrylockett.aoc2023.day08

import ca.terrylockett.aoccommon.resources.Resources
import java.io.File

fun main() {
	val inputFile: String = Resources.getInputFilePath("input.txt").orElseThrow()

	println("2023 day08 part1: ${part1(inputFile)}")
	println("2023 day08 part2: ${part2(inputFile)}")
}

fun part1(inputFilePath: String): Int {
	val instructions = File(inputFilePath).readLines().first().toCharArray()
	val elementsMap = createElementsMap(inputFilePath)
	
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

fun part2(inputFilePath: String): Long {
	val instructions = File(inputFilePath).readLines().first().toCharArray()
	val elementsMap = createElementsMap(inputFilePath)

	val startingNodes = elementsMap.filter { it.key.endsWith('A') }.keys.map { it }.toList()
	
	val resultsList = ArrayList<Long>()

	for (node in startingNodes) {
		var node = node
		var moves = 0L
		while (true) {
			for (instruction in instructions) {
				moves++
				when (instruction) {
					'L' -> node = (elementsMap.getValue(node).left)
					'R' -> node = (elementsMap.getValue(node).right)
				}
				if (node.endsWith('Z')) {
					break
				}
			}
			if (node.endsWith('Z')) {
				resultsList.add(moves)
				break
			}
		}
	}
	
	return findLowestCommonMultiple(resultsList)
}

fun findLowestCommonMultiple(numbers: List<Long>): Long {
	var lcm = numbers[0]
	for (i in 1 until numbers.size) {
		val currentNumber = numbers[i]
		lcm = lcm * currentNumber / findGreatestCommonDivisor(lcm, currentNumber)
	}
	return lcm
}

fun findGreatestCommonDivisor(first: Long, second: Long): Long {
	return if (second == 0L) {
		first
	} else {
		findGreatestCommonDivisor(second, first % second)
	}
}

private fun createElementsMap(inputFilePath: String): Map<String, Element> {
	val elementsMap = HashMap<String, Element>()

	File(inputFilePath).readLines()
		.filter { it.contains('=') }
		.forEach { line ->
			val key = line.split("=")[0].trim()
			val element = createElementFromPair(line.split("=")[1].trim())
			elementsMap[key] = element
		}
	
	return elementsMap
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
