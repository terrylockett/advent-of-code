package ca.terrylockett.aoc2025.day06;

import ca.terrylockett.aoccommon.resources.Resources;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestDay06 {

	static final String TEST_FILE_NAME = "test-input.txt";

	static String testInput = "";

	@BeforeAll
	static void setup() {
		testInput = Resources.getInput(TEST_FILE_NAME).orElseThrow();
	}

	@Test
	void testDay06part1() {
		assertEquals(4277556L, Day06Runner.part1(testInput));
	}

	@Test
	void testDay06part2() {
		assertEquals(3263827L, Day06Runner.part2(testInput));
	}
}
