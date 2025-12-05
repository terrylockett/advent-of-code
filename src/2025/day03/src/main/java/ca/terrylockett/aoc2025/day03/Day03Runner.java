package ca.terrylockett.aoc2025.day03;

import ca.terrylockett.aoccommon.resources.Resources;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day03Runner {

	static final String INPUT_FILE_NAME = "input.txt";

	static final List<Pattern> PART1_REGEXS = new ArrayList<>();
	static {
		for (int i = 9; i >= 0; i--) {
			for (int j = 9; j >= 0; j--) {
				PART1_REGEXS.add(Pattern.compile(String.format("(%d).*?(%d)", i, j)));
			}
		}
	}

	public static void main(String[] args) throws Exception {
		String input = Resources.getInput(INPUT_FILE_NAME).orElseThrow();

		System.out.println("2025 day03 part1: " + part1(input));
		// System.out.println("2025 day03 part2: " + part2(input));
	}

	static int part1(String puzzleInput) {

		return puzzleInput.lines().mapToInt(line -> {
			for (Pattern pattern : PART1_REGEXS) {
				Matcher m = pattern.matcher(line);
				if (m.find()) {
					return Integer.parseInt(m.group(1) + m.group(2));
				}
			}
			return 0;
		}).sum();
	}
}
