package buct.fjm.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class dhcpdConfRead {
	public static Map<String, String> read(String filePath) throws Exception{
		Map<String, String> resultMap = new HashMap<String, String>();
		BufferedReader myBufferedReader = new BufferedReader(new FileReader(
				filePath));
		String tempStr = "";
		while ((tempStr = myBufferedReader.readLine()) != null) {
			if (tempStr.contains(" ")) {
				String[] tempStrAr = tempStr.split(" ");
				//先将分割后的字符串数据中的空格去除
				for(int i = 0;i < tempStrAr.length;i++){
					tempStrAr[i] = tempStrAr[i].trim();
				}
				//进行判断
				for(int i = 0;i < tempStrAr.length;i++){
					if(tempStrAr[i].equals("routers")){
						//网关
						resultMap.put("routers", tempStrAr[i+1]);
					}else if(tempStrAr[i].equals("subnet-mask")){
						//子网掩码
						resultMap.put("subnet-mask", tempStrAr[i+1]);
					}else if(tempStrAr[i].equals("domain-name-servers")){
						//子网掩码
						resultMap.put("domain-name-servers", tempStrAr[i+1]);
					}
				}
			}
		}
		myBufferedReader.close();
		return resultMap;
	}
}
