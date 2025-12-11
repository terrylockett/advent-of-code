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

	enum Operator {
		ADD, MUL;
	}

	public static void main(String[] args) {
		String input = Resources.getInput(INPUT_FILE_NAME).orElseThrow();

		System.out.println("2025 day06 part1: " + part1(input));
		System.out.println("2025 day06 part2: " + part2(input));
	}

	static long part1(String puzzleInput) {
		List<String> lines = puzzleInput.lines().filter(not(String::isEmpty)).collect(Collectors.toList());

		String operatorsLine = lines.remove(lines.size() - 1);
		List<Operator> operators = parseOperators(operatorsLine);

		// init results list
		List<Long> results = new ArrayList<>();
		for (Operator operator : operators) {
			if (operator == Operator.MUL) {
				results.add(1L);
			} else {
				results.add(0L);
			}
		}
		// do math
		for (String line : lines) {
			Matcher m = NUMBER_TOKEN_PAT.matcher(line);
			int index = 0;
			while (m.find()) {
				long number = Long.parseLong(m.group(1));
				if (operators.get(index) == Operator.MUL) {
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

	static long part2(String puzzleInput) {
		List<String> lines = puzzleInput.lines().filter(not(String::isEmpty)).collect(Collectors.toList());

		String operatorsLine = lines.remove(lines.size() - 1);
		List<Operator> operators = parseOperators(operatorsLine);

		// Math time
		long grandTotal = 0L;
		int opIndex = 0;
		long tmpTot = 0;
		for (int i = 0; i < lines.get(0).length(); i++) {
			var colDigits = new StringBuilder();
			for (String line : lines) {
				char character = line.charAt(i);
				if (' ' != character) {
					colDigits.append(character);
				}
			}
			if (colDigits.length() > 0) {
				long num = Long.parseLong(colDigits.toString());
				if (operators.get(opIndex) == Operator.ADD) {
					tmpTot += num;
				} else {
					if (tmpTot == 0) {
						tmpTot++;
					}
					tmpTot *= num;
				}
			} else {
				grandTotal += tmpTot;
				tmpTot = 0;
				opIndex++;
			}
		}
		grandTotal += tmpTot;

		return grandTotal;
	}

	private static List<Operator> parseOperators(String operatorLine) {
		List<Operator> operators = new ArrayList<>();

		Matcher m = OPERATOR_TOKEN_PAT.matcher(operatorLine);
		while (m.find()) {
			if ("*".equals(m.group(1))) {
				operators.add(Operator.MUL);
			} else if ("+".equals(m.group(1))) {
				operators.add(Operator.ADD);
			} else {
				throw new RuntimeException("uh oh bad operator during parsing");
			}
		}
		return operators;
	}

}
