package ca.terrylockett.aoc2025.day02;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

class Range {
	static final Pattern EXPERT_CSV_PARSER_PATTERN = Pattern.compile(",");

	private final long start;
	private final long end;

	public Range(long start, long end) {
		this.start = start;
		this.end = end;
	}

	public long getStart() {
		return start;
	}
	public long getEnd() {
		return end;
	}

	@Override
	public String toString() {
		return String.format("Start: %d, End: %d", start, end);
	}

	public static List<Range> createRanges(String puzzleInput) {
		List<Range> ranges = new ArrayList<>();
		for (String csvToken : EXPERT_CSV_PARSER_PATTERN.split(puzzleInput)) {
			var rangeTokens = csvToken.split("-");
			long start = Long.parseLong(rangeTokens[0]);
			long end = Long.parseLong(rangeTokens[1]);
			ranges.add(new Range(start, end));
		}
		return ranges;
	}
}
