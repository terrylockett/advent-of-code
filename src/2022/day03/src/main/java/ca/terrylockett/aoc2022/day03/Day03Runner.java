package ca.terrylockett.aoc2022.day03;

import ca.terrylockett.aoccommon.resources.Resources;

public class Day03Runner {

	final static String INPUT_FILE_NAME = "input.txt";

	public static void main(String[] args) throws Exception {

		String inputFilePath = Resources.getInputFilePath(INPUT_FILE_NAME).orElseThrow();

		// ******** Part 01 ********
		System.out.println("Day3 Part1: " + RuckSackValidator.findDuplicateItemsTotal(inputFilePath));

		// ******** Part 02 ********
		System.out.println("Day3 Part2: " + RuckSackValidator.findBadgeTotals(inputFilePath));
	}

}
