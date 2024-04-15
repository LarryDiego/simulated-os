package os;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class SubProcess {
	private int instructions;
	private String id;
	private String processId;
	private int timeToExecute;
	private static int count;
	
	public SubProcess(String processId, int instructions) {
		count++;
		this.id = processId + "Sp" + count;
		this.processId = processId;
		this.instructions = instructions;
		Random random = new Random();
		List<Integer> givenList = Arrays.asList(100, 200, 300, 400, 500, 600, 700, 800, 900, 1000, 2000);
		this.timeToExecute = givenList.get(random.nextInt(givenList.size()));
	}

	public int getInstructions() {
		return instructions;
	}

	public void setInstructions(int instructions) {
		this.instructions = instructions;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProcessId() {
		return processId;
	}

	public void setProcessId(String processId) {
		this.processId = processId;
	}

	public int getTimeToExecute() {
		return timeToExecute;
	}

	public void setTimeToExecute(int timeToExecute) {
		this.timeToExecute = timeToExecute;
	}
	
	
	
}
