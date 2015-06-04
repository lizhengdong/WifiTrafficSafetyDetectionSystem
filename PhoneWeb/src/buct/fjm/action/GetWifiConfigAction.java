package buct.fjm.action;

import java.io.File;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import buct.fjm.util.*;

import com.opensymphony.xwork2.ActionSupport;

import buct.fjm.configMove.GetWifiInfo;
import buct.fjm.jsonConver.*;

//@SuppressWarnings("serial")
@Component("GetWifiConfig")
@Scope("prototype")
public class GetWifiConfigAction extends ActionSupport {
	public String execute(){
		String json = "";
		try {
//			ConfigRead myConfigRead = new ConfigRead();
//			Map<String, String> readConfigResult = new HashMap<String, String>();
//			String wifiInfoConfPath = "/usr/local/raywork/tempPhoneWebConfig/wifiInfo.conf";
//			File myFile=new File(wifiInfoConfPath);
//			if(!myFile.exists()){
//				myFile.createNewFile();
//			}else{
//				myFile.delete();
//				myFile.createNewFile();
//			}
			//读取之前先读取系统配置文件将其写到该配置文件中
 			Map<String, String> readSysWifiInfo = new HashMap<String, String>();
 			readSysWifiInfo = GetWifiInfo.get();
// 			ConfigWrite.write(wifiInfoConfPath, readSysWifiInfo);
			
			//读取写后的文件
//			readConfigResult = myConfigRead.read(wifiInfoConfPath);
			if (readSysWifiInfo != null) {
				ConvertHashMapToJson myConvertHashMapToJson = new ConvertHashMapToJson();
				json = "{success:true,data:"
						+ myConvertHashMapToJson
								.hashMapToJson(readSysWifiInfo) + "}";
			}else{
				System.out.println("读取配置 文件错误");
			}
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("text/html;charset=utf-8");
			// 获取流
			PrintWriter out = response.getWriter();
			// 将数据以json格式打到客户端
			out.print(json);
			System.out.println(json); 
			// 清空缓存
			out.flush();
			// 关闭流
			out.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
