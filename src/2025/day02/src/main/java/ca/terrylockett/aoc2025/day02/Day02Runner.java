package ca.terrylockett.aoc2025.day02;

import ca.terrylockett.aoccommon.resources.Resources;

import java.util.ArrayList;
import java.util.List;

public class Day02Runner {

	static final String INPUT_FILE_NAME = "input.txt";

	public static void main(String[] args) {
		String input = Resources.getInput(INPUT_FILE_NAME).orElseThrow();

		System.out.println("2025 day02 part1: " + part1(input));
		// System.out.println("2025 day02 part2: " + part2(input));
	}

	static long part1(String input) {
		List<Long> invalidIds = new ArrayList<>();

		for (Range range : Range.createRanges(input)) {
			for (long i = range.getStart(); i <= range.getEnd(); i++) {
				String productId = String.valueOf(i);
				if (productId.length() % 2 != 0) {
					// skip odd lengths
					continue;
				}
				var first = productId.substring(0, productId.length() / 2);
				var second = productId.substring(productId.length() / 2);

				if (first.equals(second)) {
					invalidIds.add(i);
				}
			}
		}

		return invalidIds.stream().mapToLong(Long::longValue).sum();
	}
}
