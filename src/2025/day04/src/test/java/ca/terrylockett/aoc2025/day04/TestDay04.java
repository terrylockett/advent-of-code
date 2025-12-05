package ca.terrylockett.aoc2025.day04;

import ca.terrylockett.aoccommon.resources.Resources;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestDay04 {

	static final String TEST_FILE_NAME = "test-input.txt";

	static String testInput = "";

	@BeforeAll
	static void setup() {
		testInput = Resources.getInput(TEST_FILE_NAME).orElseThrow();
	}

	@Test
	void testDay04part1() {
		assertEquals(13, Day04Runner.part1(testInput));
	}

	@Test
	void testDay04part2() {
		assertEquals(43, Day04Runner.part2(testInput));
	}

}
