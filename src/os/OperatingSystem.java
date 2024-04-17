package os;

import java.util.List;

import os.memory.MemoryManager;
import os.scheduler.FCFS;
import os.scheduler.Lottery;
import os.scheduler.Priority;
import os.scheduler.RoundRobin;
import os.scheduler.SJF;
import os.scheduler.Scheduler;

public class OperatingSystem {
    public static MemoryManager memoryManager = new MemoryManager();
    public static Scheduler scheduler = new FCFS();
    
    public static SOProcess systemCall(int size, int priority, int timeToExecute) {
    	return new SOProcess(size, priority, timeToExecute);
    }

    public static List<SubProcess> systemCall(SystemCallType typeCall, SOProcess process) {
        if (typeCall == SystemCallType.WRITE && process != null) {
            boolean checkWrite = memoryManager.checkWrite(process);

            if (checkWrite) {
                memoryManager.write(process);
                scheduler.addProcess(process);
            } else {
                System.out.println("Page fault");
            }
        }

        if (typeCall == SystemCallType.READ && process != null) {
            return memoryManager.read(process);
        }

        if (typeCall == SystemCallType.DELETE && process != null) {
            scheduler.close(process);
            memoryManager.delete(process);
        }

        if (typeCall == SystemCallType.STOP && process != null) {
            scheduler.close(process);
        }

        return null;
    }
}
