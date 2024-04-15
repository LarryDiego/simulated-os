package os.scheduler.strategy;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

import os.OperatingSystem;
import os.SOProcess;
import os.SubProcess;
import os.SystemCallType;
import os.cpu.Core;
import os.scheduler.Scheduler;

public class Priority extends Scheduler{
	private PriorityQueue<SubProcess> queue;
	private int order;
	
	public Priority(int order) {
		super();
		this.order = order;
		
		Comparator<SubProcess> comparator = new Comparator<SubProcess>() {
			@Override
			public int compare(SubProcess subProcess1, SubProcess subProcess2) {
				return 0;
			}
			
		};
		this.queue = new PriorityQueue<>(comparator);
	}

	@Override
	public void execute(SOProcess process) {
		List<SubProcess> subProcesses = OperatingSystem.systemCall(SystemCallType.READ_PROCESS, process);
		for (SubProcess subProcess : subProcesses) {
			this.queue.add(subProcess);
		}
		while (!this.queue.isEmpty()) {
			Core[] cores = this.getCpuManager().getCores();
			for (int i = 0; i < cores.length; i++) {
				if (cores[i].getCurrentSubprocess() == null) {
					this.getCpuManager().registerProcess(i, this.queue.poll());
				}
			}
		}
		
	}

	@Override
	public void finish(SOProcess process) {
		// TODO Auto-generated method stub
		
	}

}
