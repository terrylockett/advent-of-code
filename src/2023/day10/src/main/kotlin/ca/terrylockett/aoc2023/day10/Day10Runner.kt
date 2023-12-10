package ca.terrylockett.aoc2023.day10;

import ca.terrylockett.aoccommon.resources.Resources;

fun main() {
    val inputFile: String = Resources.getInputFilePath("input.txt").orElseThrow()
    println("2023 day10 part1: ${part1(inputFile)}")
    //println("2023 day10 part2: TODO")
}


fun part1(inputFilePath: String) : Int {
    val pipeMap = PipeMap(inputFilePath)
    return pipeMap.traverse()
}
    



