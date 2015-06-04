package buct.fjm.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class ConfigEdit {
	public static void edit(String filePath,Map<String,String> changes)throws Exception {
		BufferedReader br=new BufferedReader(new FileReader(filePath));
		StringBuffer sb=new StringBuffer(4096);
		String temp=null;
		while((temp=br.readLine())!=null){
		  String t[]=temp.split("=");
		  if(changes.containsKey(t[0])){
			  sb.append(t[0]+"="+changes.get(t[0])).append("\n");
			  continue;
		  }
		  sb.append(temp).append( "\n");
		}
		br.close();
		BufferedWriter bw=new BufferedWriter(new FileWriter(filePath));
		bw.write(sb.toString());
		bw.close();
	}
}
