package com.ati.stepfinder;

public class SolutionCounter implements Runnable {

	StepFinder parent;
	
	public SolutionCounter(StepFinder stepFinder) {
		this.parent=stepFinder;
	}

	@Override
	public void run() {
		System.out.println(parent.getSolutionNum()+ " Solutions have been tried");
	}

}
