package ca.terrylockett.aoc2022.day11;

import ca.terrylockett.aoccommon.inputfilefinder.Resources;

public class Day11Runner {

	public static void main(String[] args) throws Exception {
		String inputFile = Resources.getInputFilePath("input.txt").orElseThrow();

		MonkeyProcessor mp = new MonkeyProcessor();
		mp.processInput(inputFile);
		mp.playNRounds(20);
		System.out.println("Day 11 Part 01: " + mp.getMonkeyBusinessScore());

		mp = new MonkeyProcessor();
		mp.processInput(inputFile);
		mp.setWorry(false);
		mp.playNRounds(10000);
		System.out.println("Day 11 Part 02: " + mp.getMonkeyBusinessScore());
	}
}
