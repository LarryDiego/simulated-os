package os.scheduler;

import java.util.Comparator;
import java.util.List;

import os.OperatingSystem;
import os.SOProcess;
import os.SubProcess;
import os.SystemCallType;

public class SJF extends SchedulerQueue {
    public SJF() {
		super(new Comparator<SOProcess>() {
			@Override
			public int compare(SOProcess process1, SOProcess process2) {
				return process1.getTimeToExecute() - process2.getTimeToExecute();
			}
			
		});
	}

    public void addProcess(SOProcess process) {
        this.queueProcess.add(process);
    }

    public SubProcess execute() {
        orderListByTimeToExecute();
        
        if (queueSubProcesses != null) {
        	
        	SubProcess element = this.queueSubProcesses.poll();
        	
        	if (element != null) {
        		return element;
        	}         
        }
        return null;
    }

    private void orderListByTimeToExecute() {
        if (queueProcess != null) {
        	
        	SOProcess process = this.queueProcess.poll();
        	
        	if (process != null) {
        		List<SubProcess> subProcesses = (List<SubProcess>) OperatingSystem.systemCall(SystemCallType.READ, process);
        		
        		for (SubProcess value : subProcesses) {
        			this.queueSubProcesses.add(value);
        		}
        	}
        }
    }
}
