package ca.terrylockett.aoc2025.day09;

import ca.terrylockett.aoccommon.input.PuzzleInput;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Day09Runner {

	static final String INPUT_FILE_NAME = "input.txt";
	static final Pattern INPUT_PARSE_PAT = Pattern.compile("(\\d+),(\\d+)");

	public static void main(String[] args) {
		String input = PuzzleInput.getInput(INPUT_FILE_NAME).orElseThrow();

		System.out.println("2025 day09 part1: " + part1(input));
		// System.out.println("2025 day09 part2: " + part2(input));
	}

	static long part1(String puzzleInput) {
		List<Tile> tiles = parseInput(puzzleInput);

		long largestArea = 0;
		for (int i = 0; i < tiles.size(); i++) {
			for (int k = i + 1; k < tiles.size(); k++) {
				Tile t1 = tiles.get(i);
				Tile t2 = tiles.get(k);

				long area = getArea(t1, t2);
				if (area > largestArea) {
					largestArea = area;
				}
			}
		}

		return largestArea;
	}

	static long getArea(Tile t1, Tile t2) {
		long x = Math.abs(t2.x - t1.x) + 1L;
		long y = Math.abs(t2.y - t1.y) + 1L;

		return x * y;
	}

	static List<Tile> parseInput(String puzzleInput) {
		List<Tile> tiles = new ArrayList<>();
		var lines = puzzleInput.lines().collect(Collectors.toList());

		for (String line : lines) {
			Matcher m = INPUT_PARSE_PAT.matcher(line);
			if (m.find()) {
				long x = Long.parseLong(m.group(1));
				long y = Long.parseLong(m.group(2));
				tiles.add(new Tile(x, y));
			}
		}
		return tiles;
	}

	static class Tile {
		final long x;
		final long y;

		public Tile(long x, long y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public String toString() {
			return String.format("(%s, %s)", x, y);
		}
	}

}
