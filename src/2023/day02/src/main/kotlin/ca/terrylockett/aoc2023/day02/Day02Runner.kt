package ca.terrylockett.aoc2023.day02

import ca.terrylockett.aoccommon.inputfilefinder.Resources

fun main() {
	val inputFile: String = Resources.getInputFilePath("input.txt").orElseThrow()

	println("2023 day02 part1: ${CubeGame.playGamePart1(inputFile)}")
	println("2023 day02 part2: ${CubeGame.playGamePart2(inputFile)}")
}
