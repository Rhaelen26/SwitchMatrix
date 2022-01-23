package com.ati.stepfinder;

import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.ati.matrix.Matrix;

public class StepFinder {

	private ArrayList<Thread> threads;
	private Matrix startMatrix;
	private volatile boolean done;
	private volatile int solutionNum;
	private ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

	public StepFinder(Matrix matrix) {
		this.threads = new ArrayList<Thread>();
		this.startMatrix = matrix;
		this.done = false;
		this.solutionNum = 0;
	}

	public void findSteps() {

		createThreads();

		for (Thread thread : threads) {
			thread.start();
		}
		
		//#4
		executor.scheduleAtFixedRate(new SolutionCounter(this), 10, 10, TimeUnit.SECONDS);
	}

	// The number of Threads might be too much
	private void createThreads() {
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 6; j++) {
				StepThread stepper = new StepThread(startMatrix, i+j*6+1, this);
				threads.add(new Thread(stepper));
			}
		}
	}

	public synchronized void failedSolutionFound() {
		this.solutionNum++;
	}

	public synchronized void solutionFound(String solution) {

		this.done = true;

		executor.shutdown();
        for (Thread thread : this.threads) {
            thread.interrupt();
        }
        //#5
		System.out.println("The Solution is: " + solution);
	}

	public boolean isDone() {
		return done;
	}
	
	public int getSolutionNum() {
		return solutionNum;
	}
}
