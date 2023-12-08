package ca.terrylockett.aoccommon.inputfilefinder;

import org.junit.jupiter.api.Test;

import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.*;

class TestResources {

	@Test
	void testFindFileFromResources() throws URISyntaxException {
		String path = Resources.getInputFilePath("abc.txt").orElseThrow();

		assertNotNull(path);
	}
}
