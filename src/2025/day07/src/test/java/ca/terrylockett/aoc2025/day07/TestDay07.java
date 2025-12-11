package ca.terrylockett.aoc2025.day07;

import ca.terrylockett.aoccommon.input.PuzzleInput;
import ca.terrylockett.aoccommon.structures.Grid;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestDay07 {

	static final String TEST_FILE_NAME = "test-input.txt";

	static String testInput = "";

	@BeforeAll
	static void setup() {
		testInput = PuzzleInput.getInput(TEST_FILE_NAME).orElseThrow();
	}

	@Test
	void testDay07part1() {
		Grid grid = Day07Runner.initGrid(testInput);
		assertEquals(21, Day07Runner.part1(grid));
	}

	@Test
	void testDay07part2() {
		Grid grid = Day07Runner.initGrid(testInput);
		Day07Runner.part1(grid);

		assertEquals(40L, Day07Runner.part2(grid));
	}

}
