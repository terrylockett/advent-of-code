package ca.terrylockett.aoc2025.day04;

import java.util.Optional;

public class Grid {

	public enum Direction {
		N, NE, E, SE, S, SW, W, NW;
	}

	private final char[][] grid;
	private final int rowCount;
	private final int colCount;

	public Grid(int rows, int cols) {
		rowCount = rows;
		colCount = cols;
		grid = new char[rows][cols];

		for (int row = 0; row < rows; row++) {
			for (int col = 0; col < cols; col++) {
				grid[row][col] = '.';
			}
		}
	}

	public Optional<Character> getNeighbor(int row, int col, Direction direction) {
		try {
			Character value = null;
			switch (direction) {
				case N :
					value = grid[row - 1][col];
					break;
				case NE :
					value = grid[row - 1][col + 1];
					break;
				case E :
					value = grid[row][col + 1];
					break;
				case SE :
					value = grid[row + 1][col + 1];
					break;
				case S :
					value = grid[row + 1][col];
					break;
				case SW :
					value = grid[row + 1][col - 1];
					break;
				case W :
					value = grid[row][col - 1];
					break;
				case NW :
					value = grid[row - 1][col - 1];
					break;
			}
			return Optional.ofNullable(value);
		} catch (IndexOutOfBoundsException e) {
			// Pervert alert, why do bounds checking when you can just wrap in a try/catch 
			return Optional.empty();

		}
	}

	public void setCell(int row, int col, char value) {
		this.grid[row][col] = value;
	}

	public char getCell(int row, int col) {
		return this.grid[row][col];
	}

	public int getRowCount() {
		return rowCount;
	}

	public int getColCount() {
		return colCount;
	}
}
