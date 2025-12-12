package ca.terrylockett.aoc2025.day09;

import ca.terrylockett.aoccommon.input.PuzzleInput;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestDay09 {

	static final String TEST_FILE_NAME = "test-input.txt";

	static String testInput = "";

	@BeforeAll
	static void setup() {
		testInput = PuzzleInput.getInput(TEST_FILE_NAME).orElseThrow();
	}

	@Test
	void testDay09part1() {
		assertEquals(50L, Day09Runner.part1(testInput));
	}
}
