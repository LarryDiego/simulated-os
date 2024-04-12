 package os;

public class Execute {
	public static void main(String[] args) {
		while (!OperatingSystem.scheduler.isEmpty()) {
		
		SOProcess process1 = OperatingSystem.systemCall(SystemCallType.CREATE_PROCESS, 20);
		OperatingSystem.systemCall(SystemCallType.WRITE_PROCESS, process1);
		
		SOProcess process2 = OperatingSystem.systemCall(SystemCallType.CREATE_PROCESS, 38);
		OperatingSystem.systemCall(SystemCallType.WRITE_PROCESS, process2);
		
		SOProcess process3 = OperatingSystem.systemCall(SystemCallType.CREATE_PROCESS, 38);
		OperatingSystem.systemCall(SystemCallType.WRITE_PROCESS, process3);
		
		SOProcess process4 = OperatingSystem.systemCall(SystemCallType.CREATE_PROCESS, 20);
		OperatingSystem.systemCall(SystemCallType.WRITE_PROCESS, process4);
		
		OperatingSystem.systemCall(SystemCallType.CLOSE_PROCESS, process1);		
		OperatingSystem.systemCall(SystemCallType.CLOSE_PROCESS, process3);
		
		SOProcess process5 = OperatingSystem.systemCall(SystemCallType.CREATE_PROCESS, 8);
		OperatingSystem.systemCall(SystemCallType.WRITE_PROCESS, process5);
		}
	}

}
