package buct.fjm.config;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class InputConfig {
	private long startTime;
	private long endTime;
	private boolean isInput;
	private boolean truncate;
	private long lastTruncateTime;
	private final String configPath="/usr/local/raywork/conf/InputTConfig.conf";
	
	public InputConfig(){
		init();
	}
	
	private void init(){
		try {
	    	FileReader fr = new FileReader(configPath);
	        BufferedReader br = new BufferedReader(fr);
	        SimpleDateFormat format = new SimpleDateFormat("yyyy-M-dd HH:mm:ss");
	        try {
	            String line;
	            while ((line = br.readLine()) != null) {
	               String[] item=line.split("=");
	               if(item[0]!=null&&!item[0].equals("")&&item[1]!=null&&!item[1].equals("")){
	            	   try {
		            	   if(item[0].equals("StartTime")){
		            		   this.startTime=format.parse(item[1]).getTime();
			               }else if(item[0].equals("EndTime")){
			            	   this.endTime=format.parse(item[1]).getTime();
			               }else if(item[0].equals("IsInput")){
			            	   this.isInput=Boolean.parseBoolean(item[1]);
			            	}else if(item[0].equals("Truncate")){
			            		this.truncate=Boolean.parseBoolean(item[1]);
			            	}else if(item[0].equals("LastTruncateTime")){
			            		this.lastTruncateTime=format.parse(item[1]).getTime();
			            	    }
	            	   } 
	            	   catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
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

	public long getStartTime() {
		return startTime;
	}

	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}

	public long getEndTime() {
		return endTime;
	}

	public void setEndTime(long endTime) {
		this.endTime = endTime;
	}

	public boolean isInput() {
		return isInput;
	}

	public void setInput(boolean isInput) {
		this.isInput = isInput;
	}

	public boolean isTruncate() {
		return truncate;
	}

	public void setTruncate(boolean truncate) {
		this.truncate = truncate;
	}

	public long getLastTruncateTime() {
		return lastTruncateTime;
	}

	public void setLastTruncateTime(long lastTruncateTime) {
		this.lastTruncateTime = lastTruncateTime;
	}
	
	
}
