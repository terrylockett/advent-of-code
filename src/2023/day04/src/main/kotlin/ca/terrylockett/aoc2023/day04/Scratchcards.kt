package ca.terrylockett.aoc2023.day04

import java.nio.file.Files
import java.nio.file.Path
import kotlin.math.pow
import kotlin.streams.toList

fun part1GetPoints(inputFilePath: String): Int {
    var total: Int = 0

    Files.lines(Path.of(inputFilePath)).forEach { line ->
        total += playCard(line)
    }

    return total
}

private fun playCard(line: String): Int {
    val tokens = line.split(":", "|")

    val winningTokens = getNumbers(tokens[1])
    val myNumbers = getNumbers(tokens[2])

    return when (val intersect = winningTokens.intersect(myNumbers.toSet()).size) {
        0 -> 0
        1 -> 1
        else -> {
            2.0.pow((intersect - 1).toDouble()).toInt()
        }
    }
}

fun getNumbers(numbersIn: String): List<Int> {
    return numbersIn.split(Regex("\\s+"))
        .stream()
        .map(String::trim)
        .filter(String::isNotEmpty)
        .mapToInt(Integer::parseInt)
        .toList()
}
