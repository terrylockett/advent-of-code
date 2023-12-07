package ca.terrylockett.aoc2023.day07

import ca.terrylockett.aoccommon.inputfilefinder.InputFileFinder
import java.io.File
import java.util.TreeSet
import java.util.regex.Pattern

val FIVE_OF_KIND_REGEX: Pattern = Pattern.compile("(.)(\\1){4}")
val FOUR_OF_KIND_REGEX: Pattern = Pattern.compile("(.)(\\1){3}")
val FULL_HOUSE_REGEX_1: Pattern = Pattern.compile("([\\w])\\1(?!\\1)(?!\\1)(?!\\1)(\\w)\\2\\2")
val FULL_HOUSE_REGEX_2: Pattern = Pattern.compile("([\\w])\\1\\1(?!\\1)(?!\\1)(\\w)\\2")
val THREE_OF_KIND_REGEX: Pattern = Pattern.compile("(.)\\1\\1")
val TWO_PAIR_REGEX: Pattern = Pattern.compile("(.)\\1.*(.)\\2")
val ONE_PAIR_REGEX: Pattern = Pattern.compile("(.)\\1")

class Hand(private val cards: String, val bid: Int) : Comparable<Hand> {
    private val handType: HandType = getHandType(cards)

    companion object {
        val cardWeightMap = HashMap<Char, Int>()

        init {
            cardWeightMap['A'] = 13
            cardWeightMap['K'] = 12
            cardWeightMap['Q'] = 11
            cardWeightMap['J'] = 10
            cardWeightMap['T'] = 9
            cardWeightMap['9'] = 8
            cardWeightMap['8'] = 7
            cardWeightMap['7'] = 6
            cardWeightMap['6'] = 5
            cardWeightMap['5'] = 4
            cardWeightMap['4'] = 3
            cardWeightMap['3'] = 2
            cardWeightMap['2'] = 1
        }
    }

    override fun compareTo(other: Hand): Int {
        // first check hand Type
        if (handType.value > other.handType.value) {
            return 1
        } else if (handType.value < other.handType.value) {
            return -1
        }

        // fall back to the highest card value
        val myCards = cards.toCharArray()
        val otherCards = other.cards.toCharArray()
        for (i in myCards.indices) {
            return if (cardWeightMap.getValue(myCards[i]) > cardWeightMap.getValue(otherCards[i])) {
                1
            } else if (cardWeightMap.getValue(myCards[i]) < cardWeightMap.getValue(otherCards[i])) {
                -1
            } else {
                continue
            }
        }

        return 0
    }

    override fun toString(): String {
        return "Cards: $cards, Bid: $bid, Type: ${handType.name}"
    }
}

fun main() {
    val inputFile: String = InputFileFinder.getInputFilePath("input.txt").orElseThrow()

    println("2023 day07 part1: ${part1(inputFile)}")
    // println("2023 day07 part2: TODO")
}

fun part1(inputFilePath: String): Int {
    val hands = TreeSet<Hand>()

    File(inputFilePath).readLines().forEach {
        val tokens = it.split(" ")
        val cards = tokens[0]
        val bid = Integer.parseInt(tokens[1])
        hands.add(Hand(cards, bid))
    }

    var total = 0
    hands.forEachIndexed { idx, element ->
        total += (idx + 1) * element.bid
    }
    return total
}

fun getHandType(cards: String): HandType {
    val cards = cards.toCharArray().sorted().joinToString("")

    if (FIVE_OF_KIND_REGEX.matcher(cards).matches()) {
        return HandType.FIVE
    }
    if (FOUR_OF_KIND_REGEX.matcher(cards).find()) {
        return HandType.FOUR
    }
    if (FULL_HOUSE_REGEX_1.matcher(cards).matches() || FULL_HOUSE_REGEX_2.matcher(cards).matches()) {
        return HandType.FULL
    }
    if (THREE_OF_KIND_REGEX.matcher(cards).find()) {
        return HandType.THREE
    }
    if (TWO_PAIR_REGEX.matcher(cards).find()) {
        return HandType.TWO_PAIR
    }
    if (ONE_PAIR_REGEX.matcher(cards).find()) {
        return HandType.ONE_PAIR
    }

    return HandType.HIGH_CARD
}

enum class HandType(val value: Int) {
    FIVE(7),
    FOUR(6),
    FULL(5),
    THREE(4),
    TWO_PAIR(3),
    ONE_PAIR(2),
    HIGH_CARD(1),
}
