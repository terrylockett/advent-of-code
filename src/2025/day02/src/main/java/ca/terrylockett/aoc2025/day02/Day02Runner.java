package ca.terrylockett.aoc2025.day02;

import ca.terrylockett.aoccommon.resources.Resources;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day02Runner {

	static final String INPUT_FILE_NAME = "input.txt";
	static final int DIVISOR_CACHE_SIZE = 10;

	static final Map<Integer, Pattern> DIVISOR_PATTERNS = new HashMap<>();
	static final Map<Integer, Set<Integer>> WHOLE_DIVISORS = new HashMap<>();
	static {
		for (int i = 1; i <= DIVISOR_CACHE_SIZE; i++) {
			DIVISOR_PATTERNS.put(i, Pattern.compile(String.format("(\\w{%d})", i)));
			WHOLE_DIVISORS.put(i, getWholeDivisors(i));
		}
	}

	public static void main(String[] args) {
		String input = Resources.getInput(INPUT_FILE_NAME).orElseThrow();
		List<Range> ranges = Range.createRanges(input);

		System.out.println("2025 day02 part1: " + part1(ranges));
		System.out.println("2025 day02 part2: " + part2(ranges));
	}

	static long part1(List<Range> ranges) {
		List<Long> invalidIds = new ArrayList<>();

		for (Range range : ranges) {
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

	static long part2(List<Range> ranges) {
		List<Long> invalidIds = new ArrayList<>();

		for (Range range : ranges) {
			for (long i = range.getStart(); i <= range.getEnd(); i++) {
				if (isInvalidProductIdPt2(i)) {
					invalidIds.add(i);
				}
			}
		}

		return invalidIds.stream().mapToLong(Long::longValue).sum();
	}

	static boolean isInvalidProductIdPt2(long id) {
		String productId = String.valueOf(id);
		if (productId.length() == 1) {
			return false;
		}
		Set<Integer> wholeDivisors = WHOLE_DIVISORS.get(productId.length());

		for (Integer divisor : wholeDivisors) {
			Matcher m = DIVISOR_PATTERNS.get(divisor).matcher(productId);

			Set<String> groups = new HashSet<>();
			while (m.find()) {
				groups.add(m.group(1));
				if (groups.size() > 1) {
					break;
				}
			}
			if (groups.size() == 1) {
				return true;
			}
		}
		return false;
	}

	private static Set<Integer> getWholeDivisors(int stringLength) {
		Set<Integer> wholeDivisors = new HashSet<>(Set.of(1));
		for (int i = 2; i <= stringLength / 2; i++) {
			if (stringLength % i == 0) {
				wholeDivisors.add(i);
			}
		}
		return wholeDivisors;
	}
}
