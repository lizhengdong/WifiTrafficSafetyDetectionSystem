package buct.fjm.action;

import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import buct.fjm.service.AllTrafficPropotionService;
import buct.fjm.service.UpDownstreamTrafficService;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
@Component("AllTrafficPropotion")
@Scope("prototype")
public class AllTrafficPropotionAction extends ActionSupport {
	String nativeMac;
	private AllTrafficPropotionService allTrafficPropotionService;

	public String getNativeMac() {
		return nativeMac;
	}

	public void setNativeMac(String nativeMac) {
		this.nativeMac = nativeMac;
	}

	@Resource
	public void setAllTrafficPropotionService(
			AllTrafficPropotionService allTrafficPropotionService) {
		this.allTrafficPropotionService = allTrafficPropotionService;
	}

	public AllTrafficPropotionService getAllTrafficPropotionService() {
		return allTrafficPropotionService;
	}

	public String execute() throws Exception {
		String json = "";
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		try {
			List list = allTrafficPropotionService
					.TrafficPropotionByMacAddress();
			int listSize=list.size();
			List<String> macAddList=new ArrayList<String>(new Integer(listSize));
			List<String> floatAmountList=new ArrayList<String>(new Integer(listSize));
			Object[] objs = null;
			for (int i = 0; i < list.size(); i++) {
				objs = (Object[]) list.get(i);
				
//				System.out.println("objsSize"+objs.length);
//				System.out.println("objsStr"+objs.toString());
				
				
				macAddList.add(objs[0].toString());
				floatAmountList.add(objs[1].toString());
//				System.out.println("objs[0]"+objs[0].toString());
//				System.out.println("objs[1]"+objs[1].toString());
				
			}

			String tempJson = "";
			for (int j = 0; j < macAddList.size(); j++) {
				tempJson += "{'name': '" + macAddList.get(j) + "','data':"+ floatAmountList.get(j) +",'showName': ''},";
			}
			int lastComma = tempJson.lastIndexOf(",");
			tempJson = tempJson.substring(0, lastComma);
			json = "[" + tempJson + "]";
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 获取流
		PrintWriter out = response.getWriter();
		// 将数据以json格式打到客户端
		out.print(json);
		System.out.println(json);
		// 清空缓存
		out.flush();
		// 关闭流
		out.close();
		return null;
	}

}
