package ca.terrylockett.aoc2025.day02;

import ca.terrylockett.aoccommon.resources.Resources;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestDay02 {

	static final String TEST_FILE_NAME = "test-input.txt";

	static String testInput = "";

	@BeforeAll
	static void setup() {
		testInput = Resources.getInput(TEST_FILE_NAME).orElseThrow();
	}

	@Test
	void testDay02part1() {
		assertEquals(1227775554L, Day02Runner.part1(testInput));
	}

	@Test
	void testRangeCreation() {
		var ranges = Range.createRanges(testInput);
		assertEquals(11, ranges.size());
		// First Range
		assertEquals(11L, ranges.get(0).getStart());
		assertEquals(22L, ranges.get(0).getEnd());
		// Last Range
		assertEquals(2121212118L, ranges.get(10).getStart());
		assertEquals(2121212124L, ranges.get(10).getEnd());
	}
}
