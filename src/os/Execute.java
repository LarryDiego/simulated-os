package os;

public class Execute {
	public static void main(String[] args) {
		
		Process p1 = OperatingSystem.systemCall(SystemCallType.CREATE_PROCESS, new Process(20));
		OperatingSystem.systemCall(SystemCallType.WRITE_PROCESS, p1);
		
		Process p2 = OperatingSystem.systemCall(SystemCallType.CREATE_PROCESS, new Process(38));
		OperatingSystem.systemCall(SystemCallType.WRITE_PROCESS, p2);
		
		Process p3 = OperatingSystem.systemCall(SystemCallType.CREATE_PROCESS, new Process(38));
		OperatingSystem.systemCall(SystemCallType.WRITE_PROCESS, p3);
		
		Process p4 = OperatingSystem.systemCall(SystemCallType.CREATE_PROCESS, new Process(20));
		OperatingSystem.systemCall(SystemCallType.WRITE_PROCESS, p4);
		
		OperatingSystem.systemCall(SystemCallType.CLOSE_PROCESS, p1);
		OperatingSystem.systemCall(SystemCallType.CLOSE_PROCESS, p3);
		
		Process p5 = OperatingSystem.systemCall(SystemCallType.CREATE_PROCESS, new Process(8));
		OperatingSystem.systemCall(SystemCallType.WRITE_PROCESS, p5);
		
	}

}
