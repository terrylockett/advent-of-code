package ca.terrylockett.aoc2022.day07;

import ca.terrylockett.aoccommon.resources.Resources;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;

public class Day07Runner {

	public static void main(String[] args) throws URISyntaxException, FileNotFoundException {
		String inputFile = Resources.getInputFilePath("input.txt").orElseThrow();

		long part01 = ElfComputerDevice.part01(inputFile);
		System.out.println("Day 07 part 01: " + part01);

		long part02 = ElfComputerDevice.part02(inputFile);
		System.out.println("Day 07 part 02: " + part02);
	}

}
