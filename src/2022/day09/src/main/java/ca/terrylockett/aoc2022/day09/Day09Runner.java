package ca.terrylockett.aoc2022.day09;

import ca.terrylockett.aoccommon.resources.Resources;

public class Day09Runner {

	public static void main(String[] args) throws Exception {
		String inputFile = Resources.getInputFilePath("input.txt").orElseThrow();

		Rope ropePart1 = new Rope(2);
		ropePart1.processMoves(inputFile);

		System.out.println("Day 09 Part 01: " + ropePart1.getTailKnot().getVisitedLocationsCount());

		Rope ropePart2 = new Rope(10);
		ropePart2.processMoves(inputFile);

		System.out.println("Day 09 Part 02: " + ropePart2.getTailKnot().getVisitedLocationsCount());
	}

}
