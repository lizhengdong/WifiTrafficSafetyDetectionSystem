package buct.fjm.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionSupport;
@SuppressWarnings("serial")
@Component ("showNode")
@Scope ("prototype")
public class ShowNodeAction extends ActionSupport{
	
	public String execute() throws Exception
	{
		HttpServletResponse response=ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		
		//String json = "[{id:'0',text:'基本功能','expanded':true,'children':[{id:'1',text:'当日上网记录',leaf:true,iconCls:'houfei-treeRootIcon',linkUrl:'/PhoneWeb/jsp/DayFlow.jsp'},{id:'2',text:'手机安全信息',leaf:true,iconCls:'houfei-treeRootIcon',linkUrl:'/PhoneWeb/jsp/PhoneSafety.jsp'},{id:'3',text:'实时上网记录',leaf:true,iconCls:'houfei-treeRootIcon',linkUrl:'/PhoneWeb/jsp/TimesFlow.jsp'}]},{id:'50',text:'系统设置','expanded':false,'children':[{id:'4',text:'系统配置',leaf:true,iconCls:'houfei-treeRootIcon',linkUrl:'/PhoneWeb/jsp/SystemConfig.jsp'},{id:'5',text:'黑名单设置',leaf:true,iconCls:'houfei-treeRootIcon',linkUrl:'/PhoneWeb/jsp/AccessControl.jsp'},{id:'6',text:'运行状态',leaf:true,iconCls:'houfei-treeRootIcon',linkUrl:'/PhoneWeb/jsp/RunState.jsp'},{id:'7',text:'网络参数',leaf:true,iconCls:'houfei-treeRootIcon',linkUrl:'/PhoneWeb/jsp/NetworkParameter.jsp'},{id:'8',text:'客户端列表',leaf:true,iconCls:'houfei-treeRootIcon',linkUrl:'/PhoneWeb/jsp/Clientlist.jsp'},{id:'9',text:'基本设置',leaf:true,iconCls:'houfei-treeRootIcon',linkUrl:'/PhoneWeb/jsp/WifiBasicConfig.jsp'},{id:'10',text:'安全设置',leaf:true,iconCls:'houfei-treeRootIcon',linkUrl:'/PhoneWeb/jsp/WifiSafetyConfig.jsp'},{id:'11',text:'主机状态',leaf:true,iconCls:'houfei-treeRootIcon',linkUrl:'/PhoneWeb/jsp/ClientState.jsp'},{id:'12',text:'DHCP服务器',leaf:true,iconCls:'houfei-treeRootIcon',linkUrl:'/PhoneWeb/jsp/DHCPService.jsp'},{id:'13',text:'系统工具',leaf:true,iconCls:'houfei-treeRootIcon',linkUrl:'/PhoneWeb/jsp/SystemTools.jsp'}]}]";
		String json = "[{id:'0',text:'基本功能','expanded':true,'children':[{id:'1',text:'当日上网记录',leaf:true,iconCls:'houfei-treeRootIcon',linkUrl:'/PhoneWeb/jsp/DayFlow.jsp'},{id:'3',text:'实时上网记录',leaf:true,iconCls:'houfei-treeRootIcon',linkUrl:'/PhoneWeb/jsp/TimesFlow.jsp'}]},{id:'50',text:'系统设置','expanded':false,'children':[{id:'5',text:'黑名单设置',leaf:true,iconCls:'houfei-treeRootIcon',linkUrl:'/PhoneWeb/jsp/AccessControl.jsp'},{id:'6',text:'运行状态',leaf:true,iconCls:'houfei-treeRootIcon',linkUrl:'/PhoneWeb/jsp/RunState.jsp'},{id:'11',text:'PPPOE参数',leaf:true,iconCls:'houfei-treeRootIcon',linkUrl:'/PhoneWeb/jsp/PPPOEParameter.jsp'},{id:'8',text:'客户端列表',leaf:true,iconCls:'houfei-treeRootIcon',linkUrl:'/PhoneWeb/jsp/Clientlist.jsp'},{id:'9',text:'无线设置',leaf:true,iconCls:'houfei-treeRootIcon',linkUrl:'/PhoneWeb/jsp/WifiBasicConfig.jsp'},{id:'12',text:'DHCP服务器',leaf:true,iconCls:'houfei-treeRootIcon',linkUrl:'/PhoneWeb/jsp/DHCPService.jsp'},{id:'13',text:'设备控制',leaf:true,iconCls:'houfei-treeRootIcon',linkUrl:'/PhoneWeb/jsp/SystemTools.jsp'}]}]";
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
