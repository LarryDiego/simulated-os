package os.scheduler;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

import os.OperatingSystem;
import os.SOProcess;
import os.SubProcess;
import os.SystemCallType;

public class Lottery extends Scheduler {
	private LinkedList<SOProcess> processList;
	private LinkedList<SubProcess> subProcessList;
    public Lottery() {
		this.processList = new LinkedList<SOProcess>();
		this.subProcessList = new LinkedList<SubProcess>();
	}

	public void addProcess(SOProcess process) {
        this.processList.add(process);
    }

    public SubProcess execute() {
        randomFirstProcess();
        if (subProcessList != null) {
        	
        	
        	SubProcess element = this.subProcessList.poll();
        	
        	if (element != null) {
        		return element;
        	}
        }
        return null;
    }

    private void randomFirstProcess() {
    	if (processList != null && !processList.isEmpty()) {
    		
    		int randomIndex = (int) (Math.random() * this.processList.size());
    		SOProcess process = this.processList.get(randomIndex);
    		
    		if (process != null) {
    			List<SubProcess> subProcesses = (List<SubProcess>) OperatingSystem.systemCall(SystemCallType.READ, process);
    			
    			for (SubProcess value : subProcesses) {
    				this.subProcessList.add(value);
    			}
    			
    			this.processList.removeIf(p -> p.getId() == process.getId());
    		}
    	}
    }

	@Override
	public void close(SOProcess process) {
		this.processList = removeProcessById(this.processList, process.getId());

        this.subProcessList = removeSubProcessByProcessId(this.subProcessList, process.getId());
	}
	
	private LinkedList<SOProcess> removeProcessById(LinkedList<SOProcess> queueProcess2, String id) {
		LinkedList<SOProcess> result = new LinkedList<SOProcess>();
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
		// TODO Auto-generated method stub
		return null;
	}
}
