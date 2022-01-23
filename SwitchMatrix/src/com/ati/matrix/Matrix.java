package com.ati.matrix;

import java.util.Random;

public class Matrix {

	private SwitchState[][] states;

	public Matrix() {
		states = new SwitchState[6][6];
		this.clearMatrix();
	}

	private void clearMatrix() {
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 6; j++) {
				states[i][j] = SwitchState.UP;
			}
		}
	}

	//by using Matrix.press() it is guaranteed that the matrix is solveable
	public void initializeMatrix() {
		Random rand = new Random();
		
		for(int k = 0; k<1+rand.nextInt(100);k++) {
			this.press(rand.nextInt(6), rand.nextInt(6));
		}
	}

	public void press(int x, int y) {
		if (x < 0 || x > 5 || y < 0 || y > 5) {
			System.out.println("The Matrix's size is 6*6, you cannot press anything outside of it!");
		} else {
			changeState(x, y);
			if (!(x - 1 < 0)) {
				changeState(x - 1, y);
			}
			if (!(x + 1 > 5)) {
				changeState(x + 1, y);
			}
			if (!(y - 1 < 0)) {
				changeState(x, y - 1);
			}
			if (!(y + 1 > 5)) {
				changeState(x, y + 1);
			}
		}
	}

	private void changeState(int x, int y) {
		switch (this.states[x][y]) {
		case UP:
			this.states[x][y] = SwitchState.DOWN;
			break;

		case DOWN:
			this.states[x][y] = SwitchState.UP;
			break;

		default:
			break;
		}
	}

	public void printMatrix() {
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 6; j++) {
				System.out.print(states[i][j] + "\t");
			}
			System.out.println();
		}
	}

	public SwitchState[][] getStates() {
		return this.states;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof Matrix)) {
			return false;
		}

		Matrix that = (Matrix) obj;

		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 6; j++) {
				if (!(that.getStates()[i][j] == this.getStates()[i][j])) {
					return false;
				}
			}
		}

		return true;
	}
}
