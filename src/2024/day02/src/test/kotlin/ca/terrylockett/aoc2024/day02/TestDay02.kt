package ca.terrylockett.aoc2024.day02

import ca.terrylockett.aoccommon.resources.Resources
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TestDay02 {

	@Test
	fun part1() {
		val inputFilePath = Resources.getInputFilePath("test-input.txt").orElseThrow()
		assertEquals(2, part01(inputFilePath))
	}

	@Test
	fun part2() {
		val inputFilePath = Resources.getInputFilePath("test-input.txt").orElseThrow()
		assertEquals(4, part02(inputFilePath))
	}
}
