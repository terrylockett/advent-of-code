package ca.terrylockett.aoc2023.day06

import ca.terrylockett.aoccommon.inputfilefinder.Resources
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TestDay06 {
	@Test
	fun part1() {
		val inputFilePath = Resources.getInputFilePath("test-input.txt").orElseThrow()
		assertEquals(288, part1(inputFilePath))
	}

	@Test
	fun part2() {
		val inputFilePath = Resources.getInputFilePath("test-input.txt").orElseThrow()
		assertEquals(71503, part2(inputFilePath))
	}
}
