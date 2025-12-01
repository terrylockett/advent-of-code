package ca.terrylockett.aoc2025.day01;

import ca.terrylockett.aoccommon.resources.Resources;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day01Runner {

	static final String INPUT_FILE_NAME = "input.txt";

	public static void main(String[] args) throws Exception {
		String input = Resources.getInputFilePath(INPUT_FILE_NAME).orElseThrow();

		var dial = new Dial();
		System.out.println("202DTreeFindart1: " + dial.part1(input));
		// System.out.println("2025 day01 part2: " + part2(input));
	}
}

class Dial {
	public static final int DIAL_START_POSITION = 50;
	public static final int DIAL_MAX = 100;

	private int position = DIAL_START_POSITION;
	private int zeroCount = 0;

	public int part1(String input) throws FileNotFoundException {
		FileInputStream fin = new FileInputStream(input);
		try (Scanner scanner = new Scanner(fin)) {
			while (scanner.hasNext()) {
				String line = scanner.nextLine();

				String dir = line.substring(0, 1);
				int clicks = Integer.parseInt(line.substring(1));
				if ("L".equals(dir)) {
					clicks *= -1;
				}

				this.position = (this.position + clicks) % DIAL_MAX;
				if (0 == this.position) {
					this.zeroCount++;
				}
				if (position < 0) {
					position = DIAL_MAX + position;
				}

			}
		}

		return zeroCount;
	}

	public int getPosition() {
		return position;
	}

	public int getZeroCount() {
		return zeroCount;
	}
}
