package ca.terrylockett.aoccommon.structures;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RangeTest {
	
	@Test
	void testBasic() {
		var range = new Range(10, 25);
		
		assertEquals("(10, 25)", range.toString());
		assertEquals(10L, range.getStart());
		assertEquals(25L, range.getEnd());
		
		range.setStart(5);
		range.setEnd(12);
		assertEquals(5L, range.getStart());
		assertEquals(12L, range.getEnd());
	}
	
	@Test
	void testRangeContainsInclusive() {
		var range = new Range(10, 15);
		
		assertFalse(range.containsInclusive(9));
		assertFalse(range.containsInclusive(16));

		assertTrue(range.containsInclusive(10));
		assertTrue(range.containsInclusive(15));
		assertTrue(range.containsInclusive(12L));
	}
	
}
