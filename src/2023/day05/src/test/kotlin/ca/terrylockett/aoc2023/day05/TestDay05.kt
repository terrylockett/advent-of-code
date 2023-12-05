package ca.terrylockett.aoc2023.day05

import ca.terrylockett.aoccommon.inputfilefinder.InputFileFinder
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TestDay05 {
    @Test
    fun part1() {
        val inputFilePath = InputFileFinder.getInputFilePath("test-input.txt").orElseThrow()
        assertEquals(35L, part1(inputFilePath))
    }

    @Test
    fun part2() {
        val inputFilePath = InputFileFinder.getInputFilePath("test-input.txt").orElseThrow()
        assertEquals(46L, part2(inputFilePath))
    }
}
