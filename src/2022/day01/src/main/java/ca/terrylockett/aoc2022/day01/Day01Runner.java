package ca.terrylockett.aoc2022.day01;

import ca.terrylockett.aoccommon.inputfilefinder.Resources;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;

public class Day01Runner {

	public static void main(String[] args) throws URISyntaxException, FileNotFoundException {

		String filePath = Resources.getInputFilePath("input.txt").orElseThrow();

		// ***** part 01 *****//
		int maxCals = DumbElfCalorieTracker.findTheFoodHog(filePath);
		System.out.println("part1 - biggest calorie total: " + maxCals);

		// ***** part 02 *****//
		int threeMaxCals = DumbElfCalorieTracker.findTheFoodHog(filePath, 3);
		System.out.println("part2 - Three biggest calorie totals: " + threeMaxCals);
	}
}