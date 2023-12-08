package ca.terrylockett.aoc2023.day04

import ca.terrylockett.aoccommon.inputfilefinder.Resources

fun main() {
	val inputFile: String = Resources.getInputFilePath("input.txt").orElseThrow()

	println("day04 2023 part1: ${part1GetPoints(inputFile)}")
	println("day04 2023 part2: ${part2GetPoints(inputFile)}")
}
