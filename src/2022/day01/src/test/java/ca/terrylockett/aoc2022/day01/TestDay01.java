package ca.terrylockett.aoc2022.day01;

import ca.terrylockett.aoccommon.inputfilefinder.Resources;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestDay01 {

	private static final String TEST_FILE_NAME = "test_input.txt";

	static String testFilePath;

	@BeforeAll
	public static void setup() throws URISyntaxException {
		testFilePath = Resources.getInputFilePath(TEST_FILE_NAME).orElseThrow();
	}

	@Test
	public void test1_a() throws URISyntaxException, FileNotFoundException {
		assertEquals(24000, DumbElfCalorieTracker.findTheFoodHog(testFilePath));
	}

	@Test
	public void test1_b() throws URISyntaxException, FileNotFoundException {
		assertEquals(45000, DumbElfCalorieTracker.findTheFoodHog(testFilePath, 3));
	}

}
