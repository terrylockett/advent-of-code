package ca.terrylockett.aoccommon.input;

import org.junit.jupiter.api.Test;

import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class PuzzleInputTest {

	@Test
	void testFindFileFromResources() throws URISyntaxException {
		String path = PuzzleInput.getInputFilePath("abc.txt").orElseThrow();

		assertNotNull(path);
	}
}
