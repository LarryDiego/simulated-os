package os.memory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import os.SOProcess;
import os.SubProcess;

public class MemoryManager {
    public List<SubProcess[]> physicMemory;
    private Map<String, MemoryAddress> logicMemory;

    public static final int PAGE_SIZE = 4;
    public static final int MEMORY_SIZE = 256;

    public MemoryManager() {
        int quantityPages = MEMORY_SIZE / PAGE_SIZE;

        physicMemory = new ArrayList<>(quantityPages);
        for (int frame = 0; frame < quantityPages; frame++) {
            physicMemory.add(new SubProcess[PAGE_SIZE]);
        }

        logicMemory = new HashMap<>();
    }

    public List<SubProcess> read(SOProcess process) {
        List<SubProcess> subProcesses = new ArrayList<>();

        for (String subProcessIdSelected : process.getSubProcess()) {
        	MemoryAddress addressSubProcess = logicMemory.get(subProcessIdSelected);

            if (addressSubProcess != null && physicMemory.get(addressSubProcess.getFrame())[addressSubProcess.getIndexPage()] != null) {
                subProcesses.add(physicMemory.get(addressSubProcess.getFrame())[addressSubProcess.getIndexPage()]);
            }
        }

        return subProcesses;
    }

    public void write(SOProcess process) {
        allocateProcessWithPaging(process);
    }

    public boolean checkWrite(SOProcess process) {
        List<Integer> emptyFrames = findEmptyPages();
        return emptyFrames.size() >= (double)process.getSize() / PAGE_SIZE;
    }

    private List<Integer> findEmptyPages() {
        List<Integer> framesIndex = new ArrayList<>();

        for (int frame = 0; frame < physicMemory.size(); frame++) {
            SubProcess[] element = physicMemory.get(frame);

            if (element[0] == null) {
                framesIndex.add(frame);
            }
        }

        return framesIndex;
    }

    private void allocateProcessWithPaging(SOProcess process) {
        List<Integer> emptyFrames = findEmptyPages();

        int countSize = 0;
        for (Integer indexFrame : emptyFrames) {
            SubProcess[] page = physicMemory.get(indexFrame);

            int indexPage = 0;
            while (indexPage < page.length && countSize < process.getSize()) {
                String subProcessId = process.getSubProcess().get(countSize);

                physicMemory.get(indexFrame)[indexPage] = new SubProcess(subProcessId, process);

                logicMemory.put(subProcessId, new MemoryAddress(indexFrame, indexPage));

                countSize++;
                indexPage++;
            }
        }

        printMemory();
    }

    public SOProcess delete(SOProcess process) {
        List<String> subProcess = process.getSubProcess();

        for (SubProcess[] page : physicMemory) {
            if (page[0] != null && page[0].getProcess().getId().equals(process.getId())) {
                for (int i = 0; i < page.length; i++) {
                    page[i] = null;
                }
            }
        }

        for (String sb : subProcess) {
            logicMemory.remove(sb);
        }

        printMemory();

        return process;
    }

    private void printMemory() {
        System.out.println("----------------------------------------------------------------------");
        System.out.println("teste de memoria %");
        for (SubProcess[] page : physicMemory) {
            for (SubProcess subProcess : page) {
                System.out.print("{ id: " + (subProcess != null ? subProcess.getId() : null) + " } ");
            }
            System.out.println();
        }
    }
}
