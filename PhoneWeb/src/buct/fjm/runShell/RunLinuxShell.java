package buct.fjm.runShell;

import java.io.InputStreamReader;
import java.io.LineNumberReader;

public class RunLinuxShell {
	//运行linux脚本
	public static void run(){
		   String stopWifi = "sh /usr/local/raywork/stopwifi.sh";
			String startWifi = "sh /usr/local/raywork/startwifi.sh";
			runCommand(stopWifi);
			runCommand(startWifi);
	}
	private static void runCommand(String command){
		try{
			String shPath = command;
			Process process = Runtime.getRuntime().exec(shPath);
			InputStreamReader isr = new InputStreamReader(process.getInputStream());
			LineNumberReader input = new LineNumberReader(isr);
			String line;
			while((line = input.readLine()) != null){
				System.out.println(line);
			}
			input.close();
			isr.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
