package os.scheduler.strategy;

import java.util.List;

import os.OperatingSystem;
import os.SOProcess;
import os.SubProcess;
import os.SystemCallType;
import os.scheduler.Scheduler;

public class Lottery extends Scheduler{

	@Override
	public void execute(SOProcess process) {
		List<SubProcess> subProcesses = OperatingSystem.systemCall(SystemCallType.READ_PROCESS, process);
		
	}

	@Override
	public void finish(SOProcess process) {
		// TODO Auto-generated method stub
		
	}

}
