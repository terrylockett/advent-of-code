package ca.terrylockett.aoc2025.day05;

import ca.terrylockett.aoccommon.resources.Resources;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day05Runner {

	static final String INPUT_FILE_NAME = "input.txt";
	static final Pattern RANGE_PATTERN = Pattern.compile("(\\d+)-(\\d+)");

	public static void main(String[] args) throws Exception {
		String input = Resources.getInput(INPUT_FILE_NAME).orElseThrow();

		System.out.println("2025 day05 part1: " + part1(input));
		// System.out.println("2025 day05 part2: " + part2(input));
	}

	static long part1(String puzzleInput) {
		List<Range> ranges = getRanges(puzzleInput);
		List<Long> ids = getIds(puzzleInput);
		long total = 0L;

		outer : for (Long id : ids) {
			for (Range range : ranges) {
				if (range.contains(id)) {
					total++;
					continue outer;
				}

			}
		}

		return total;
	}

	static List<Range> getRanges(String puzzleInput) {
		List<Range> ranges = new ArrayList<>();

		try (Scanner scan = new Scanner(puzzleInput)) {
			while (scan.hasNext()) {
				Matcher m = RANGE_PATTERN.matcher(scan.nextLine());
				if (!m.find()) {
					break;
				}
				long start = Long.parseLong(m.group(1));
				long end = Long.parseLong(m.group(2));
				ranges.add(new Range(start, end));
			}
		}

		return ranges;
	}

	static List<Long> getIds(String puzzleInput) {
		List<Long> ids = new ArrayList<>();

		try (Scanner scan = new Scanner(puzzleInput)) {
			boolean parseIds = false;
			while (scan.hasNext()) {
				String line = scan.nextLine();
				if ("".equals(line)) {
					parseIds = true;
					continue;
				}
				if (!parseIds) {
					continue;
				}

				long id = Long.parseLong(line);
				ids.add(id);
			}
		}

		return ids;
	}

}
