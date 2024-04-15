package os;

import java.util.LinkedList;
import java.util.List;

import os.memory.MemoryAddress;

public class SOProcess {
	private String id;
	private int sizeInMemory;
	private MemoryAddress memoryAddress;
	
	private int numberOfInstructions;
	private List<String> subProcesses;
	private static int count;
	
	
	public SOProcess(int sizeInMemory) {
		count++;
		this.id = "P" + count;
		this.sizeInMemory = sizeInMemory;
		this.subProcesses = this.getSubProcesses();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getSizeInMemory() {
		return sizeInMemory;
	}

	public void setSizeInMemory(int sizeInMemory) {
		this.sizeInMemory = sizeInMemory;
	}

	public MemoryAddress getMemoryAddress() {
		return memoryAddress;
	}

	public void setMemoryAddress(MemoryAddress memoryAddress) {
		this.memoryAddress = memoryAddress;
	}

	public List<String> getSubProcesses() {
		if(this.subProcesses == null || this.subProcesses.isEmpty()) {
			List<String> subProcessesSO = new LinkedList<>();
			for (int i = 0; i < this.getSizeInMemory(); i++) {
				subProcessesSO.add(this.getId());
			}
			this.subProcesses = subProcessesSO;
		}
		return this.subProcesses;
	}

}
