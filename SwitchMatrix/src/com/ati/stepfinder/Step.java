package com.ati.stepfinder;

public class Step {

	private int x;
	private int y;

	public Step(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof Step)) {
			return false;
		}

		Step that = (Step) obj;

		return that.getX() == this.getX() && that.getY() == this.getY();
	}

	@Override
	public String toString() {
		return "[ "+this.x+", "+this.y+"] ";
	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}
}
