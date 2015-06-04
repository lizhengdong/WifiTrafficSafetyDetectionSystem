package buct.fjm.model;

public class Config {
	private String filePath;
	private String copyPath;
	private boolean isC;
	private String nativeMac;
	private String wfpW;
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getCopyPath() {
		return copyPath;
	}
	public void setCopyPath(String copyPath) {
		this.copyPath = copyPath;
	}
	
	public boolean isC() {
		return isC;
	}
	public void setC(boolean isC) {
		this.isC = isC;
	}
	public String getNativeMac() {
		return nativeMac;
	}
	public void setNativeMac(String nativeMac) {
		this.nativeMac = nativeMac;
	}
	public String getWfpW() {
		return wfpW;
	}
	public void setWfpW(String wfpW) {
		this.wfpW = wfpW;
	}
}
