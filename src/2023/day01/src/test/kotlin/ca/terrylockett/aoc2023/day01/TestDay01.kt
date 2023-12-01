package ca.terrylockett.aoc2023.day01

import ca.terrylockett.aoccommon.inputfilefinder.InputFileFinder
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TestDay01 {
    companion object {
        const val PART1_TEST_FILE_NAME = "test-input.txt"
        const val PART2_TEST_FILE_NAME = "test-input2.txt"
    }

    @Test
    fun testDay01_part1() {
        val part1InputFile = InputFileFinder.getInputFilePath(PART1_TEST_FILE_NAME).orElseThrow()

        assertEquals(142, CalibrationDocument.basicCalibration(part1InputFile))
    }

    @Test
    fun testDay01_part2() {
        val part2InputFile = InputFileFinder.getInputFilePath(PART2_TEST_FILE_NAME).orElseThrow()

        assertEquals(281, CalibrationDocument.fancyCalibration(part2InputFile))
    }
}
