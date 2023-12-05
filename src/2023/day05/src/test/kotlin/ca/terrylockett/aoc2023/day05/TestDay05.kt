package ca.terrylockett.aoc2023.day05;

import ca.terrylockett.aoccommon.inputfilefinder.InputFileFinder;
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TestDay05 {

   companion object {
        const val TEST_FILE_NAME = "test-input.txt"
    }

    @Test
    fun part1() {
        val inputFilePath = InputFileFinder.getInputFilePath(TEST_FILE_NAME).orElseThrow()

        assertEquals(35L, part1(inputFilePath))
    }
    
//    @Test
//    fun part2() {
//        val inputFilePath = InputFileFinder.getInputFilePath(TEST_FILE_NAME).orElseThrow()
//        val engine = EngineSchematic(inputFilePath)
//
//        assertEquals(467835, engine.part2FindGears())
//    }
}
