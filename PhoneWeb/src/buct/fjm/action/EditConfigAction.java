package buct.fjm.action;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import buct.fjm.util.ConfigEdit;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
@Component ("editConfig")
@Scope ("prototype")
public class EditConfigAction extends ActionSupport{

	private String filePath;
	private String copyPath;
	private String isContinue;
	private String nativeMac;
	private String wfpW;
	public String execute() throws Exception
	{
		/*System.out.println(filePath);
		System.out.println(copyPath);
		System.out.println(isContinue);
		System.out.println(nativeMac);
		System.out.println(wfpW);*/
		
		Map<String,String>changes=new HashMap<String,String>();
		changes.put("FilePath", filePath);
		changes.put("CopyPath", copyPath);
		if(isContinue.equals("Y")){
			changes.put("isContinue", "true");
		}else if(isContinue.equals("N")){
			changes.put("isContinue", "false");
		}
		
		ConfigEdit.edit("E:/Workspaces/PhonePacp/MySqlInput2/SystemConfig.conf", changes);
		
		Map<String,String>changes2=new HashMap<String,String>();
		changes2.put("NativeMac", nativeMac);
		ConfigEdit.edit("E:/Workspaces/PhonePacp/MySqlInput2/AnalyseConfig.conf", changes2);
		return null;
	}
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

	public String getIsContinue() {
		return isContinue;
	}
	public void setIsContinue(String isContinue) {
		this.isContinue = isContinue;
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
