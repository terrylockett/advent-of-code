package ca.terrylockett.aoc2025.day03;

import ca.terrylockett.aoccommon.resources.Resources;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestDay03 {

	static final String TEST_FILE_NAME = "test-input.txt";

	static String testInput = "";

	@BeforeAll
	static void setup() {
		testInput = Resources.getInput(TEST_FILE_NAME).orElseThrow();
	}

	@Test
	void testDay03part1() {
		assertEquals(357, Day03Runner.part1(testInput));
	}
}
