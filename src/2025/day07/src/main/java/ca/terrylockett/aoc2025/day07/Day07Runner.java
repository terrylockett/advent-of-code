package ca.terrylockett.aoc2025.day07;

import ca.terrylockett.aoccommon.input.PuzzleInput;
import ca.terrylockett.aoccommon.structures.Grid;

import java.util.List;
import java.util.stream.Collectors;

public class Day07Runner {

	static final String INPUT_FILE_NAME = "input.txt";

	public static void main(String[] args) {
		String input = PuzzleInput.getInput(INPUT_FILE_NAME).orElseThrow();

		System.out.println("2025 day07 part1: " + part1(input));
		// System.out.println("2025 day07 part2: " + part2(input));
	}

	public static int part1(String puzzleInput) {
		Grid grid = initGrid(puzzleInput);
		int startRow = 0;
		int startCol = findStartingCol(grid);
		grid.setCell(startRow, startCol, '|');

		int totalSplits = 0;

		for (int row = 0; row < grid.getRowCount() - 1; row++) {
			for (int col = 0; col < grid.getColCount(); col++) {
				char currentValue = grid.getCell(row, col);
				if (currentValue == '.' || currentValue == '^') {
					continue;
				}

				char southChar = grid.peekNeighbour(row, col, Grid.Direction.S).orElse('Z');

				if (southChar == '.') {
					grid.setCell(row + 1, col, '|');
				} else if (southChar == '^') {
					totalSplits++;

					char sw = grid.peekNeighbour(row, col, Grid.Direction.SW).orElse('Z');
					if (sw == '.') {
						grid.setCell(row + 1, col - 1, '|');
					}
					char se = grid.peekNeighbour(row, col, Grid.Direction.SE).orElse('Z');
					if (se == '.') {
						grid.setCell(row + 1, col + 1, '|');
					}
				}
			}
		}

		return totalSplits;
	}

	static Grid initGrid(String puzzleInput) {

		List<String> lines = puzzleInput.lines().collect(Collectors.toList());
		int rows = lines.size();
		int cols = lines.get(0).length();

		Grid grid = new Grid(rows, cols);

		for (int row = 0; row < rows; row++) {
			String line = lines.get(row);
			for (int col = 0; col < cols; col++) {
				char c = line.charAt(col);
				if (c == 'S') {
					grid.setCell(row, col, 'S');
				} else if (c == '^') {
					grid.setCell(row, col, '^');
				}
			}
		}
		return grid;
	}

	static int findStartingCol(Grid grid) {
		for (int i = 0; i < grid.getColCount(); i++) {
			if (grid.getCell(0, i) == 'S') {
				return i;
			}
		}
		throw new RuntimeException("Didnt find Start value on first row of grid.");
	}
}
