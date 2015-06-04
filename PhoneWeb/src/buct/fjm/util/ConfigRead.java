package buct.fjm.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

public class ConfigRead {

	public static Map<String, String> read(String filePath) throws Exception {
		Map<String, String> resultMap = new HashMap<String, String>();
		BufferedReader myBufferedReader = new BufferedReader(new FileReader(
				filePath));
		String tempStr = "";
		while ((tempStr = myBufferedReader.readLine()) != null) {
			if (tempStr.contains("=")) {
				String[] tempStrAr = tempStr.split("=");
				if (tempStrAr.length == 2) {
					resultMap.put(tempStrAr[0], tempStrAr[1]);
				} else if (tempStrAr.length == 1) {
					resultMap.put(tempStrAr[0], "");
				}
			}
		}
		myBufferedReader.close();
		return resultMap;
	}
}
