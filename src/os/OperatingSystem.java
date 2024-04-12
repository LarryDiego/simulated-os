package os;

import java.util.List;

import os.memory.MemoryManager;
import os.scheduler.Scheduler;
import os.scheduler.strategy.FCFS;

public class OperatingSystem {
	public static MemoryManager memoryManager;
	public static Scheduler scheduler;
	
	public static SOProcess systemCall(SystemCallType type, int processSize) {
		if(type.equals(SystemCallType.CREATE_PROCESS)) {
			if(memoryManager == null) {
				memoryManager = new MemoryManager();
			}
			if(scheduler == null) {
				scheduler = new FCFS();
			}
		}
		return new SOProcess(processSize);
	}
	
	public static List<SubProcess> systemCall(SystemCallType type, SOProcess process) {		
		 if(type.equals(SystemCallType.WRITE_PROCESS)) {
			memoryManager.write(process);
			scheduler.execute(process);
			
		} else if(type.equals(SystemCallType.CLOSE_PROCESS)) {
			scheduler.finish(process);
			memoryManager.delete(process);
			
		} else if(type.equals(SystemCallType.READ_PROCESS)) {
			memoryManager.read(process);
		}
		 return null;
	}
}
