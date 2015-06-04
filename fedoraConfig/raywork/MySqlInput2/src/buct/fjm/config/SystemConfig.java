package buct.fjm.config;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


/**
 * ��ȡϵͳ����
 * @author fjm
 *
 */
public class SystemConfig {
	private static SystemConfig systemConfig;
	//�������ļ�Ŀ¼
	private String filePath;
	//�����ļ�Ŀ¼
	private String copyPath;
	//����������ļ���
	private int maxFileCount;
	//����·������Ϣʱ��
	private int delay;
	//�Ƿ����ִ�г���
	private boolean isContinue;
	private final String configPath="/usr/local/raywork/conf/SystemConfig.conf";
	/**
	 * �������캯��
	 */
	private SystemConfig(){
		init();
	}
	/**
	 * ӳ�������ļ�����SystemConfig
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
	            	   if(item[0].equals("FilePath")){
		            	   this.filePath=item[1];
		               }else if(item[0].equals("CopyPath")){
		            	   this.copyPath=item[1];
		               }else if(item[0].equals("MaxFileCount")){
		            	   this.maxFileCount=Integer.parseInt(item[1]);
		               }else if(item[0].equals("Delay")){
		            	   this.delay=Integer.parseInt(item[1]);
		               }else if(item[0].equals("IsContinue")){
		            	   if(item[1].equals("true")){
		            		   this.isContinue=true;
		            	   }else{
		            		   this.isContinue=false;
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
	
	public static SystemConfig getConfig() {
		if(systemConfig==null){
			systemConfig=new SystemConfig();
		}
		return systemConfig;
	}
	public static SystemConfig getSystemConfig() {
		return systemConfig;
	}
	public static void setSystemConfig(SystemConfig systemConfig) {
		SystemConfig.systemConfig = systemConfig;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getCopyPath() {
		return copyPath;
	}
	public void setCopyPath(String copyPath) {
		this.copyPath = copyPath;
	}
	public int getMaxFileCount() {
		return maxFileCount;
	}
	public void setMaxFileCount(int maxFileCount) {
		this.maxFileCount = maxFileCount;
	}
	public int getDelay() {
		return delay;
	}
	public void setDelay(int delay) {
		this.delay = delay;
	}
	public boolean isContinue() {
		return isContinue;
	}
	public void setContinue(boolean isContinue) {
		this.isContinue = isContinue;
	}
	public String getConfigPath() {
		return configPath;
	}
}
