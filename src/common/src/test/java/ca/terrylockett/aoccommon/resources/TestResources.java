package ca.terrylockett.aoccommon.resources;

import org.junit.jupiter.api.Test;

import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.*;

@Deprecated
class TestResources {

	@Test
	void testFindFileFromResources() throws URISyntaxException {
		String path = Resources.getInputFilePath("abc.txt").orElseThrow();

		assertNotNull(path);
	}
}
