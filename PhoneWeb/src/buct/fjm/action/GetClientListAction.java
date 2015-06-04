package buct.fjm.action;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import buct.fjm.file.FileScanner;
import buct.fjm.jsonConver.ConverAccessListToJson;
import buct.fjm.model.Clientlist;
import buct.fjm.service.ClientService;

import com.opensymphony.xwork2.ActionSupport;


@SuppressWarnings("serial")
@Component("getClientList")
@Scope("prototype")
public class GetClientListAction extends ActionSupport{
	private ClientService clientService;

	public ClientService getClientService() {
		return clientService;
	}

	@Resource
	public void setClientService(ClientService clientService) {
		this.clientService = clientService;
	}
	
	public String execute() throws Exception{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		FileScanner fs = new FileScanner();
		String json = "";
		String device = "wlp0s29f7u3";
		String path = "/usr/local/raywork/arp.txt";
		
		//生成当前连接的MAC地址列表
		fs.generateMacFile();
		//解析当前的macList
		ArrayList<String> macList = fs.AnalyseMac(path, device);
		
		//遍历数据库，如果数据库中mac不存在于macList，则删掉
		List<Clientlist> cList = clientService.findAll();
		for(Clientlist cl:cList){
			if(!macList.contains(cl.getCmac())){
				clientService.delete(cl);
			}
		}
		//获取删除多余条目的mac列表
		String hql = "select distinct cmac from Clientlist";
		List ncMacList = clientService.queryMac(hql);
		//遍历macList，如果数据库中不存在该mac则加入数据库
		for(int j = 0; j < macList.size(); j++){
			if(!ncMacList.contains(macList.get(j))){
				Clientlist cl = new Clientlist();
				cl.setCmac(macList.get(j));
				cl.setShowpkgs(true);
				clientService.add(cl);
			}
		}
		List<Clientlist> fcList = clientService.findAll();
		json = ConverAccessListToJson.ConverListToPageJson(fcList, 1000);
		
		//获取流
		PrintWriter out = response.getWriter();
		//将数据以json形式打到客户端
		out.print(json);
		System.out.println(json);
		//清空缓存
		out.flush();
		//关闭流
		out.close();
		
		return null;
	}

}