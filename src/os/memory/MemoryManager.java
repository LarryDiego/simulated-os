package os.memory;

import os.Process;
public class MemoryManager {
	private String[] physicalMemory;
	private Strategy strategy;
	//Paginação
	//private String[] logicalMemory;
	
	public MemoryManager(Strategy strategy) {
		this.physicalMemory = new String[128];
		//Test line
		//this.physicalMemory = new String[4];
		this.strategy = strategy;		
	}
	
	public void write(Process p) {
		if(this.strategy.equals(Strategy.FIRST_FIT)) {
			this.writeWithFirstFit(p);			
		} else if(this.strategy.equals(Strategy.BEST_FIT)) {
			this.writeWithBestFit(p);
		} else if(this.strategy.equals(Strategy.WORST_FIT)) {
			this.writeWithWorstFit(p);
		} else if(this.strategy.equals(Strategy.PAGINATION)) {
			this.writeWithPagination(p);
		}
	}

	public void delete(Process p) {
        for (int i = p.getMa().getStart(); i <= p.getMa().getEnd(); i++) {
            if (this.physicalMemory[i] != null && this.physicalMemory[i].equals(p.getId())) {
                this.physicalMemory[i] = null;
            }
        }
        System.out.println("--------------------------------------------------------------------------");
        System.out.println("                  (-) REMOVING THE MEMORY PROCESS (-)");
        System.out.println("PROCESS ID: " + p.getId() + " REMOVED!\nPAGES/SIZE IN MEMORY: " + p.getSizeInMemory() + "\nRELEASED PAGES/INDEXES: " + p.getMa().getStart() + " - " + p.getMa().getEnd());
        System.out.println("--------------------------------------------------------------------------\n");
        this.memoryInUse();
    }
	
	private void writeWithFirstFit(Process p) {
		MemoryAddress ma = firstFitMethod(p.getSizeInMemory());
		if(ma != null) {
			allocateProcess(ma, p);
		} else {
			printError(p.getId(), p.getSizeInMemory());
		}
		
	}
	
	private MemoryAddress firstFitMethod(int sizeInMemory) {
		int actualSize = 0;
		int start = 0;
		int end = 0;
		
		for(int i = 0; i < this.physicalMemory.length; i++) {
			String element = this.physicalMemory[i];
			
			if(element == null) {
				actualSize++;
				if(actualSize >= sizeInMemory) {
					end = start + actualSize - 1;
					return new MemoryAddress(start, end);
				}
			} else {
				start = i + 1;
				actualSize = 0;
			}
		}
		return null;		
	}
	
	private void writeWithBestFit(Process p) {
		MemoryAddress ma = bestFitMethod(p.getSizeInMemory());

        if (ma != null) {
            allocateProcess(ma, p);
        } else {
            printError(p.getId(), p.getSizeInMemory());
        }			
	}
	
	private MemoryAddress bestFitMethod(int size) {
        int start = -1;
        int small = this.physicalMemory.length + 1;

        MemoryAddress memory = null;
        
        int actualSize = 0;

        for (int i = 0; i < this.physicalMemory.length; i++) {
            int j = i;

            while (j < this.physicalMemory.length && this.physicalMemory[j] == null) {
                j++;
                actualSize++;
            }

            int length = j - i;
            if (length >= size && length < small && actualSize < small) {
                start = i;
                small = length;
                i = j - 1;
                actualSize = 0;
            }
        }

        if (start != -1) {
            memory = new MemoryAddress(start, start + size - 1);
        }

        return size != -1 ? memory : null;
    }
	
	private void writeWithWorstFit(Process p) {
		MemoryAddress ma = worstFitMethod(p.getSizeInMemory());

        if (ma != null) {
            allocateProcess(ma, p);
        } else {
            printError(p.getId(), p.getSizeInMemory());
        }
		
	}
	
	private MemoryAddress worstFitMethod(int sizeInMemory) {
        MemoryAddress memory = new MemoryAddress(0, 0);
        int actualSize = 0;
        int bigMemory = 0;

        for (int i = 0; i < this.physicalMemory.length; i++) {
            String element = this.physicalMemory[i];

            if (element == null) {
            	actualSize++;

                if (actualSize >= sizeInMemory && actualSize > bigMemory) {
                	memory.setStart(i - actualSize + 1);
                	memory.setEnd(memory.getStart() + sizeInMemory - 1);
                	
                    bigMemory = actualSize;
                }
            } else {
            	actualSize = 0;
            }
        }

        return bigMemory > 0 ? memory : null;
    }
	
	/*private MemoryAddress worstFitMethod2(int size) {
        int small = -1;
        int big = -1;

        MemoryAddress memory = null;

        for (int i = 0; i < this.physicalMemory.length; i++) {
            int j = i;

            while (j < this.physicalMemory.length && this.physicalMemory[j] == null) {
                j++;
            }

            int length = j - i;
            if (length >= size && length > big) {
                small = i;
                big = length;
                i = j - 1;
            }
        }

        if (big != -1) {
            memory = new MemoryAddress(small, small + size - 1);
        }

        return memory;
    } */
	
	private void writeWithPagination(Process p) {
		// TODO Auto-generated method stub		
	}
	
	private void allocateProcess(MemoryAddress ma, Process p) {
        p.setMa(ma);
        initialProcess(p);
    }
	
	private void initialProcess(Process p) {
        printInitial(p.getSizeInMemory(), p.getId());
        int startMemory = p.getMa().getStart();
        int endMemory = p.getMa().getEnd();

        for (int i = startMemory; i <= endMemory; i++) {
            this.physicalMemory[i] = p.getId();
            printCreate(i, p.getId());
        }
        printFinish(p.getId());
        this.memoryInUse();
        System.out.println("--------------------------------------------------------------------------\n");
    }
	
	//Print das etapas
	private void printInitial(int sizeInMemory, String id) {
        System.out.println("--------------------------------------------------------------------------");
        System.out.println("                  (+) WRITING THE PROCESS IN MEMORY (+)");
        System.out.println("PROCESS ID: " + id + "\nPAGES/SIZE IN MEMORY: " + sizeInMemory + "\n");
    }
	
	private void printCreate(int index, String id) {
        System.out.println("PAGE/INDEX: " + index + " -> PROCESS ID: " + id);
    }
	
	private void printFinish(String id) {
        System.out.println("\nPROCESS ID: " + id + " WRITED WITH SUCCESS!\n");
    }
	
	private void printError(String id, int size) {
        System.out.println("                  (*) ERROR: INSUFFICIENT MEMORY (*)" + "\nPROCESS ID: " + id + ", \nPAGES/SIZE IN MEMORY: " + size);
    }
	
	public void memoryInUse() {
		int memoryInUseCount = 0;
		
		for (int i = 0; i < this.physicalMemory.length; i++) {
			if (this.physicalMemory[i] != null) {
				memoryInUseCount++;
			}
		}
		
		int memoryPercentage = (memoryInUseCount*100)/this.physicalMemory.length;
		System.out.println("--------------------------------------------------------------------------");
        System.out.println("                  (%) MEMORY IN USE (%)");
        System.out.println("MEMORY IN USE: " + memoryPercentage + "% ");
      
	}
	
	
}
