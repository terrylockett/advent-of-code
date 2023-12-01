package ca.terrylockett.aoccommon.inputfilefinder;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Optional;

public class InputFileFinder {
	
	private InputFileFinder() {
		//top secret hidden constructor.
	}

	/**
	 * This is an odd way to get files, but I think its cute, so it says. <3
	 * <br/><br/>
	 * Running a main method in intellij vs running it from the run task will have different working dirs which breaks relative paths to the input file.
	 * As a way around this put the input file into the resources dir and then class load the file to find the path.
	 *
	 * @deprecated replace with getInputFilePath(String fileName)
	 * 
	 * @param classLoader class loader to use.
	 * @param fileName    name of the file.
	 * @return The absolute path to the file.
	 */
	@Deprecated(since="2023-12-01", forRemoval=true)
	public static String getFilePath(ClassLoader classLoader, String fileName) throws URISyntaxException {
		URL resource = classLoader.getResource(fileName);
		if (resource == null) {
			System.err.println(fileName + " not found!");
			throw new IllegalArgumentException("file not found! " + fileName);
		}

		return new File(resource.toURI()).getAbsolutePath();
	}


	/**
	 * This is an odd way to get files, but I think its cute, so it says. <3
	 * <br/><br/>
	 * Running a main method in intellij vs running it from the run task will have different working dirs which breaks relative paths to the input file.
	 * As a way around this put the input file into the resources dir and then class load the file to find the path.
	 *
	 * @param fileName The name of the file.
	 * @return The absolute path to the file.
	 */
	public static Optional<String> getInputFilePath(String fileName) throws URISyntaxException {
		
		URL resource = ClassLoader.getSystemResource(fileName);
		if (resource == null) {
			return Optional.empty();
		}
		
		return Optional.of(new File(resource.toURI()).getAbsolutePath());
	}
}
