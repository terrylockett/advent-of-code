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

}
