package ca.terrylockett.aoc2025.day04;

import ca.terrylockett.aoccommon.resources.Resources;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Day04Runner {

	static final String INPUT_FILE_NAME = "input.txt";

	public static void main(String[] args) {
		String input = Resources.getInput(INPUT_FILE_NAME).orElseThrow();

		System.out.println("2025 day04 part1: " + part1(input));
		System.out.println("2025 day04 part2: " + part2(input));
	}

	static long part2(String puzzleInput) {
		Grid grid = createGrid(puzzleInput);
		int validRolls = 0;

		List<Grid.Cell> cellsToRemove = new ArrayList<>();

		do {
			for (var cell : cellsToRemove) {
				grid.setCell(cell.row, cell.col, '.');
			}
			cellsToRemove.clear();

			for (var itr = grid.getIterator(); itr.hasNext();) {
				Grid.Cell cell = itr.next();
				if (cell.value != '@') {
					continue;
				}

				int currentCellNeighborCount = 0;
				for (var direction : Grid.Direction.values()) {
					char c = grid.getNeighbor(cell.row, cell.col, direction).orElse('.');
					if ('@' == c) {
						currentCellNeighborCount++;
					}
				}

				if (currentCellNeighborCount < 4) {
					validRolls++;
					cellsToRemove.add(cell);
				}
			}
		} while (!cellsToRemove.isEmpty());

		return validRolls;
	}

	static long part1(String puzzleInput) {
		Grid grid = createGrid(puzzleInput);
		int validRolls = 0;

		for (var itr = grid.getIterator(); itr.hasNext();) {
			Grid.Cell cell = itr.next();
			if (cell.value != '@') {
				continue;
			}

			int currentCellNeighborCount = 0;
			for (var direction : Grid.Direction.values()) {
				char c = grid.getNeighbor(cell.row, cell.col, direction).orElse('.');
				if ('@' == c) {
					currentCellNeighborCount++;
				}
			}

			if (currentCellNeighborCount < 4) {
				validRolls++;
			}
		}

		return validRolls;
	}

	static Grid createGrid(String puzzleInput) {

		List<String> lines = puzzleInput.lines().collect(Collectors.toList());
		Grid grid = new Grid(lines.size(), lines.get(0).length());

		for (int row = 0; row < lines.size(); row++) {
			String line = lines.get(row);
			char[] chars = line.toCharArray();
			for (int col = 0; col < line.length(); col++) {
				char c = chars[col];
				if (c == '@') {
					grid.setCell(row, col, '@');
				}
			}
		}
		return grid;
	}

}
