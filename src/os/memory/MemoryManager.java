package os.memory;

import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;

import os.SOProcess;
import os.SubProcess;

public class MemoryManager {
	private SubProcess[][] physicalMemory;
	private Hashtable<String, FrameMemory> logicalMemory;
	private int pageSize;
	
	static int INSTRUCTIONS_PER_PROCESS = 7;
	
	public MemoryManager(int pageSize, int physicalMemorySize) {
		int pages = physicalMemorySize / pageSize;
		this.physicalMemory = new SubProcess[pages][pageSize];
		this.logicalMemory = new Hashtable<String, FrameMemory>();
		this.pageSize = pageSize;
	}
	
	public MemoryManager() {
		this(4, 256);	
	}
	
	public void write(SOProcess process) {
			this.writeWithPaging(process);
	}
	
	private void writeWithPaging(SOProcess process) {
		List<FrameMemory> frames = this.findFramePages(process);
		int spaces = (int) Math.ceil((process.getSubProcesses().size() / this.pageSize));
		List<String> subProcessesIds = process.getSubProcesses();
		
		if (spaces <= frames.size()) {
			int subProcessIndex = 0;
			
			for (int i = 0; i < spaces; i++) {
				FrameMemory frameMemory = frames.get(i);
				for (int j = 0; j < this.pageSize; j++) {
					if (subProcessIndex < process.getSubProcesses().size()) {
						SubProcess subProcess = new SubProcess(subProcessesIds.get(subProcessIndex), INSTRUCTIONS_PER_PROCESS);
						this.physicalMemory[frameMemory.getPageNumber()][j] = subProcess;
						frameMemory.setOffset(j);
						this.logicalMemory.put(subProcess.getId(), frameMemory);
						subProcessIndex++;
						
					} else {
						break;
					}
				}
			}
		} else {
			System.out.println("Page Fault");
		}
	}
	
	private List<FrameMemory> findFramePages(SOProcess process) {
		List<FrameMemory> frames = new LinkedList<>();

		for (int i = 0; i < this.physicalMemory.length; i++) {
			if (this.physicalMemory[i][0] == null) {
				frames.add(new FrameMemory(i, 0));
			}
		}
		return frames;		
	}
	
	public List<SubProcess> read(SOProcess process) {
		List<String> subProcessesIds = process.getSubProcesses();
		List<SubProcess> subProcesses = new LinkedList<>();
		for (String subProcessId : subProcessesIds) {	
			//Endereço da memória física recuperado através da memória lógica
			FrameMemory physicalMemoryAddress = this.logicalMemory.get(subProcessId);
			subProcesses.add(this.physicalMemory[physicalMemoryAddress.getPageNumber()][physicalMemoryAddress.getOffset()]);
		}
		return subProcesses;
	}	
	
	public void delete(SOProcess process) {
        for (int i = process.getMemoryAddress().getStart(); i <= process.getMemoryAddress().getEnd(); i++) {
            if (this.physicalMemory[i] != null && this.physicalMemory[i].equals(process.getId())) {
                this.physicalMemory[i] = null;
            }
        }
        System.out.println("--------------------------------------------------------------------------");
        System.out.println("                  (-) REMOVING THE MEMORY PROCESS (-)");
        System.out.println("PROCESS ID: " + process.getId() + " REMOVED!\nPAGES/SIZE IN MEMORY: " + process.getSizeInMemory() + "\nRELEASED PAGES/INDEXES: " + process.getMemoryAddress().getStart() + " - " + process.getMemoryAddress().getEnd());
        System.out.println("--------------------------------------------------------------------------\n");
        this.memoryInUse();
    }
	
	//Print das etapas
	private void printMemoryStatus() {
		for (int i = 0; i < this.physicalMemory.length; i++) {
			for (int j = 0; j < this.physicalMemory[i].length; j++) {
				SubProcess subProcess = this.physicalMemory[i][j];
				String subProcessId = null;
				if (subProcess != null) {
					subProcessId = subProcess.getId();
				}
				if (j == this.physicalMemory[i].length -1) {
					System.out.println(subProcessId);
				} else {
					System.out.print(subProcessId + " | ");
				}
			}
		}
	}
	private void printInitial(int sizeInMemory, String id) {
        System.out.println("--------------------------------------------------------------------------");
        System.out.println("                  (+) WRITING THE PROCESS IN MEMORY (+)");
        System.out.println("PROCESS ID: " + id + "\nPAGES/SIZE IN MEMORY: " + sizeInMemory + "\n");
    }
	
	private void printCreate(int index, String id) {
        System.out.println("PAGE/INDEX: " + index + " -> PROCESS ID: " + id);
    }
	
	private void printFinish(String id) {
        System.out.println("\nPROCESS ID: " + id + " WRITED WITH SUCCESS!\n");
    }
	
	private void printError(String id, int size) {
        System.out.println("                  (*) ERROR: INSUFFICIENT MEMORY (*)" + "\nPROCESS ID: " + id + ", \nPAGES/SIZE IN MEMORY: " + size);
    }
	
	public void memoryInUse() {
		int memoryInUseCount = 0;
		
		for (int i = 0; i < this.physicalMemory.length; i++) {
			if (this.physicalMemory[i] != null) {
				memoryInUseCount++;
			}
		}
		
		int memoryPercentage = (memoryInUseCount*100)/this.physicalMemory.length;
		System.out.println("--------------------------------------------------------------------------");
        System.out.println("                  (%) MEMORY IN USE (%)");
        System.out.println("MEMORY IN USE: " + memoryPercentage + "% ");
      
	}
	
	
}
