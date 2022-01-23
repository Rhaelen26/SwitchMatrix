package com.ati.stepfinder;

import java.util.ArrayList;

import com.ati.matrix.Matrix;

public class StepThread implements Runnable {

	private Matrix startMatrix;
	private StepFinder listener;
	private ArrayList<Step> allSteps;
	private int length;

	public StepThread(Matrix matrix, int length, StepFinder stepFinder) {
		this.startMatrix = matrix;
		this.listener = stepFinder;
		this.allSteps = getAllSteps(matrix);
		this.length = length;
	}

	private ArrayList<Step> getAllSteps(Matrix matrix) {
		ArrayList<Step> possibleSteps = new ArrayList<>();
		for (int i = 0; i < matrix.getStates().length; i++) {
			for (int j = 0; j < matrix.getStates()[i].length; j++) {
				possibleSteps.add(new Step(i, j));
			}
		}
		return possibleSteps;
	}

	@Override
	public void run() {
		ArrayList<Step> stepsTaken = new ArrayList<>();
		createAndCheckSolutions(allSteps, stepsTaken, 0, allSteps.size() - 1, 0);
	}

	private void createAndCheckSolutions(ArrayList<Step> all, ArrayList<Step> saved, int min, int max, int saveIndex) {
		if (saveIndex == length && !listener.isDone()) {
			if (checkSolution(saved)) {
				listener.solutionFound(saved.toString());
			} else {
				listener.failedSolutionFound();
			}
		} else if(!listener.isDone()){
			for (int i = min; i <= max && max - i + 1 >= length - saveIndex; i++) {
				if(saved.size()<saveIndex+1) {
					saved.add(all.get(i));
				}else {
					saved.set(saveIndex, all.get(i));
				}
				createAndCheckSolutions(all, saved, i + 1, max, saveIndex + 1);
			}
		}
	}

	private boolean checkSolution(ArrayList<Step> stepsTaken) {
		return startMatrix.equals(executeSteps(new Matrix(), stepsTaken));
	}

	private Matrix executeSteps(Matrix matrix, ArrayList<Step> stepsTaken) {
		Matrix resultMatrix = matrix;
		for (Step step : stepsTaken) {
			resultMatrix.press(step.getX(), step.getY());
		}
		return resultMatrix;
	}

}
