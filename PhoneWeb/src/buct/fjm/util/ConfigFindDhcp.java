package buct.fjm.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConfigFindDhcp {
	public static Map<String, String> findDhcp(String filepath, List<String> keys) throws Exception{
		Map<String, String> results = new HashMap<String, String>();
		
		BufferedReader br = new BufferedReader(new FileReader(filepath));
		String temp = null;
		while((temp = br.readLine()) != null){
			String t[] = temp.split(" ");
			for(int i = 0; i < t.length; i++){
				if(keys.contains(t[i])){
					int buf = i;
					buf++;
					if(t[buf].contains(";")){
						int j = t[buf].indexOf(";");
						t[buf] = t[buf].substring(0, j);
					}
					results.put(t[i], t[buf]);
				}
				if(t[i].equals("	range")){
					//System.out.println("Entered");
					int buf = i;
					buf++;
					results.put("startIp", t[buf]);
					buf++;
					if(t[buf].contains(";")){
						int j = t[buf].indexOf(";");
						t[buf] = t[buf].substring(0, j);
					}
					results.put("endIp", t[buf]);
				}
			}
		}
		br.close();
		return results;
	}

}