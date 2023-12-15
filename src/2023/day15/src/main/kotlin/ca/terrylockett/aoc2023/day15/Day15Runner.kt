package ca.terrylockett.aoc2023.day15;

import ca.terrylockett.aoccommon.resources.Resources;
import java.io.File

fun main() {
    val inputFile: String = Resources.getInputFilePath("input.txt").orElseThrow()
    println("2023 day15 part1: ${part01(inputFile)}")
}


fun part01(inputFilePath : String) : Int {
    val tokens = File(inputFilePath).readLines().first().split(",")
    val hashedValues = ArrayList<Int>()

    for(token in tokens){
        var currentValue = 0;
        for(x in token.toCharArray()) {
            currentValue += x.code;
            currentValue *= 17;
            currentValue %= 256
        }
        hashedValues.add(currentValue)
    }

    return hashedValues.sum();
}