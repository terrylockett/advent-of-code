package ca.terrylockett.aoc2025.day05;

import ca.terrylockett.aoccommon.resources.Resources;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day05Runner {

	static final String INPUT_FILE_NAME = "input.txt";
	static final Pattern RANGE_PATTERN = Pattern.compile("(\\d+)-(\\d+)");

	public static void main(String[] args) {
		String input = Resources.getInput(INPUT_FILE_NAME).orElseThrow();

		System.out.println("2025 day05 part1: " + part1(input));
		System.out.println("2025 day05 part2: " + part2(input));
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

	static long part2(String puzzleInput) {
		List<Range> ranges = getRangesMerged(puzzleInput);
		long total = 0L;

		for (Range range : ranges) {
			total += (range.end - range.start) + 1;
		}

		return total;
	}

	/**
	 * Merge all the ranges in the puzzle input.
	 * 
	 * <pre>
	 *Example:
	 *1-5
	 *3-7
	 *10-15
	 * 
	 *Will become:
	 *1-7
	 *10-15
	 * </pre>
	 * 
	 * @param puzzleInput
	 * @return
	 */
	static List<Range> getRangesMerged(String puzzleInput) {
		List<Range> ranges = new ArrayList<>();

		try (Scanner scan = new Scanner(puzzleInput)) {
			while (scan.hasNext()) {
				Matcher m = RANGE_PATTERN.matcher(scan.nextLine());
				if (!m.find()) {
					break;
				}
				long start = Long.parseLong(m.group(1));
				long end = Long.parseLong(m.group(2));
				Range nRange = new Range(start, end);

				ranges = mergeRange(ranges, nRange);
			}
		}

		return ranges;
	}

	static List<Range> mergeRange(List<Range> ranges, Range toMerge) {
		boolean anyOverlap = false;
		Range targetRange = null;
		for (var tmp : ranges) {
			if (null != slamRanges(toMerge, tmp)) {
				anyOverlap = true;
				targetRange = tmp;
				break;
			}
		}
		// no overlaps so add the range and get outta here.
		if (!anyOverlap) {
			ranges.add(toMerge);
			return ranges;
		}

		List<Range> tmpList = new ArrayList<>(ranges);
		tmpList.remove(targetRange);
		return mergeRange(tmpList, slamRanges(targetRange, toMerge));
	}

	/**
	 * No doubts in my mind that this is the easiest way to implement merging
	 * ranges. There is no possibility that I made a very confusing mess in here.
	 * 
	 * @param first
	 * @param second
	 * @return
	 */
	static Range slamRanges(Range first, Range second) {

		if (first.start > second.start && first.end < second.end) {
			return second;
		}
		if (second.start > first.start && second.end < first.end) {
			return first;
		}

		if (first.start <= second.end && first.start >= second.start) {
			if (first.end >= second.end) {
				return new Range(second.start, first.end);
			}
			return second;
		}
		if (first.end >= second.start && first.end <= second.end) {
			if (first.start <= second.start) {
				return new Range(first.start, second.end);
			}
			return first;
		}
		return null;
	}

}
