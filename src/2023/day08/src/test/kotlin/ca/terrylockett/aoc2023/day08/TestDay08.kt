package ca.terrylockett.aoc2023.day08

import ca.terrylockett.aoccommon.resources.Resources
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TestDay08 {

	@Test
	fun part1_1() {
		val inputFilePath = Resources.getInputFilePath("test-input.txt").orElseThrow()
		assertEquals(2, part1(inputFilePath))
	}

	@Test
	fun part1_2() {
		val inputFilePath = Resources.getInputFilePath("test-input2.txt").orElseThrow()
		assertEquals(6, part1(inputFilePath))
	}

//    @Test
//    fun part2() {
//        val inputFilePath = Resources.getInputFilePath("test-input.txt").orElseThrow()
//        assertEquals(0, 0)
//    }
}
