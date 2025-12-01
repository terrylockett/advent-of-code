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
		System.out.println("2025 day01 part1: " + dial.part1(input));

		dial.reset();
		System.out.println("2025 day01 part2: " + dial.part2(input));
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

	public int part2(String input) throws FileNotFoundException {
		FileInputStream fin = new FileInputStream(input);
		try (Scanner scanner = new Scanner(fin)) {
			while (scanner.hasNext()) {
				String line = scanner.nextLine();

				String dir = line.substring(0, 1);
				int clicks = Integer.parseInt(line.substring(1));
				if ("L".equals(dir)) {
					clicks *= -1;
				}

				Dial.part2RotateDial(this, clicks);

			}
		}

		return zeroCount;
	}

	static void part2RotateDial(Dial dial, int clicks) {

		boolean isRight = clicks > 0;
		int inc = 1;
		if (!isRight) {
			inc = -1;
		}

		clicks = Math.abs(clicks);

		for (int i = 0; i < clicks; i++) {
			// move
			dial.position += inc;
			if (dial.position == 100) {
				dial.position = 0;
			}
			if (dial.position == -1) {
				dial.position = 99;
			}
			// check
			if (dial.position == 0) {
				dial.zeroCount++;
			}
		}
	}

	public void reset() {
		this.position = DIAL_START_POSITION;
		this.zeroCount = 0;
	}

	public int getPosition() {
		return position;
	}

	public int getZeroCount() {
		return zeroCount;
	}

	void setPosition(int position) {
		this.position = position;
	}
}
