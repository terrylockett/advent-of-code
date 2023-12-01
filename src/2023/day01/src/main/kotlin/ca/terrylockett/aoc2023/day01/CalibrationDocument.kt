package ca.terrylockett.aoc2023.day01

import java.io.File
import java.io.FileNotFoundException
import kotlin.streams.toList

class CalibrationDocument(inputFilePath: String) {
  private val inputFilePath: String

  init {
    if (!File(inputFilePath).exists()) {
      throw FileNotFoundException("$inputFilePath could not be found")
    }
    this.inputFilePath = inputFilePath
  }

  fun calibrationValuesSum(): Int {
    var total = 0
    File(inputFilePath).forEachLine { line ->
      run {
        val ints =
            line.chars().filter(Character::isDigit).boxed().map(Character::getNumericValue).toList()
        total += (ints.first() * 10) + ints.last()
      }
    }
    return total
  }
}
