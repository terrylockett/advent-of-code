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
        assertEquals(6440, part1(inputFilePath))
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

//    @Test
//    fun part2() {
//        val inputFilePath = InputFileFinder.getInputFilePath("test-input.txt").orElseThrow()
//        assertEquals(0, 0)
//    }
}
