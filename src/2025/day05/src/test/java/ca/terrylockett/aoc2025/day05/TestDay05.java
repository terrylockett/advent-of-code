package ca.terrylockett.aoc2025.day05;

import ca.terrylockett.aoccommon.resources.Resources;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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
	void testDay05part2() {
		assertEquals(14, Day05Runner.part2(testInput));
	}

	@Test
	void testDay05part2OverlappingRangeAfterMerge() {
		String input = "1-5\n" + "6-10\n" + "3-7";

		var actualRanges = Day05Runner.getRangesMerged(input);
		System.out.println(actualRanges);
		assertEquals(1, actualRanges.size());
	}

	@Test
	void testDay05part2OverlappingRangeAfterMerge2() {
		String input = "1-1\n" + "1-5\n" + "3-8\n" + "7-10\n";

		var actualRanges = Day05Runner.getRangesMerged(input);
		System.out.println(actualRanges);
		assertEquals(1, actualRanges.size());
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

	// There is no way my confusing as shit slamRanges(..) function gave me grief
	// and
	// required a butt load of tests to pinpoint my bugs.
	@Test
	void testSlamRanges() {
		Range foo = new Range(1, 1);
		Range bar = new Range(1, 5);
		var actual = Day05Runner.slamRanges(foo, bar);

		assertNotNull(actual);
		assertEquals(1, actual.start);
		assertEquals(5, actual.end);
	}

	@Test
	void testSlamRanges2() {
		Range foo = new Range(1, 5);
		Range bar = new Range(1, 1);
		var actual = Day05Runner.slamRanges(foo, bar);

		assertNotNull(actual);
		assertEquals(1, actual.start);
		assertEquals(5, actual.end);
	}

	@Test
	void testSlamRanges3() {
		Range foo = new Range(1, 5);
		Range bar = new Range(5, 5);
		var actual = Day05Runner.slamRanges(foo, bar);

		assertNotNull(actual);
		assertEquals(1, actual.start);
		assertEquals(5, actual.end);
	}

	@Test
	void testSlamRanges4() {
		Range foo = new Range(5, 5);
		Range bar = new Range(1, 5);
		var actual = Day05Runner.slamRanges(foo, bar);

		assertNotNull(actual);
		assertEquals(1, actual.start);
		assertEquals(5, actual.end);
	}

	@Test
	void testSlamRangesAA() {
		Range foo = new Range(3, 3);
		Range bar = new Range(1, 5);
		var actual = Day05Runner.slamRanges(foo, bar);

		assertNotNull(actual);
		assertEquals(1, actual.start);
		assertEquals(5, actual.end);
	}

	@Test
	void testSlamRanges122() {
		Range bar = new Range(1, 5);
		Range foo = new Range(3, 3);
		var actual = Day05Runner.slamRanges(foo, bar);

		assertNotNull(actual);
		assertEquals(1, actual.start);
		assertEquals(5, actual.end);
	}

	@Test
	void testSlamRanges122aaa() {
		Range foo = new Range(2, 12);
		Range bar = new Range(4, 6);
		var actual = Day05Runner.slamRanges(foo, bar);

		assertNotNull(actual);
	}
}
