package buct.fjm.util;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import com.opensymphony.xwork2.util.FileManager;

public class ConfigWrite {
	public static boolean write(String filePath,Map<String,String> configContext){
		String mapKey,mapValue;
		StringBuffer myStringBuffer = new StringBuffer();
		Iterator<String> myIterator = configContext.keySet().iterator();
		while(myIterator.hasNext()){
			mapKey = myIterator.next();
			mapValue = configContext.get(mapKey);
			myStringBuffer.append(mapKey + "=" + mapValue + "\n");
		}
		FileManage myFileManage = new FileManage(filePath);
		try{
			if(myFileManage.writeFile(myStringBuffer.toString())){
				return true;
			}else{
				return false;
			}
		}catch(IOException e){
			e.printStackTrace();
			return false;
		}
	}
}
