package ca.terrylockett.aoc2023.day02

import ca.terrylockett.aoccommon.inputfilefinder.InputFileFinder
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TestDay02 {
    companion object {
        const val PART1_TEST_FILE_NAME = "test-input.txt"
    }

    @Test
    fun part1() {
        val inputFilePath = InputFileFinder.getInputFilePath(PART1_TEST_FILE_NAME).orElseThrow()

        assertEquals(8, CubeGame.playGamePart1(inputFilePath))
    }
}
