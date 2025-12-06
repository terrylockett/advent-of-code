package ca.terrylockett.aoc2025.day05;

import ca.terrylockett.aoccommon.resources.Resources;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestDay05 {

	static final String TEST_FILE_NAME = "test-input.txt";

	static String testInput = "";

	@BeforeAll
	static void setup() {
		testInput = Resources.getInput(TEST_FILE_NAME).orElseThrow();
	}

	@Test
	void testDay05part1() {
		assertEquals(3, Day05Runner.part1(testInput));
	}

	@Test
	void testGetRanges() {
		var ranges = Day05Runner.getRanges(testInput);
		assertEquals(3L, ranges.get(0).start);
		assertEquals(5L, ranges.get(0).end);

		assertEquals(12L, ranges.get(3).start);
		assertEquals(18L, ranges.get(3).end);
	}

	@Test
	void testGetIds() {
		var ids = Day05Runner.getIds(testInput);
		assertEquals(6, ids.size());
		assertEquals(1L, ids.get(0));

		assertEquals(32L, ids.get(5));
	}

}
