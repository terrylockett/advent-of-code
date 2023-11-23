package ca.terrylockett.aoc2023.inputfilefinder;

import org.junit.jupiter.api.Test;

import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestInputFileFinder {

	@Test
	void testFindFileFromResources() throws URISyntaxException {
		String expected = "/Users/terry/Workspaces/Misc/AdventOfCode2023/libs/InputFileFinder/build/resources/test/abc.txt";

		String path = InputFileFinder.getFilePath(this.getClass().getClassLoader(), "abc.txt");

		assertEquals(expected, path);
	}
}
