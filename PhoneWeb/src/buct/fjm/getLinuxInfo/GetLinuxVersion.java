package buct.fjm.getLinuxInfo;

import java.util.Properties;

public class GetLinuxVersion {
	public static String getVersion(){
		String winOSName = "";//系统名称
		String winOSVersion = "";//系统版本
		String winOSArch = "";//系统架构
		String winVersionInfo = "";
		Properties props = System.getProperties();//获取系统属性集
		winOSName = props.getProperty("os.name");
		winOSVersion = props.getProperty("os.version");
		winOSArch = props.getProperty("os.arch");
		winVersionInfo = winOSName + " " + winOSVersion + " " + winOSArch;
		return winVersionInfo;
	}
}
