package ca.terrylockett.aoc2023.day01

import ca.terrylockett.aoccommon.inputfilefinder.Resources

fun main() {
	val inputFile: String = Resources.getInputFilePath("input.txt").orElseThrow()

	println("2023 day01 part1: ${CalibrationDocument.basicCalibration(inputFile)}")
	println("2023 day01 part2: ${CalibrationDocument.fancyCalibration(inputFile)}")
}
