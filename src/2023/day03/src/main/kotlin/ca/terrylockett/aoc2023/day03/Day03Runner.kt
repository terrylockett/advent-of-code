package ca.terrylockett.aoc2023.day03

import ca.terrylockett.aoccommon.resources.Resources

fun main() {
	val inputFile: String = Resources.getInputFilePath("input.txt").orElseThrow()
	val engine = EngineSchematic(inputFile)

	println("2023 day03 part1: ${engine.part1FindParts()}")
	println("2023 day03 part2: ${engine.part2FindGears()}")
}
