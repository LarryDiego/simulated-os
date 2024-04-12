package os.scheduler;

import os.SOProcess;
import os.cpu.CpuManager;

//NÃO coloca subprocessos, e sim os processos grandes.
public abstract class Scheduler {
	private CpuManager cpuManager;
	
	public Scheduler() {
		this.cpuManager = new CpuManager();
	}	
	
	public CpuManager getCpuManager() {
		return cpuManager;
	}

	public abstract void execute(SOProcess process);
	public abstract void finish(SOProcess process);
	public abstract boolean isEmpty();

}
