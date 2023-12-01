package ca.terrylockett.aoc2023.day01

import ca.terrylockett.aoccommon.inputfilefinder.InputFileFinder

fun main() {
  val inputFile: String = InputFileFinder.getInputFilePath("input.txt").orElseThrow()
  val calibrationDocument = CalibrationDocument(inputFile)
  
  println("2023 day01: ${calibrationDocument.calibrationValuesSum()}")
}
