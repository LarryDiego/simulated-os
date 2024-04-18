package os.cpu;

import os.SubProcess;
import os.scheduler.Scheduler;

public class CpuManager {
    private Core[] cores;
    public static int CLOCK = 500;
    public static int NUMBER_OF_INSTRUCTIONS_BY_CLOCK = 7;
    public static int NUMBER_OF_CORES = 4;
    private Scheduler scheduler;
    
    

    public CpuManager(Scheduler scheduler) {
        this.cores = new Core[CpuManager.NUMBER_OF_CORES];

        for (int index = 0; index < this.cores.length; index++) {
            this.cores[index] = new Core(index, CpuManager.NUMBER_OF_INSTRUCTIONS_BY_CLOCK);
        }
        this.scheduler = scheduler;
        this.runClock();
    }

    private void runClock() {
        new Thread(() -> {
            while (true) {
                executeCores();
                try {
                    Thread.sleep(CpuManager.CLOCK);
                } catch (InterruptedException e) {
                	e.printStackTrace();
                }
            }
        }).start();
    }

    private void executeCores() {
    	int count = 0;
        SubProcess data = null;
        for (Core core : cores) {
            data = this.scheduler.execute();
            if (data != null) {
                if (core.getSubProcess() == null) {
                    core.setSubProcess(data);
                    core.run();
                    count++;
//                    if (this.scheduler != null && count == 4) {
//                    	if (this.scheduler.getQuantumTable() != null) {
//                    		
//                    		if (this.scheduler.getQuantumTable().get(data.getProcess().getId()) != 0) {    			
//                    			System.out.println(this.scheduler.getQuantumTable().get(data.getProcess().getId()));
//                    		}
//                    	}
//                	}
                }
            }
        }

        if (data != null) {
        	int quantumValue = 1;
        	if (this.scheduler.getQuantumTable() != null) {        		
        		quantumValue = this.scheduler.getQuantumTable().get(data.getProcess().getId());
        	}
        	
        	System.out.println("----------------------------------------------------------------------------------");
        	System.out.println("				CLOCK: " + CLOCK + " MS");
        	if (quantumValue == 0) {
        		System.out.println("				QUANTUM: 3000 (LIMITE REACHED)");
        		System.out.println("		CHANGING PROCESS - CATCHING THE NEXT IN QUEUE");
        	} else if (quantumValue == 1) {
        		System.out.print("");
        	} else {
        		System.out.println("				QUANTUM: " + quantumValue);
        	}
        	System.out.println("----------------------------------------------------------------------------------");
        	}
        }
    }
