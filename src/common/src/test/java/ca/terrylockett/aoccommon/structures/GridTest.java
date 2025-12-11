package ca.terrylockett.aoccommon.structures;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class GridTest {
	
	@Test
	void testBasics() {
		var grid = new Grid(5,3);
		
		assertEquals(5, grid.getRowCount());
		assertEquals(3, grid.getColCount());
		
		assertEquals(Grid.DEFAULT_CHAR, grid.getCell(0,0));
		
		grid.setCell(0,0, 'X');
		assertEquals('X', grid.getCell(0,0));
	}
	
	@Test
	void testPeekNeighbor() {
		var grid = new Grid(7,7);
		grid.setCell(4,4,'a');
		grid.setCell(4,5,'b');
		grid.setCell(4,6,'c');
		grid.setCell(5,4,'d');
		grid.setCell(5,5,'e');
		grid.setCell(5,6,'f');
		grid.setCell(6,4,'g');
		grid.setCell(6,5,'h');
		grid.setCell(6,6,'i');
		
		assertEquals('a', grid.peekNeighbour(5,5, Grid.Direction.NW).get());
		assertEquals('b', grid.peekNeighbour(5,5, Grid.Direction.N).get());
		assertEquals('c', grid.peekNeighbour(5,5, Grid.Direction.NE).get());
		assertEquals('f', grid.peekNeighbour(5,5, Grid.Direction.E).get());
		assertEquals('i', grid.peekNeighbour(5,5, Grid.Direction.SE).get());
		assertEquals('h', grid.peekNeighbour(5,5, Grid.Direction.S).get());
		assertEquals('g', grid.peekNeighbour(5,5, Grid.Direction.SW).get());
		assertEquals('d', grid.peekNeighbour(5,5, Grid.Direction.W).get());
	}

	@Test
	void testPeekNeighborBounds() {
		var grid = new Grid(3,3);
		
		assertFalse(grid.peekNeighbour(0,1, Grid.Direction.N).isPresent());
		
		assertFalse(grid.peekNeighbour(0,1, Grid.Direction.NE).isPresent());
		assertFalse(grid.peekNeighbour(1,2, Grid.Direction.NE).isPresent());

		assertFalse(grid.peekNeighbour(1,2, Grid.Direction.E).isPresent());

		assertFalse(grid.peekNeighbour(2,1, Grid.Direction.SE).isPresent());
		assertFalse(grid.peekNeighbour(1,2, Grid.Direction.SE).isPresent());

		assertFalse(grid.peekNeighbour(2,1, Grid.Direction.S).isPresent());

		assertFalse(grid.peekNeighbour(1,0, Grid.Direction.SW).isPresent());
		assertFalse(grid.peekNeighbour(2,1, Grid.Direction.SW).isPresent());

		assertFalse(grid.peekNeighbour(1,0, Grid.Direction.W).isPresent());

		assertFalse(grid.peekNeighbour(0,1, Grid.Direction.NW).isPresent());
		assertFalse(grid.peekNeighbour(1,0, Grid.Direction.NW).isPresent());
		
	}
}
