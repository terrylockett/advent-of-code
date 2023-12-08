package ca.terrylockett.aoc2022.day04;

import ca.terrylockett.aoccommon.inputfilefinder.Resources;

public class Day04Runner {

	public static void main(String[] args) throws Exception {
		String inputFile = Resources.getInputFilePath("input.txt").orElseThrow();

		// ***** part 01 *****
		int count = CampAssignmentCleaner.findAssignmentsErrorsCount(inputFile);
		System.out.println("Part 1: " + count);

		// ***** part 01 *****
		count = CampAssignmentCleaner.findAssignmentsErrorsCount(inputFile, true);
		System.out.println("Part 2: " + count);

	}

}
