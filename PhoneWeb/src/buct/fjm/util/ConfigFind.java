package buct.fjm.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConfigFind {

	public static Map<String,String> find(String filePath,List<String> keys)throws Exception {
		Map<String,String> results=new HashMap<String,String>();
		
		BufferedReader br=new BufferedReader(new FileReader(filePath));
		String temp=null;
		while((temp=br.readLine())!=null){
		  String t[]=temp.split("=");
		  if(keys.contains(t[0])){
			 results.put(t[0], t[1]);
		  }
		}
		br.close();
		return results;
	}
	
	public static String findPassword(String filepath, String username) throws Exception{
		String result = "";
		BufferedReader br = new BufferedReader(new FileReader(filepath));
		String line = null;
		while((line = br.readLine()) != null){
			String item[] = line.split("\t");
			if(item[0].equals("\""+username+"\"")){
				result = item[2];
			}
		}
		if(result != ""){
			result =result.substring(1, result.length()-1);
		}
		else {
			result = "unknown";
		}
		return result;
	}
}
