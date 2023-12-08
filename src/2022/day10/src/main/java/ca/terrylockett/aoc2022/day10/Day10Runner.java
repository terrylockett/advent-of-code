package ca.terrylockett.aoc2022.day10;

import ca.terrylockett.aoccommon.resources.Resources;

public class Day10Runner {

	public static void main(String[] args) throws Exception {
		String inputFile = Resources.getInputFilePath("input.txt").orElseThrow();

		// part 1
		ClockCircuit clock = new ClockCircuit();
		clock.procesInstructionSet(inputFile);
		System.out.println("Day 10 Part 01: " + clock.calcSignalStrengthsSum());

		// part 2
		System.out.println("Day 10 Part 02: ");
		for (int i = 0; i < 6; i++) {
			System.out.println(clock.renderCrtLine(i));
		}
	}
}
