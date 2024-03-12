package os;

import os.cpu.CpuManager;
import os.memory.MemoryManager;
import os.memory.Strategy;
import os.scheduler.Scheduler;

public class OperatingSystem {
	private static MemoryManager mm;
	private static CpuManager cm;
	//Segunda Etapa
	//private static Scheduler ps;
	
	public static Process systemCall(SystemCallType type, Process p) {
		if(type.equals(SystemCallType.CREATE_PROCESS)) {
			if(mm == null) {
				mm = new MemoryManager(Strategy.BEST_FIT);
			}
			if(cm == null) {
				cm = new CpuManager();
			}
			return new Process();
			
		} else if(type.equals(SystemCallType.WRITE_PROCESS)) {
			mm.write(p);
			
		} else if(type.equals(SystemCallType.CLOSE_PROCESS)) {
			mm.delete(p);
			
		}
		return null;
	}
}
