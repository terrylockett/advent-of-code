package ca.terrylockett.aoc2023.day10;

import ca.terrylockett.aoccommon.resources.Resources;
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TestDay10 {

    @Test
    fun part1_1() {
        val inputFilePath = Resources.getInputFilePath("test-input1.txt").orElseThrow()
        assertEquals(4, part1(inputFilePath))
    }

    @Test
    fun part1_2() {
        val inputFilePath = Resources.getInputFilePath("test-input2.txt").orElseThrow()
        assertEquals(8, part1(inputFilePath))
    }
    @Test
    fun part1_3() {
        val inputFilePath = Resources.getInputFilePath("test-input3.txt").orElseThrow()
        assertEquals(6, part1(inputFilePath))
    }
    
//    @Test
//    fun part2() {
//        val inputFilePath = Resources.getInputFilePath("test-input.txt").orElseThrow()
//        assertEquals(0, 0)
//    }
}
