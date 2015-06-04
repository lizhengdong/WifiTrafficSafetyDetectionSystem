package buct.fjm.hostapdreplace;

import java.util.HashMap;
import java.util.Map;

import buct.fjm.*;
import buct.fjm.util.ConfigEdit;
import buct.fjm.util.ConfigRead;

public class WifiSafetyConfigToHostapd {
	public static void replace(String sourcePath, String targetPath){
		// 将WifiSafetyConfig中的配置项保存到hostapd.conf中去
		String wifiSafetyConfigPath = sourcePath;
		String hostapdConfigPath = targetPath;
		try {
			Map<String, String> wifiSafetyMap = ConfigRead
					.read(wifiSafetyConfigPath);
			Map<String, String> changesMap = new HashMap<String, String>();
			if (wifiSafetyMap.containsKey("safetyConfig1")) {
				// 用户选择不开启无线安全
				changesMap.put("wpa_key_mgmt", "");// 加密方式
				changesMap.put("wpa_pairwise", "");// 加密算法
				changesMap.put("wpa_passphrase", "");// 密钥
			} else if (wifiSafetyMap.containsKey("safetyConfig2")) {
				// 开启无线安全
				changesMap.put("wpa_key_mgmt", wifiSafetyMap.get("rzType1"));
				changesMap.put("wpa_pairwise", wifiSafetyMap.get("jmsf1"));
				changesMap.put("wpa_passphrase", wifiSafetyMap.get("pskPwd"));
			}
			ConfigEdit.edit(hostapdConfigPath, changesMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
