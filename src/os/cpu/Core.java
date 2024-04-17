package os.cpu;

import os.SubProcess;

public class Core {
	private int id;
	private int numberOfInstructionsByClock;
	private SubProcess subProcess;

	public Core(int id, int numberOfInstructionsByClock) {
		this.id = id;
		this.numberOfInstructionsByClock = numberOfInstructionsByClock;
		this.subProcess = null;
	}

	public void run() {
		if (this.subProcess != null) {
			System.out.print("CPU (Core " + this.id + "):");
			System.out.println(" " + this.subProcess.getId());

			finish();
		}
	}

	private void finish() {
		if (this.subProcess != null && this.subProcess.getProcess().getInstructions() > this.subProcess.getProcess()
				.getInstructionsExecuted()) {
			this.subProcess.finish();
			this.subProcess.getProcess().setInstructionsExecuted(this.subProcess.getInstructions());
			this.subProcess.getProcess().checkSubProcessConclusions();
			this.subProcess = null;
		}
	}

	public int getId() {
		return this.id;
	}

	public SubProcess getSubProcess() {
		return this.subProcess;
	}

	public void setSubProcess(SubProcess subProcess) {
		this.subProcess = subProcess;
	}
}
