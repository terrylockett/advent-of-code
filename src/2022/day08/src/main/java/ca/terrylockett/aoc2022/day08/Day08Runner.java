package ca.terrylockett.aoc2022.day08;

import ca.terrylockett.aoccommon.resources.Resources;

import java.io.IOException;
import java.net.URISyntaxException;

public class Day08Runner {

	public static void main(String[] args) throws IOException, URISyntaxException {
		String inputFile = Resources.getInputFilePath("input.txt").orElseThrow();

		int part1 = TreeHouseLocator.getNumberOfVisibleTrees_Part1(inputFile);
		System.out.println("Day08 Part01: " + part1);

		int part2 = TreeHouseLocator.getMaxScenicScore_Part2(inputFile);
		System.out.println("Day08 Part02: " + part2);
	}

}
