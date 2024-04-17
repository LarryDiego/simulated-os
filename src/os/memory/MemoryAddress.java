package os.memory;

public class MemoryAddress {
	private int frame;
	private int indexPage;
	
	public MemoryAddress(int frame, int indexPage) {
		this.frame = frame;
		this.indexPage = indexPage;
	}

	public int getFrame() {
		return frame;
	}

	public void setFrame(int frame) {
		this.frame = frame;
	}

	public int getIndexPage() {
		return indexPage;
	}

	public void setIndexPage(int indexPage) {
		this.indexPage = indexPage;
	}

	
}
