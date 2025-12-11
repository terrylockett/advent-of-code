package ca.terrylockett.aoc2025.day08;

import ca.terrylockett.aoccommon.input.PuzzleInput;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestDay08 {

	static final String TEST_FILE_NAME = "test-input.txt";

	static String testInput = "";

	@BeforeAll
	static void setup() {
		testInput = PuzzleInput.getInput(TEST_FILE_NAME).orElseThrow();
	}

	@Test
	void testDay08part1() {
		assertEquals(40, Day08Runner.part1(testInput, 10));
	}

	@Test
	void testDay08part2() {
		assertEquals(25272, Day08Runner.part2(testInput));
	}
}
