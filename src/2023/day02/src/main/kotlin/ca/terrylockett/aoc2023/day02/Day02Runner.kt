package ca.terrylockett.aoc2023.day02

import ca.terrylockett.aoccommon.inputfilefinder.InputFileFinder

fun main() {
    val inputFile: String = InputFileFinder.getInputFilePath("input.txt").orElseThrow()

    println("2023 day02 part1: ${CubeGame.playGamePart1(inputFile)}")
}
