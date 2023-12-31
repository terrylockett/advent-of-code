package ca.terrylockett.aoc2022.day09;

import ca.terrylockett.aoccommon.resources.Resources;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestDay09 {

	static final String TEST_FILE_NAME = "test_input.txt";
	static final String TEST_LARGE_FILE_NAME = "test_large_input.txt";
	static String testFilePath = "";
	static String testLargeFilePath = "";

	@BeforeAll
	public static void setup() throws URISyntaxException {
		testFilePath = Resources.getInputFilePath(TEST_FILE_NAME).orElseThrow();
		testLargeFilePath = Resources.getInputFilePath(TEST_LARGE_FILE_NAME).orElseThrow();
	}

	@Test
	public void testDay09Part01() throws Exception {
		Rope rope = new Rope(2);
		rope.processMoves(testFilePath);

		assertEquals(13, rope.getTailKnot().getVisitedLocationsCount());
	}

	@Test
	public void testDay09Part02() throws Exception {
		Rope rope = new Rope(10);
		rope.processMoves(testFilePath);

		assertEquals(1, rope.getTailKnot().getVisitedLocationsCount());

	}

	@Test
	public void testDay09Part02_large() throws Exception {
		Rope rope = new Rope(10);
		rope.processMoves(testLargeFilePath);

		assertEquals(36, rope.getTailKnot().getVisitedLocationsCount());
	}
}
