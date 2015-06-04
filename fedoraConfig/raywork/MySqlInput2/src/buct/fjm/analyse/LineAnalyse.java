package buct.fjm.analyse;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import buct.fjm.config.AnalyseConfig;
import buct.fjm.config.InputConfig;
import buct.fjm.model.Pkg;
import buct.fjm.model.Pkguri;
import buct.fjm.model.Tpkg;
import buct.fjm.model.Tpkguri;
import buct.fjm.model.Uri;
/**
 * ���ļ���ÿ�����ݽ���
 * @author fjm
 *
 */
public class LineAnalyse {
	private Pkg pkg;
	private Pkguri pkguri;
	private Tpkg tpkg;
	private Tpkguri tpkguri;
	private Uri uri;
	
	
	public Pkg getPkg() {
		return pkg;
	}
	public void setPkg(Pkg pkg) {
		this.pkg = pkg;
	}
	public Pkguri getPkguri() {
		return pkguri;
	}
	public void setPkguri(Pkguri pkguri) {
		this.pkguri = pkguri;
	}
	public Tpkg getTpkg() {
		return tpkg;
	}
	public void setTpkg(Tpkg tpkg) {
		this.tpkg = tpkg;
	}
	public Tpkguri getTpkguri() {
		return tpkguri;
	}
	public void setTpkguri(Tpkguri tpkguri) {
		this.tpkguri = tpkguri;
	}
	public Uri getUri() {
		return uri;
	}
	public void setUri(Uri uri) {
		this.uri = uri;
	}
	public LineAnalyse(){
	}
	/**
	 * �����ļ���ÿ�����ݣ�������ӳ�䵽���ݿ�ʵ������
	 * @author fjm
	 * @param line
	 * @return
	 */
	public boolean Analyse(String line){
		//��ʼ�����������ļ�
		AnalyseConfig analyseConfig=new AnalyseConfig();
		//����Mac��ַ
		String nativeMac=null;
		//Զ��Ip��ַ
		String remoteIp=null;
		//��������
		boolean flowDirectioin;
		//Զ�̶˿ں�
		int remotePort;
		//��������
		int flowAmount;
		//ʱ��
		Timestamp time=null;
		//Э������
		Short protocolType;
		//uri��Ŀ¼
		String root=null;
		//uri·��
		String path=null;
		

		//���������ļ�ӳ���ʵ����
		//���������ļ��еķָ���ָ�line�ַ���
		String[] content=line.split(analyseConfig.getDecollator());
		//����Э������
		if(content.length<8){
			return false;
		}
		if(isNumeric(content[7])&&content[7].equals(analyseConfig.getTcpType())||content[7].equals(analyseConfig.getUdpType())){
			protocolType=Short.parseShort(content[7]);
			//System.out.println(protocolType);
		}else{
			//��Э�����Ͳ�ΪTCP��UDP��������������
			//System.out.println("protocolType error");
			return false;
		}
		//����ԴMac��ַ��Զ��Ip��ַ,Զ��port
		if(content[1].equals(analyseConfig.getSrcMac())){
			remoteIp=content[2].trim();
			//System.out.println(content[3].trim());
			if(isNumeric(content[3])&&!content[3].trim().equals("")){
				remotePort=Integer.parseInt(content[3].trim());
			}else{
				remotePort=0;
			}
     	    flowDirectioin=true;
     	    nativeMac=content[4].trim();
        }else if(content[4].equals(analyseConfig.getSrcMac())){
        	remoteIp=content[5].trim();
        	//System.out.println(content[6].trim());
        	if(isNumeric(content[6])&&!content[6].trim().equals(""))
        	{
        		remotePort=Integer.parseInt(content[6].trim());
        	}else{
        		remotePort=0;
        	}
     	    flowDirectioin=false;
     	    nativeMac=content[1].trim();
        }else if(content[2].startsWith("192.168.29")){
        	remoteIp=content[5].trim();
        	//System.out.println(content[6].trim());
        	if(isNumeric(content[6])&&!content[6].trim().equals(""))
        	{
        		remotePort=Integer.parseInt(content[6].trim());
        	}else{
        		remotePort=0;
        	}
     	    flowDirectioin=false;
     	    nativeMac=content[1].trim();
        }else if(content[4].startsWith("192.168.29")){
        	remoteIp=content[2].trim();
			//System.out.println(content[3].trim());
			if(isNumeric(content[3])&&!content[3].trim().equals("")){
				remotePort=Integer.parseInt(content[3].trim());
			}else{
				remotePort=0;
			}
     	    flowDirectioin=true;
     	    nativeMac=content[4].trim();
        }else{
        
        	//����Ϊ�����ļ��е�ԴMAC��ַ����������
        	//System.out.println(line);
        	//System.out.println("main error");
     	   return false;
        }
		//����ʱ��
		//��ȷʱ��
		Date cDate=new Date();
		//ʱ���ַ���
		String timeStr="";
		if(content[0].length()==31&&!content[0].equals("")&&content[0]!=null){
     	   cDate=new Date(dateFormat(content[0]));
     	   SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
     	   timeStr =format.format(cDate);
     	   try {
					time=new Timestamp(format.parse(timeStr).getTime());
				} catch (ParseException e) {
					e.printStackTrace();
				}
        }
		//��ʱ��Ϊ�գ���������
		if(time==null){
			//System.out.println("time error");
			return false;
		}
		
		//������������
		if(isNumeric(content[8])&&content[8]!=null&&!content[8].equals("")){
			flowAmount=Integer.parseInt(content[8]);
        }else{
        	//System.out.println("flowAmount error");
        	return false;
        }
		
		//����uri
		if(content.length>=10){
			root=content[9];
     	   if(content[10]!=null&&!content[10].equals("")){
     		   path=content[10];
     	   }
        }
		
		//pkgʵ����ӳ��
		pkg=new Pkg();
		pkg.setNativeMac(nativeMac);
		pkg.setRemoteIp(remoteIp);
		pkg.setRemotePort(remotePort);
		pkg.setFlowDirectioin(flowDirectioin);
		pkg.setFlowAmount(flowAmount+"");
		pkg.setProtocolType(protocolType);
		pkg.setFirstVisitTime(new Timestamp(cDate.getTime()));
		pkg.setLastVisitTime(new Timestamp(cDate.getTime()));
		pkg.setDateVisit(time);
		
		//����root��path�ж��Ƿ���uri
		if(root!=null){
			//ӳ��uriʵ����
			uri=new Uri();
			uri.setHost(root);
			uri.setPath(path);
			uri.setPort(remotePort);
		}
		
		//���������ļ��ж��Ƿ�Ҫӳ��Tpkgʵ����
		InputConfig inputConfig=new InputConfig();
		/*if(inputConfig.isInput()&&inputConfig.getStartTime()<cDate.getTime()){
			//ӳ��Tpkgʵ����
			tpkg=new Tpkg();
			tpkg.setTnativeMac(nativeMac);
			tpkg.setTremoteIp(remoteIp);
			tpkg.setTremotePort(remotePort);
			tpkg.setTflowDirection(flowDirectioin);
			tpkg.setTflowAmount(flowAmount+"");
			tpkg.setTprotocolType(protocolType);
			tpkg.setTfirstVisitTime(new Timestamp(cDate.getTime()));
			tpkg.setTlastVisitTime(new Timestamp(cDate.getTime()));
		}else if(!inputConfig.isInput()){
			if(inputConfig.getStartTime()<cDate.getTime()&&inputConfig.getEndTime()>cDate.getTime()){
				tpkg=new Tpkg();
				tpkg.setTnativeMac(nativeMac);
				tpkg.setTremoteIp(remoteIp);
				tpkg.setTremotePort(remotePort);
				tpkg.setTflowDirection(flowDirectioin);
				tpkg.setTflowAmount(flowAmount+"");
				tpkg.setTprotocolType(protocolType);
				tpkg.setTfirstVisitTime(new Timestamp(cDate.getTime()));
				tpkg.setTlastVisitTime(new Timestamp(cDate.getTime()));
			}
		}*/
		if(inputConfig.isInput()){
			tpkg=new Tpkg();
			tpkg.setTnativeMac(nativeMac);
			tpkg.setTremoteIp(remoteIp);
			tpkg.setTremotePort(remotePort);
			tpkg.setTflowDirection(flowDirectioin);
			tpkg.setTflowAmount(flowAmount+"");
			tpkg.setTprotocolType(protocolType);
			tpkg.setTfirstVisitTime(new Timestamp(cDate.getTime()));
			tpkg.setTlastVisitTime(new Timestamp(cDate.getTime()));
		}
		
		return true;
	}
	/**
	 * ʱ���ַ�����ʽ��
	 * @author fjm
	 */
	public String dateFormat(String str){
		String s[]=str.split(" ");
		String result="";
		if(!s[1].equals(""))
		{
			result=s[0]+" "+s[1].replace(",", "")+" "+s[3].substring(0,8)+" "+s[2];
		}else
		{
			result=s[0]+" "+s[2].replace(",", "")+" "+s[4].substring(0,8)+" "+s[3];
		}
		return result;
	}
	
	public boolean isNumeric(String str){ 
		   Pattern pattern = Pattern.compile("[0-9]*"); 
		   Matcher isNum = pattern.matcher(str);
		   if( !isNum.matches() ){
		       return false; 
		   } 
		   return true; 
	}
}
