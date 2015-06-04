package buct.fjm.configMove;

import java.util.HashMap;
import java.util.Map;

import buct.fjm.getLinuxInfo.GetLinuxIP;
import buct.fjm.getLinuxInfo.GetLinuxVersion;
import buct.fjm.util.ConfigRead;
import buct.fjm.util.dhcpdConfRead;
//获得GetWifiInfo.conf中所需的数据并写入到该文件中
public class GetWifiInfo {
	//定义要获得的变量
	private static String softInfo = "PhoneWeb Version 1.0";//软件版本固定值
	private static String systemVersionInfo = "";
	//lan口
	private static String macLanInfo = "";
	private static String ipLanInfo = "";
	private static String zwymLanInfo = "";
	//wan
	private static String macWanInfo = "";
	private static String IPWanInfo = "";
	private static String zwymWanInfo = "";
	private static String wgWanInfo = "";
	private static String dnsWanInfo = "";
	//wireless
	private static String wirelessInfo = "";
	private static String ssidInfo = "";
	private static String xdInfo = "";
	private static String msInfo = "";
	private static String pddkInfo = "";
	private static String wdsInfo = "";
	public static Map<String,String> get() {
		//获取系统版本
		systemVersionInfo = GetLinuxVersion.getVersion();
		//获取Lan口状态
		
		//获取Wan口状态
		
		//wan口Mac地址，读取/usr/local/raywork/conf/AnalyseConfig.conf
		//中的NativeMac
		String analyseConfigPath="/usr/local/raywork/conf/AnalyseConfig.conf";
		Map<String,String> wanMacAddMap = new HashMap<String,String>();
		try {
			wanMacAddMap = ConfigRead.read(analyseConfigPath);
			if(wanMacAddMap.containsKey("NativeMac")){
				macWanInfo = wanMacAddMap.get("NativeMac");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//获取wan口IP地址、子网掩码、网关、DNS服务器。	
		String dhcpConfigPath = "/etc/dhcp/dhcpd.conf";
		Map<String,String> wanIpAnd = new HashMap<String,String>();
		try {
			wanIpAnd = dhcpdConfRead.read(dhcpConfigPath);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(wanIpAnd.containsKey("routers")){
			//网关
			wgWanInfo = wanIpAnd.get("routers");
		}
		if(wanIpAnd.containsKey("subnet-mask")){
			//子网掩码
			zwymWanInfo = wanIpAnd.get("subnet-mask");
		}
		if(wanIpAnd.containsKey("domain-name-servers")){
			//dns
			dnsWanInfo = wanIpAnd.get("domain-name-servers");
		}
		//IP地址
		IPWanInfo = GetLinuxIP.getAddress();
		
		//无线状态
		String hostapdConfigPath = "/etc/hostapd/hostapd.conf";
		Map<String,String> hostapdMap = new HashMap<String,String>();
		try {
			hostapdMap = ConfigRead.read(hostapdConfigPath);
			if(hostapdMap.containsKey("ssid")){
				if(hostapdMap.get("ssid") != null){
					//此状态为启用无线功能
					wirelessInfo="On";
					ssidInfo=hostapdMap.get("ssid");//ssid名称
					if(hostapdMap.containsKey("channel")){
						//如果有信道号
						xdInfo = hostapdMap.get("channel");//信道号
					}
					
				}else{
					wirelessInfo="未启用";
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//现在将所获得变量写入到hashMap中，并将hashMap的值保存到文件。
		Map<String,String> theResultMap = new HashMap<String,String>();
		theResultMap.put("softInfo",softInfo);
		theResultMap.put("systemVersionInfo", systemVersionInfo);
		theResultMap.put("macLanInfo",macLanInfo );
		theResultMap.put("ipLanInfo", ipLanInfo);
		theResultMap.put("zwymLanInfo", zwymLanInfo);
		theResultMap.put("macWanInfo", macWanInfo);
		theResultMap.put("IPWanInfo",IPWanInfo );
		theResultMap.put("zwymWanInfo", zwymWanInfo);
		theResultMap.put("wgWanInfo",wgWanInfo );
		theResultMap.put("dnsWanInfo",dnsWanInfo);
		theResultMap.put("wirelessInfo",wirelessInfo );
		theResultMap.put("ssidInfo",ssidInfo );
		theResultMap.put("xdInfo",xdInfo );
		theResultMap.put("msInfo",msInfo );
		theResultMap.put("pddkInfo",pddkInfo);
		theResultMap.put("wdsInfo",wdsInfo );
		
		return theResultMap;
	}	
}
