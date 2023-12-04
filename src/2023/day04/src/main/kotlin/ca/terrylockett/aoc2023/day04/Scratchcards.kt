package ca.terrylockett.aoc2023.day04

import java.nio.file.Files
import java.nio.file.Path
import java.util.regex.Pattern
import kotlin.math.pow
import kotlin.streams.toList

private val CARD_INDEX_PAT = Pattern.compile("Card\\s+(\\d+):")

fun part1GetPoints(inputFilePath: String): Int {
    var total = 0

    Files.lines(Path.of(inputFilePath)).forEach { line ->
        total += convertMatchingNumsToScore(playCard(line))
    }

    return total
}

fun part2GetPoints(inputFilePath: String): Int {
    val cardsCountMap = HashMap<Int, Int>()
    var maxCardRound = 0

    Files.lines(Path.of(inputFilePath)).forEach { line ->
        val currentCardIndex = getCardIndex(line)
        maxCardRound = currentCardIndex
        
        cardsCountMap[currentCardIndex] = cardsCountMap.getOrDefault(currentCardIndex, 0) + 1

        val numMatches = playCard(line)
        for (i in currentCardIndex + 1..currentCardIndex + numMatches) {
            cardsCountMap[i] = cardsCountMap.getOrDefault(i, 0) + (1 * cardsCountMap.getValue(currentCardIndex))
        }
    }

    var total = 0
    for (i in 1..maxCardRound) {
        total += cardsCountMap.getValue(i)
    }
    return total
}

private fun playCard(line: String): Int {
    val tokens = line.split(":", "|")

    val winningTokens = getNumbers(tokens[1])
    val myNumbers = getNumbers(tokens[2])

    return winningTokens.intersect(myNumbers.toSet()).size
}

private fun getCardIndex(line: String): Int {
    val m = CARD_INDEX_PAT.matcher(line)
    m.find()
    return Integer.parseInt(m.group(1))
}

fun convertMatchingNumsToScore(matchingNumsCount: Int): Int {
    return when (matchingNumsCount) {
        0 -> 0
        1 -> 1
        else -> {
            2.0.pow((matchingNumsCount - 1).toDouble()).toInt()
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
