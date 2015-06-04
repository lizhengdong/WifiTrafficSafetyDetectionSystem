package buct.fjm.action;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionSupport;

import buct.fjm.file.FileOperator;

@SuppressWarnings("serial")
@Component ("endTPkg")
@Scope ("prototype")
public class EndTPkgAction extends ActionSupport{

	public String execute() throws Exception
	{
		BufferedReader br=new BufferedReader(new FileReader( "/usr/local/raywork/conf/InputTConfig.conf"));
		StringBuffer sb=new StringBuffer(4096);
		String temp=null;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-M-dd HH:mm:ss");
		while((temp=br.readLine())!=null){
		  if(temp.startsWith("EndTime")){
			  sb.append("EndTime="+format.format(new Date())).append(FileOperator.SYSTEM_SEPARATOR);
			  continue;
		  }
		  if(temp.startsWith("IsInput")){
			  sb.append("IsInput="+false).append(FileOperator.SYSTEM_SEPARATOR);
			  continue;
		  }
		  sb.append(temp).append(FileOperator.SYSTEM_SEPARATOR);
		}
		br.close();
		BufferedWriter bw=new BufferedWriter(new FileWriter( "/usr/local/raywork/conf/InputTConfig.conf"));
		bw.write(sb.toString());
		bw.close();
		HttpServletResponse response=ServletActionContext.getResponse();
		response.setContentType("text/json;charset=UTF-8");
		response.getWriter().write("{success:"+true+"}");
		return null;
	}
}
