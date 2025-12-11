package ca.terrylockett.aoc2025.day06;

import ca.terrylockett.aoccommon.resources.Resources;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static java.util.function.Predicate.not;

public class Day06Runner {

	static final String INPUT_FILE_NAME = "input.txt";

	static final Pattern OPERATOR_TOKEN_PAT = Pattern.compile("\\s*(\\S)");
	static final Pattern NUMBER_TOKEN_PAT = Pattern.compile("\\s*(\\d+)");

	enum Operators {
		ADD, MUL;
	}

	public static void main(String[] args) {
		String input = Resources.getInput(INPUT_FILE_NAME).orElseThrow();

		System.out.println("2025 day06 part1: " + part1(input));
		// System.out.println("2025 day06 part2: " + part2(input));
	}

	static long part1(String puzzleInput) {
		List<String> lines = puzzleInput.lines().filter(not(String::isEmpty)).collect(Collectors.toList());

		// Parse operators
		List<Operators> operators = new ArrayList<>();
		String operatorsLine = lines.remove(lines.size() - 1);
		Matcher m = OPERATOR_TOKEN_PAT.matcher(operatorsLine);
		while (m.find()) {
			if ("*".equals(m.group(1))) {
				operators.add(Operators.MUL);
			} else if ("+".equals(m.group(1))) {
				operators.add(Operators.ADD);
			} else {
				throw new RuntimeException("uh oh bad operator during parsing");
			}
		}


		// init results list
		List<Long> results = new ArrayList<>();
		for (Operators operator : operators) {
			if (operator == Operators.MUL) {
				results.add(1L);
			} else {
				results.add(0L);
			}
		}
		// do math
		for (String line : lines) {
			m = NUMBER_TOKEN_PAT.matcher(line);
			int index = 0;
			while (m.find()) {
				long number = Long.parseLong(m.group(1));
				if (operators.get(index) == Operators.MUL) {
					number *= results.get(index);
				} else {
					number += results.get(index);
				}
				results.set(index, number);
				index++;
			}
		}

		return results.stream().mapToLong(Long::longValue).sum();
	}
}
