package os;

public class Main {
    public static void main(String[] args) {
    	
    	//FCFS
    		 
        SOProcess process1 = OperatingSystem.systemCall(130, 1, 10);
        OperatingSystem.systemCall(SystemCallType.WRITE, process1);

        SOProcess process2 = OperatingSystem.systemCall(90, 2, 5);
        OperatingSystem.systemCall(SystemCallType.WRITE, process2);

        SOProcess process3 = OperatingSystem.systemCall(34, 3, 15);
        OperatingSystem.systemCall(SystemCallType.WRITE, process3);
        
        //SOProcess process3 = OperatingSystem.systemCall(30, 3, 15);
        //OperatingSystem.systemCall(SystemCallType.WRITE, process3);
        
        System.out.println("----------------------------------------------------------------------------------");
        System.out.println("		SCHEDULER TYPE: FCFS (FIRST COME, FIRST SERVED)");
        System.out.println("----------------------------------------------------------------------------------");
        
    	
    	//PRIORITY
    	/*
        SOProcess process1 = OperatingSystem.systemCall(130, 1, 10);
        OperatingSystem.systemCall(SystemCallType.WRITE, process1);

        SOProcess process2 = OperatingSystem.systemCall(90, 2, 5);
        OperatingSystem.systemCall(SystemCallType.WRITE, process2);

        SOProcess process3 = OperatingSystem.systemCall(34, 3, 15);
        OperatingSystem.systemCall(SystemCallType.WRITE, process3);
        
        //SOProcess process3 = OperatingSystem.systemCall(30, 3, 15);
        //OperatingSystem.systemCall(SystemCallType.WRITE, process3);
        
        System.out.println("----------------------------------------------------------------------------------");
        System.out.println("				SCHEDULER TYPE: PRIORITY");
        System.out.println("----------------------------------------------------------------------------------");
        */
    	
    	//SJF
    	/*    	 
        SOProcess process1 = OperatingSystem.systemCall(130, 1, 10);
        OperatingSystem.systemCall(SystemCallType.WRITE, process1);

        SOProcess process2 = OperatingSystem.systemCall(90, 2, 5);
        OperatingSystem.systemCall(SystemCallType.WRITE, process2);

        SOProcess process3 = OperatingSystem.systemCall(34, 3, 15);
        OperatingSystem.systemCall(SystemCallType.WRITE, process3);
        
        //SOProcess process3 = OperatingSystem.systemCall(30, 3, 15);
        //OperatingSystem.systemCall(SystemCallType.WRITE, process3);
        
        System.out.println("----------------------------------------------------------------------------------");
        System.out.println("				SCHEDULER TYPE: SJF (SHORTEST JOB FIRST)");
        System.out.println("----------------------------------------------------------------------------------");
        */
    	
    	//LOTTERY
    	/*    	 
        SOProcess process1 = OperatingSystem.systemCall(130, 1, 10);
        OperatingSystem.systemCall(SystemCallType.WRITE, process1);

        SOProcess process2 = OperatingSystem.systemCall(90, 2, 5);
        OperatingSystem.systemCall(SystemCallType.WRITE, process2);

        SOProcess process3 = OperatingSystem.systemCall(34, 3, 15);
        OperatingSystem.systemCall(SystemCallType.WRITE, process3);
        
        //SOProcess process3 = OperatingSystem.systemCall(30, 3, 15);
        //OperatingSystem.systemCall(SystemCallType.WRITE, process3);
        
        System.out.println("----------------------------------------------------------------------------------");
        System.out.println("				SCHEDULER TYPE: LOTTERY");
        System.out.println("----------------------------------------------------------------------------------");
        */
    	
    	//ROUND ROBIN
    	/*    	 
        SOProcess process1 = OperatingSystem.systemCall(75, 1, 10);
        OperatingSystem.systemCall(SystemCallType.WRITE, process1);

        SOProcess process2 = OperatingSystem.systemCall(90, 2, 5);
        OperatingSystem.systemCall(SystemCallType.WRITE, process2);

        SOProcess process3 = OperatingSystem.systemCall(34, 3, 15);
        OperatingSystem.systemCall(SystemCallType.WRITE, process3);
        
        //SOProcess process4 = OperatingSystem.systemCall(58, 3, 15);
        //OperatingSystem.systemCall(SystemCallType.WRITE, process4);
        
        System.out.println("----------------------------------------------------------------------------------");
        System.out.println("				SCHEDULER TYPE: ROUND ROBIN");
        System.out.println("----------------------------------------------------------------------------------");
        */
        
    }
}
