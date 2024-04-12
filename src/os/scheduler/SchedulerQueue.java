package os.scheduler;

import java.util.PriorityQueue;

import os.SOProcess;
import os.SubProcess;

public class SchedulerQueue extends Scheduler{
	private PriorityQueue<SubProcess> queue;
	
	public SchedulerQueue() {
		this.queue = new PriorityQueue<>();
	}

	@Override
	public void execute(SOProcess process) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void finish(SOProcess process) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isEmpty() {
		return this.queue.isEmpty();
	}

}
