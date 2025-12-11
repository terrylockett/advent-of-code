package ca.terrylockett.aoccommon.structures;

public class Range {

	private long start;
	private long end;
	
	public Range(long start, long end) {
		this.start = start;
		this.end = end;
	}


	public boolean containsInclusive(long value) {
		return value >= start && value <= end;
	}


	public long getStart() {
		return start;
	}

	public void setStart(long start) {
		this.start = start;
	}

	public long getEnd() {
		return end;
	}

	public void setEnd(long end) {
		this.end = end;
	}

	@Override
	public String toString() {
		return String.format("(%d, %d)", start, end);
	}
}
