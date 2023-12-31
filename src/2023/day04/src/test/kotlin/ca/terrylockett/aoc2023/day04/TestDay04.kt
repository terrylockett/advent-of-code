package ca.terrylockett.aoc2023.day04

import ca.terrylockett.aoccommon.resources.Resources
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TestDay04 {
	companion object {
		const val TEST_FILE_NAME = "test-input.txt"
	}

	@Test
	fun part1() {
		val inputFilePath = Resources.getInputFilePath(TEST_FILE_NAME).orElseThrow()

		assertEquals(13, part1GetPoints(inputFilePath))
	}

	@Test
	fun part2() {
		val inputFilePath = Resources.getInputFilePath(TEST_FILE_NAME).orElseThrow()

		assertEquals(30, part2GetPoints(inputFilePath))
	}
}
