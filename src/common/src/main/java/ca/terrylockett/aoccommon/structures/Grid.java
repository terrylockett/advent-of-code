package ca.terrylockett.aoccommon.structures;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

public class Grid {
	public static final char DEFAULT_CHAR = '.';

	public enum Direction {
		N(-1,0), 
		NE(-1, 1), 
		E(0,1), 
		SE(1,1),
		S(1,0), 
		SW(1,-1), 
		W(0,-1), 
		NW(-1,-1);
		
		final int nextRow;
		final int nextCol;
		Direction(int nextRow, int nextCol) {
			this.nextRow = nextRow;
			this.nextCol = nextCol;
		}
	}

	private final char[][] grid;
	private final int rowCount;
	private final int colCount;

	public Grid(int rows, int cols) {
		this(rows, cols, DEFAULT_CHAR);
	}

	public Grid(int rows, int cols, char defaultChar) {
		rowCount = rows;
		colCount = cols;
		grid = new char[rows][cols];

		for (int row = 0; row < rows; row++) {
			for (int col = 0; col < cols; col++) {
				grid[row][col] = defaultChar;
			}
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

	public Optional<Character> peekNeighbour(int row, int col, Direction direction) {
		try {
			Character value = grid[row+direction.nextRow][col+direction.nextCol];
			return Optional.ofNullable(value);
		} catch (IndexOutOfBoundsException e) {
			// Pervert alert, why do bounds checking when you can just wrap in a try/catch
			return Optional.empty();
		}
	}

	
	public static class Cell {
		public final int row;
		public final int col;
		public final char value;

		public Cell(int row, int col, char value) {
			this.row = row;
			this.col = col;
			this.value = value;
		}
	}

	public Iterator<Cell> cellIterator() {
		List<Cell> cells = new ArrayList<>();

		for (int row = 0; row < rowCount; row++) {
			for (int col = 0; col < colCount; col++) {
				Cell cell = new Cell(row, col, getCell(row, col));
				cells.add(cell);
			}
		}

		return cells.iterator();
	}
}