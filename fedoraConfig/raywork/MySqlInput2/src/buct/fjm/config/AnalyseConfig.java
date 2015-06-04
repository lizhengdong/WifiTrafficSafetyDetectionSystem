package buct.fjm.config;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * ��ȡ���������ļ�
 * @author fjm
 *
 */
public class AnalyseConfig {
	//����Mac��ַ
	private String srcMac;
	//�ָ��
	private String decollator;
	//tcp���ͺ�
	private String tcpType;
	//udp���ͺ�
	private String udpType;
	//�Ƿ�ʼ��ʱ����
	private boolean isInputT;
	private final String configPath="/usr/local/raywork/conf/AnalyseConfig.conf";
	/**
	 * ���캯��
	 */
	public AnalyseConfig(){
		init();
	}
	/**
	 * ӳ�������ļ�����AnalyseConfig
	 */
	private void init(){
		try {
	    	FileReader fr = new FileReader(configPath);
	        BufferedReader br = new BufferedReader(fr);
	        try {
	            String line;
	            while ((line = br.readLine()) != null) {
	               String[] item=line.split("=");
	               if(item[0]!=null&&!item[0].equals("")&&item[1]!=null&&!item[1].equals("")){
	            	   if(item[0].equals("NativeMac")){
		            	   this.srcMac=item[1];
		               }else if(item[0].equals("Decollator")){
		            	   this.decollator=item[1];
		               }else if(item[0].equals("TCPType")){
		            	   this.tcpType=item[1];
		               }else if(item[0].equals("UDPType")){
		            	   this.udpType=item[1];
		               }else if(item[0].equals("IsInputT")){
		            	   if(item[1].equals("true")){
		            		   this.isInputT=true;
		            	   }else{
		            		   this.isInputT=false;
		            	   }
		               }
	               }
	            }
	        } finally {
	            br.close();
	        }
	    } catch (IOException ex) {
	        System.out.println("Read transaction records failed."
	                + ex.getMessage());
	        System.exit(1);
	    }
	}
	
	public String getSrcMac() {
		return srcMac;
	}
	public void setSrcMac(String srcMac) {
		this.srcMac = srcMac;
	}
	public String getDecollator() {
		return decollator;
	}
	public void setDecollator(String decollator) {
		this.decollator = decollator;
	}
	public String getTcpType() {
		return tcpType;
	}
	public void setTcpType(String tcpType) {
		this.tcpType = tcpType;
	}
	public String getUdpType() {
		return udpType;
	}
	public void setUdpType(String udpType) {
		this.udpType = udpType;
	}
	public String getConfigPath() {
		return configPath;
	}
	public boolean isInputT() {
		return isInputT;
	}
	public void setInputT(boolean isInputT) {
		this.isInputT = isInputT;
	}
	
	
}
