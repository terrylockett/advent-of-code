package ca.terrylockett.aoc2025.day07;

import ca.terrylockett.aoccommon.input.PuzzleInput;
import ca.terrylockett.aoccommon.structures.Grid;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Day07Runner {

	static final String INPUT_FILE_NAME = "input.txt";

	public static void main(String[] args) {
		String input = PuzzleInput.getInput(INPUT_FILE_NAME).orElseThrow();

		Grid grid = initGrid(input);

		System.out.println("2025 day07 part1: " + part1(grid));
		// reuse the populated grid from part 1
		System.out.println("2025 day07 part2: " + part2(grid));
	}

	static long part2(Grid grid) {
		long total = 0;
		Map<GridCell, Long> gridCache = new HashMap<>();

		int lastRowIdx = grid.getRowCount() - 1;
		var lastRow = grid.getRow(lastRowIdx);

		for (int col = 0; col < lastRow.size(); col++) {
			if (lastRow.get(col) == '|') {
				total += findPaths(grid, gridCache, lastRowIdx, col);
			}
		}

		return total;
	}
	static long findPaths(Grid grid, Map<GridCell, Long> gridCache, int row, int col) {
		long count = 0;

		// Hit top
		if (grid.peekNeighbour(row, col, Grid.Direction.N).isEmpty()) {
			return 1;
		}

		// north is pipe
		var n = grid.peekNeighbour(row, col, Grid.Direction.N).orElse('Z');
		if (n == '|') {
			GridCell cell = new GridCell(row, col);
			if (!gridCache.containsKey(cell)) {
				long val = findPaths(grid, gridCache, row - 1, col);
				gridCache.put(cell, val);
			}
			count += gridCache.get(cell);
		}

		// E is splitter and NE is pipe
		var e = grid.peekNeighbour(row, col, Grid.Direction.E).orElse('Z');
		var ne = grid.peekNeighbour(row, col, Grid.Direction.NE).orElse('Z');
		if (e == '^' && ne.equals('|')) {
			GridCell cell = new GridCell(row - 1, col + 1);
			if (!gridCache.containsKey(cell)) {
				long val = findPaths(grid, gridCache, row - 1, col + 1);
				gridCache.put(cell, val);
			}
			count += gridCache.get(cell);
		}

		// W is splitter and NW is pipe
		var w = grid.peekNeighbour(row, col, Grid.Direction.W).orElse('Z');
		var nw = grid.peekNeighbour(row, col, Grid.Direction.NW).orElse('Z');
		if (w == '^' && nw.equals('|')) {
			GridCell cell = new GridCell(row - 1, col - 1);

			if (!gridCache.containsKey(cell)) {
				long val = findPaths(grid, gridCache, row - 1, col - 1);
				gridCache.put(cell, val);
			}
			count += gridCache.get(cell);
		}

		return count;
	}

	static int part1(Grid grid) {
		int totalSplits = 0;

		for (int row = 0; row < grid.getRowCount(); row++) {
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
					grid.setCell(row, col, '|');
				} else if (c == '^') {
					grid.setCell(row, col, '^');
				}
			}
		}
		return grid;
	}

	private static class GridCell {
		final int row;
		final int col;
		public GridCell(int row, int col) {
			this.row = row;
			this.col = col;
		}

		@Override
		public int hashCode() {
			// Top 10 hash functions.
			return String.format("[%d, %d]", row, col).hashCode();
		}

		@Override
		public boolean equals(Object obj) {
			if (!(obj instanceof GridCell)) {
				return false;
			}

			GridCell cell2 = (GridCell) obj;
			return (this.row == cell2.row && this.col == cell2.col);
		}
	}
}
