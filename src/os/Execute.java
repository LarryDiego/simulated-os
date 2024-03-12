package os;

public class Execute {
	public static void main(String[] args) {
		Process p1 = OperatingSystem.systemCall(SystemCallType.CREATE_PROCESS, null);
		OperatingSystem.systemCall(SystemCallType.WRITE_PROCESS, p1);
		
		
		Process p2 = OperatingSystem.systemCall(SystemCallType.CREATE_PROCESS, null);
		OperatingSystem.systemCall(SystemCallType.WRITE_PROCESS, p2);
		
		OperatingSystem.systemCall(SystemCallType.CLOSE_PROCESS, p1);
		
		Process p3 = OperatingSystem.systemCall(SystemCallType.CREATE_PROCESS, null);
		OperatingSystem.systemCall(SystemCallType.WRITE_PROCESS, p3);
	}

}
