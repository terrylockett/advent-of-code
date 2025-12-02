package ca.terrylockett.aoc2025.day02;

import ca.terrylockett.aoccommon.resources.Resources;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TestDay02 {

	static final String TEST_FILE_NAME = "test-input.txt";

	static String testInput = "";
	static List<Range> ranges;

	@BeforeAll
	static void setup() {
		testInput = Resources.getInput(TEST_FILE_NAME).orElseThrow();
		ranges = Range.createRanges(testInput);
	}

	@Test
	void testDay02part1() {
		assertEquals(1227775554L, Day02Runner.part1(ranges));
	}

	@Test
	void testDay02part2() {
		assertEquals(4174379265L, Day02Runner.part2(ranges));
	}

	@Test
	void testIsInvalidIdPt2() {
		assertTrue(Day02Runner.isInvalidProductIdPt2(12341234));
		assertTrue(Day02Runner.isInvalidProductIdPt2(11));
		assertTrue(Day02Runner.isInvalidProductIdPt2(999));
		assertTrue(Day02Runner.isInvalidProductIdPt2(123123123));

		assertFalse(Day02Runner.isInvalidProductIdPt2(12));
		assertFalse(Day02Runner.isInvalidProductIdPt2(1231234));
		assertFalse(Day02Runner.isInvalidProductIdPt2(5));
	}

	@Test
	void testRangeCreation() {
		assertEquals(11, ranges.size());
		// First Range
		assertEquals(11L, ranges.get(0).getStart());
		assertEquals(22L, ranges.get(0).getEnd());
		// Last Range
		assertEquals(2121212118L, ranges.get(10).getStart());
		assertEquals(2121212124L, ranges.get(10).getEnd());
	}
}
