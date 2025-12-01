package ca.terrylockett.aoccommon.resources;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Optional;

public class Resources {
	
	private Resources() {
		//top secret hidden constructor.
	}

	/**
	 * return file contents for 'fileName' as a string. 
	 * The file must be in the resources dir of the submodule.
	 *
	 * @param fileName The name of the file.
	 * @return The absolute path to the file.
	 */
	public static Optional<String> getInput(String fileName) {
		var inputStream = ClassLoader.getSystemResourceAsStream(fileName);
		if (null == inputStream) {
			return Optional.empty();
		}
		
		try {
			String fileContents = new String(inputStream.readAllBytes());
			if (fileContents.endsWith("\n")) {
				fileContents = fileContents.substring(0, fileContents.lastIndexOf('\n'));
			}
			return Optional.of(fileContents);
		}
		catch (IOException e) {
			System.out.println(e);
			return Optional.empty()	;
		}
	}
	
	/**
	 *
	 * Gets the absolute file path for a 'fileName'.
	 * The file must be in the resources dir of the submodule.
	 * 
	 * @param fileName The name of the file.
	 * @return The absolute path to the file.
	 */
	public static Optional<String> getInputFilePath(String fileName) throws URISyntaxException {
		
		URL resource = ClassLoader.getSystemResource(fileName);
		if (resource == null) {
			return Optional.empty();
		}
		
		System.out.println("aaaaaaaaa: " + resource);
		
		return Optional.of(new File(resource.toURI()).getAbsolutePath());
	}
}
