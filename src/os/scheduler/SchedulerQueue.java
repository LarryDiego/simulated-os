package os.scheduler;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;

import os.SOProcess;
import os.SubProcess;

public abstract class SchedulerQueue extends Scheduler {
    protected PriorityQueue<SOProcess> queueProcess;
    protected LinkedList<SubProcess> queueSubProcesses;

    public SchedulerQueue(Comparator comparatorSOProcess) {
        super();
        this.queueProcess = new PriorityQueue(comparatorSOProcess);
        this.queueSubProcesses = new LinkedList();
    }

    public void close(SOProcess process) {
        this.queueProcess = removeProcessById(this.queueProcess, process.getId());

        this.queueSubProcesses = removeSubProcessByProcessId(this.queueSubProcesses, process.getId());
    }

    private PriorityQueue<SOProcess> removeProcessById(PriorityQueue<SOProcess> queueProcess2, String id) {
    	Comparator<SOProcess> comparator = new Comparator<SOProcess>() {
			@Override
			public int compare(SOProcess process1, SOProcess process2) {
				return 1;
			}
			
		};
		PriorityQueue<SOProcess> result = new PriorityQueue<SOProcess>(comparator);
        for (SOProcess p : queueProcess2) {
            if (p.getId() != id) {
                result.add(p);
            }
        }

        return result;
    }

    private LinkedList<SubProcess> removeSubProcessByProcessId(LinkedList<SubProcess> queueSubProcesses2, String id) {
		LinkedList<SubProcess> result = new LinkedList<SubProcess>();
        for (SubProcess p : queueSubProcesses2) {
            if (p.getId() != id) {
                result.add(p);
            }
        }

        return result;
    }
    
    @Override
	public Map<String, Integer> getQuantumTable() {
		return null;
	}
}
