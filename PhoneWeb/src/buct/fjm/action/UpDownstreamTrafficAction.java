package buct.fjm.action;

import java.io.PrintWriter;
import java.text.DecimalFormat;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import buct.fjm.model.Accesscontrol;
import buct.fjm.service.UpDownstreamTrafficService;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
@Component("UpDownstreamTraffic")
@Scope("prototype")
public class UpDownstreamTrafficAction extends ActionSupport {
	String nativeMac;
	String remoteIp;
	private UpDownstreamTrafficService upDownstreamTrafficService;

	public String getNativeMac() {
		return nativeMac;
	}

	public void setNativeMac(String nativeMac) {
		this.nativeMac = nativeMac;
	}

	public String getRemoteIp() {
		return remoteIp;
	}

	public void setRemoteIp(String remoteIp) {
		this.remoteIp = remoteIp;
	}

	@Resource
	public void setUpDownstreamTrafficService(
			UpDownstreamTrafficService upDownstreamTrafficService) {
		this.upDownstreamTrafficService = upDownstreamTrafficService;
	}

	public UpDownstreamTrafficService getUpDownstreamTrafficService() {
		return upDownstreamTrafficService;
	}

	public String execute() throws Exception {

		String json = "";
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		try {
			Long upStreamTrafficCount = upDownstreamTrafficService
					.getAllUpstreamTrafficCount();
			Long downStreamTrafficCount = upDownstreamTrafficService
					.getAllDownstreamTrafficCount();
			Long allStreamTrafficCount = upStreamTrafficCount
					+ downStreamTrafficCount;

			float upPropertion = upStreamTrafficCount.floatValue()
					/ allStreamTrafficCount.floatValue();
			DecimalFormat decimalFormat = new DecimalFormat(".00");

			int upPropertion100 = (int) (Float.parseFloat(decimalFormat
					.format(upPropertion)) * 100);
			int downPropertion100 = 100 - upPropertion100;
			json = "[{ 'name': '上行','data':" + upPropertion100
					+ " }, { 'name': '下行','data':" + downPropertion100 + "}]";
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
