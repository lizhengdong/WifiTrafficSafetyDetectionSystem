package buct.fjm.action;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import buct.fjm.service.RecentDaysTrafficService;
import buct.fjm.serviceImpl.RecentDaysTrafficServiceImpl;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
@Component("RecentDaysTraffic")
@Scope("prototype")
public class RecentDaysTrafficAction extends ActionSupport {
	private RecentDaysTrafficService recentDaysTrafficService;
	
	public RecentDaysTrafficService getRecentDaysTrafficService() {
		return recentDaysTrafficService;
	}
	@Resource
	public void setRecentDaysTrafficService(
			RecentDaysTrafficService recentDaysTrafficService) {
		this.recentDaysTrafficService = recentDaysTrafficService;
	}
	//RecentDaysTrafficServiceImpl recentDaysTrafficServiceImpl=new RecentDaysTrafficServiceImpl();
	public String execute() throws Exception {
//		System.out.println("开始执行返回折线图数据");
		String json = "";
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		Long daysTraffic[]=new Long[5];
		for(int i=0;i<5;i++){
			daysTraffic[i]=0L;
		}
		String recentDays[] =new String[5];
		//get date
		int j=0;
		for(int i=4;i>=0;i--){
			recentDays[i]=getRecentDate(j);
			j--;
		}
		
		//get traffic
		for(int i=0;i<5;i++){
			try{
			daysTraffic[i]=recentDaysTrafficService.getDayTraffic(recentDays[i]);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		json =  "[{ 'name': '"+recentDays[0]+"', 'data': "+daysTraffic[0]+"}," +
				"{ 'name': '"+recentDays[1]+"', 'data': "+daysTraffic[1]+"}," +
				"{ 'name': '"+recentDays[2]+"', 'data': "+daysTraffic[2]+"}," +
				"{ 'name': '"+recentDays[3]+"', 'data': "+daysTraffic[3]+"}," +
				"{ 'name': '"+recentDays[4]+"', 'data': "+daysTraffic[4]+"}]";
		// 获取流
		PrintWriter out = response.getWriter();
		// 将数据以json格式打到客户端
		out.print(json);
//		System.out.println(json);
//		System.out.println("结束返回折线图数据");
		// 清空缓存
		out.flush();
		// 关闭流
		out.close();
		return null;
	}
	public String getRecentDate(int day){
		Calendar calendar=Calendar.getInstance();
		calendar.add(Calendar.DATE, day);
		Date date=calendar.getTime();
		SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
		return dateFormat.format(date);
	}
}
