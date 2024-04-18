package os;

import java.util.ArrayList;
import java.util.List;

public class SOProcess {
    private static int COUNT_PROCESS = 0;
    private String id;
    private int size;
    private int instructions;
    private int instructionsExecuted;
    private List<String> subProcess;
    private int timeToExecute;
    private int priority;
    private long inputMemory;

    public SOProcess(int size, Integer priority, int timeToExecute) {
        SOProcess.COUNT_PROCESS++;
        this.inputMemory = System.currentTimeMillis();

        this.id = "P" + SOProcess.COUNT_PROCESS;
        this.size = size;
        this.subProcess = new ArrayList<>();
        this.insertSubProcess();

        this.instructions = this.subProcess.size() * 7;
        this.instructionsExecuted = 0;

        this.timeToExecute = timeToExecute;

        this.priority = priority;
    }

    private void insertSubProcess() {
        for (int i = 0; i < this.size; i++) {
            this.subProcess.add(this.id + " - SP " + i);
        }
    }

    public void checkSubProcessConclusions() {
        if (this.instructionsExecuted == this.instructions) {
        	System.out.println("----------------------------------------------------------------------------------");
            System.out.println("PROCESS ID: " + this.id + " HAS BEEN FULLY EXECUTED AND REMOVED FROM THE QUEUE");
            System.out.println("A NEW PROCESS WILL BE EXECUTED (IF THERE IS SOMEONE IN THE QUEUE)");
            System.out.println("----------------------------------------------------------------------------------");
        }
    }

    public void setInputMemory(long inputMemory) {
        this.inputMemory = inputMemory;
    }

    public void setInstructionsExecuted(int quantity) {
        this.instructionsExecuted += quantity;
    }

    public long getInputMemory() {
        return this.inputMemory;
    }

    public int getInstructionsExecuted() {
        return this.instructionsExecuted;
    }

    public String getId() {
        return this.id;
    }

    public int getSize() {
        return this.size;
    }

    public int getInstructions() {
        return this.instructions;
    }

    public List<String> getSubProcess() {
        return this.subProcess;
    }

    public int getTimeToExecute() {
        return this.timeToExecute;
    }

    public int getPriority() {
        return this.priority;
    }
}
