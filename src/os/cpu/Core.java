package os.cpu;

import os.SubProcess;

public class Core implements Runnable {
	private int id;
	private SubProcess currentSubprocess;
	private int numberOfInstructionsPerClock;
	private int numberOfInstructionsExecuted;
	
	public Core(int numberOfInstructionsPerClock, int id) {
		this.numberOfInstructionsPerClock = numberOfInstructionsPerClock;
		this.id = id;
	}
	
	public Core(int id) {
		this(7, id);
	}
	
	@Override
	public void run() {
		this.numberOfInstructionsExecuted += numberOfInstructionsPerClock;
		if (this.numberOfInstructionsExecuted >= currentSubprocess.getInstructions()) {
			this.finishSubProcess();
		}
	}
	
	private void finishSubProcess() {
		this.currentSubprocess = null;
		this.numberOfInstructionsExecuted = 0;
	}

	public SubProcess getCurrentSubprocess() {
		return currentSubprocess;
	}

	public void setCurrentSubprocess(SubProcess currentSubprocess) {
		this.currentSubprocess = currentSubprocess;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	

}
