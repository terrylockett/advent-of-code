package ca.terrylockett.aoc2023.day01

import ca.terrylockett.aoccommon.inputfilefinder.InputFileFinder

fun main() {
    val inputFile: String = InputFileFinder.getInputFilePath("input.txt").orElseThrow()

    println("2023 day01 part1: ${CalibrationDocument.basicCalibration(inputFile)}")
    println("2023 day01 part2: ${CalibrationDocument.fancyCalibration(inputFile)}")
}
