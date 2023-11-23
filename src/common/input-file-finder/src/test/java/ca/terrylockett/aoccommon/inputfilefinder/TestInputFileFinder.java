package ca.terrylockett.aoccommon.inputfilefinder;

import org.junit.jupiter.api.Test;

import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.*;

class TestInputFileFinder {

	@Test
	void testFindFileFromResources() throws URISyntaxException {
		String path = InputFileFinder.getFilePath(this.getClass().getClassLoader(), "abc.txt");

		assertNotNull(path);
	}
}
