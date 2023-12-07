package ca.terrylockett.aoc2023.day07

import ca.terrylockett.aoccommon.inputfilefinder.InputFileFinder
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TestDay07 {
    @Test
    fun part1() {
        val inputFilePath = InputFileFinder.getInputFilePath("test-input.txt").orElseThrow()
        assertEquals(6440, playGame(inputFilePath, false))
    }

    @Test
    fun part2() {
        val inputFilePath = InputFileFinder.getInputFilePath("test-input.txt").orElseThrow()
        assertEquals(5905, playGame(inputFilePath, true))
    }

    @Test
    fun testGetHandType() {
        assertEquals(HandType.FIVE, getHandType("AAAAA"))
        assertEquals(HandType.FOUR, getHandType("BBBBA"))
        assertEquals(HandType.FOUR, getHandType("ABBBB"))

        assertEquals(HandType.FULL, getHandType("AABBB"))
        assertEquals(HandType.FULL, getHandType("AAABB"))
        assertEquals(HandType.FULL, getHandType("ABBBA"))

        assertEquals(HandType.THREE, getHandType("BBBC1"))
        assertEquals(HandType.THREE, getHandType("12223"))
        assertEquals(HandType.THREE, getHandType("ACABA"))

        assertEquals(HandType.TWO_PAIR, getHandType("AABCC"))
        assertEquals(HandType.TWO_PAIR, getHandType("BAACC"))
        assertEquals(HandType.TWO_PAIR, getHandType("AACCB"))

        assertEquals(HandType.ONE_PAIR, getHandType("ABCDA"))
        assertEquals(HandType.ONE_PAIR, getHandType("AABCD"))
        assertEquals(HandType.ONE_PAIR, getHandType("BAACD"))
        assertEquals(HandType.ONE_PAIR, getHandType("BCDAA"))
        assertEquals(HandType.ONE_PAIR, getHandType("BACAD"))
        assertEquals(HandType.ONE_PAIR, getHandType("BACDA"))

        assertEquals(HandType.HIGH_CARD, getHandType("ABCDE"))
        assertEquals(HandType.HIGH_CARD, getHandType("12345"))
    }

    @Test
    fun testGetHandTypeWithWildCard() {
        // Five of a kind --> Five
        assertEquals(HandType.FIVE, getHandTypeWithWildCard("JJJJJ"))
        // Four of a kind --> Five
        assertEquals(HandType.FIVE, getHandTypeWithWildCard("JJJJA"))
        // Full house --> Five
        assertEquals(HandType.FIVE, getHandTypeWithWildCard("JJJAA"))
        assertEquals(HandType.FIVE, getHandTypeWithWildCard("JJAAA"))
        // Three of a kind --> Four
        assertEquals(HandType.FOUR, getHandTypeWithWildCard("JJJAB"))
        assertEquals(HandType.FOUR, getHandTypeWithWildCard("AAAJB"))
        // Two pair with 1J--> Full
        // Two pair with 2J--> Four
        assertEquals(HandType.FULL, getHandTypeWithWildCard("AAJBB"))
        assertEquals(HandType.FOUR, getHandTypeWithWildCard("JJAAB"))
        // One pair --> Three
        assertEquals(HandType.THREE, getHandTypeWithWildCard("AABCJ"))
        assertEquals(HandType.THREE, getHandTypeWithWildCard("JJABC"))
        // Nothing --> One
        assertEquals(HandType.ONE_PAIR, getHandTypeWithWildCard("JABCD"))
    }
}
