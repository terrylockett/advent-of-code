package ca.terrylockett.aoc2025.day03;

import ca.terrylockett.aoccommon.resources.Resources;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

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
		System.out.println("2025 day03 part2: " + part2(input));
	}

	static long part2(String puzzleInput) {
		List<String> lines = puzzleInput.lines().collect(Collectors.toList());
		var total = 0L;
		for (String line : lines) {
			var characters = new ArrayList<String>();
			int matchIndex = 0;
			for (int lookaheadIdx = 11; lookaheadIdx >= 0; lookaheadIdx--) {
				for (int i = 9; i >= 0; i--) {
					String regex = String.format("(%d)(?=\\d{%d})", i, lookaheadIdx);
					Matcher m = Pattern.compile(regex).matcher(line);
					if (m.find(matchIndex)) {
						characters.add(m.group(1));
						matchIndex = m.end();
						break;
					}
				}
			}

			String joltageStr = String.join("", characters);
			long joltage = Long.parseLong(joltageStr);
			total += joltage;
		}

		return total;
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
