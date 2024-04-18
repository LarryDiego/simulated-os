package os.scheduler;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import os.OperatingSystem;
import os.SOProcess;
import os.SubProcess;
import os.SystemCallType;
import os.cpu.CpuManager;

public class RoundRobin extends Scheduler {
    private int countExecutedSubProcess = 0;
    private SOProcess processInExecution = null;
    private Map<String, Integer> graphicQuantum = new HashMap<>();
    private final int quantum;

    private LinkedList<SOProcess> queueProcess;
	private LinkedList<SubProcess> queueSubProcesses;
	
    public RoundRobin(int quantum) {
    	this.queueProcess = new LinkedList<SOProcess>();
    	this.queueSubProcesses = new LinkedList<SubProcess>();
    	this.quantum = quantum * 1000;
	}

    public void addProcess(SOProcess process) {
        this.queueProcess.add(process);

        List<SubProcess> subProcesses = (List<SubProcess>) OperatingSystem.systemCall(SystemCallType.READ, process);

        for (SubProcess sp : subProcesses) {
            this.queueSubProcesses.add(sp);
        }

        if (queueProcess != null) {
        	
        	this.processInExecution = this.queueProcess.peek();}
    }

    public SubProcess execute() {
    	if (queueSubProcesses != null && queueProcess != null) {
    		
    		SubProcess element = this.queueSubProcesses.poll();
    		
    		if (element != null) {
    			if (this.processInExecution != null && element.getProcess().getId().equals(this.processInExecution.getId())) {
    				this.countExecutedSubProcess++;
    			}
    			
    			Queue<String> aux = new LinkedList<>();
    			for (SubProcess sp : this.queueSubProcesses) {
    				aux.add(sp.getProcess().getId());
    			}
    			
    			if (!aux.contains(element.getProcess().getId())) {
    				this.queueProcess.poll();
    				this.processInExecution = this.queueProcess.peek();
    				this.countExecutedSubProcess = 0;
    			}
    			
    			Integer valueQuantum = this.graphicQuantum.get(element.getProcess().getId());
    			
    			if (valueQuantum == null && this.countExecutedSubProcess == CpuManager.NUMBER_OF_CORES) {
    				this.graphicQuantum.put(element.getProcess().getId(), CpuManager.CLOCK);
    				
    				if (CpuManager.CLOCK >= this.quantum) {
    					rotate(element.getProcess().getId(), this.countExecutedSubProcess);
    				}
    				
    				this.countExecutedSubProcess = 0;
    			}
    			
    			if (valueQuantum != null && this.countExecutedSubProcess == CpuManager.NUMBER_OF_CORES) {
    				this.graphicQuantum.put(element.getProcess().getId(), CpuManager.CLOCK + this.graphicQuantum.get(element.getProcess().getId()));
    				
    				this.countExecutedSubProcess = 0;
    			}
    			
    			Integer value = this.graphicQuantum.get(element.getProcess().getId());
    			
    			if (!(CpuManager.CLOCK >= this.quantum) && value != null && value >= this.quantum) {
//    				System.out.println(value + "testando");
    				rotate(element.getProcess().getId(), this.countExecutedSubProcess);
    			}
    			
    			return element;
    			
    		}
    	}
        return null;
    }

    public void rotate(String processId, int executedQuantity) {
        if (this.processInExecution != null && this.processInExecution.getInstructions() > this.processInExecution.getInstructionsExecuted()) {
            SubProcess[] subProcessesByProcess = getSubProcessByProcess();

            removeProcessAndSubProcess();

            addProcessAndSubProcessInEnd(subProcessesByProcess);
            this.processInExecution = this.queueProcess.peek();
            if (executedQuantity >= 4) {
            	System.out.println(this.graphicQuantum.get(processId));
            }
            this.countExecutedSubProcess = 0;

            this.graphicQuantum.put(processId, 0);
        }
    }

    private SubProcess[] getSubProcessByProcess() {
        return this.queueSubProcesses.stream()
                .filter(sp -> sp.getProcess().getId().equals(this.processInExecution != null ? this.processInExecution.getId() : ""))
                .toArray(SubProcess[]::new);
    }

    private void removeProcessAndSubProcess() {
        this.queueProcess.poll();

        this.queueSubProcesses.removeIf(sp -> sp.getProcess().getId().equals(this.processInExecution != null ? this.processInExecution.getId() : ""));
    }

    private void addProcessAndSubProcessInEnd(SubProcess[] subProcesses) {
        if (this.processInExecution != null) {
            this.queueProcess.add(this.processInExecution);

            for (SubProcess sp : subProcesses) {
                this.queueSubProcesses.add(sp);
            }
        }
    }

	@Override
	public void close(SOProcess process) {
		this.queueProcess = removeProcessById(this.queueProcess, process.getId());

        this.queueSubProcesses = removeSubProcessByProcessId(this.queueSubProcesses, process.getId());
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
		return graphicQuantum;
	}
}
