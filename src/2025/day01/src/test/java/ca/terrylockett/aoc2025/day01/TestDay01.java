package ca.terrylockett.aoc2025.day01;

import ca.terrylockett.aoccommon.resources.Resources;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestDay01 {

	static final String TEST_FILE_NAME = "test-input.txt";

	static String testFilePath = "";

	@BeforeAll
	static void setup() throws URISyntaxException {
		testFilePath = Resources.getInputFilePath(TEST_FILE_NAME).orElseThrow();
	}

	@Test
	void testDay01part1() throws FileNotFoundException {
		var dial = new Dial();
		int actual = dial.part1(testFilePath);

		assertEquals(3, actual);
	}

	@Test
	void testDay01part2() throws FileNotFoundException {
		var dial = new Dial();
		int actual = dial.part2(testFilePath);

		assertEquals(6, actual);
	}

	@Test
	void testTesty() {
		var dial = new Dial();
		dial.setPosition(0);

		Dial.part2RotateDial(dial, 1000);
		Dial.part2RotateDial(dial, -1000);
		assertEquals(20, dial.getZeroCount());
		assertEquals(0, dial.getPosition());

		dial = new Dial();
		Dial.part2RotateDial(dial, -50);
		assertEquals(1, dial.getZeroCount());
		assertEquals(0, dial.getPosition());

		Dial.part2RotateDial(dial, -1);
		assertEquals(1, dial.getZeroCount());
		assertEquals(99, dial.getPosition());

		dial = new Dial();
		Dial.part2RotateDial(dial, -50);
		Dial.part2RotateDial(dial, 100);
		assertEquals(2, dial.getZeroCount());
		assertEquals(0, dial.getPosition());
	}

}
