package ca.terrylockett.aoc2025.day05;

public class Range {

	final long start;
	final long end;

	public Range(long start, long end) {
		this.start = start;
		this.end = end;
	}

	public boolean contains(long value) {
		return value >= start && value <= end;
	}
}
