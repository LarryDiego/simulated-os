package os;

public class SubProcess {
    private String id;
    private int instructions;
    private SOProcess process;
    private boolean isConclude;

    public SubProcess(String id, SOProcess process) {
        this.id = id;
        this.instructions = 7;
        this.process = process;
        this.isConclude = false;
    }

    public void finish() {
        this.isConclude = true;
    }

    public String getId() {
        return this.id;
    }

    public int getInstructions() {
        return this.instructions;
    }

    public boolean getIsConclude() {
        return this.isConclude;
    }

    public SOProcess getProcess() {
        return this.process;
    }
}
