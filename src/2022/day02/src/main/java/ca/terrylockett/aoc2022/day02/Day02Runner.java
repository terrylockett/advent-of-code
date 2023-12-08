package ca.terrylockett.aoc2022.day02;

import ca.terrylockett.aoccommon.inputfilefinder.Resources;

public class Day02Runner {

	public static void main(String[] args) throws Exception {

		String filePath = Resources.getInputFilePath("input.txt").orElseThrow();

		// ******** part 01 **********
		System.out.println("Part 01 Game Total: " + RockPaperScissorsGame.playGames(filePath));

		// ******** part 02 **********
		System.out.println("Part 02 Game Total: " + RockPaperScissorsGame.playGames02(filePath));

	}
}
