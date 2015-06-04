package buct.fjm.file;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class FileScanner {

	private FileReader fr = null;
	private BufferedReader br = null;
	private String line;
	
	//Default constructor
	public FileScanner(){
		
	}
	
	//生成mac列表文件
	public void generateMacFile(){
		try{
			String[] cmd={"/bin/sh","-c","arp >/usr/local/raywork/arp.txt"};
			Runtime.getRuntime().exec(cmd);
			//System.out.println("Generated!");
		} catch(Exception e){
			e.printStackTrace();
		}
	}
	
	//解析Mac列表
	public ArrayList<String> AnalyseMac(String path,String device){
		ArrayList<String> macList = new ArrayList<String>();
		try {
			fr = new FileReader(path);
			br = new BufferedReader(fr);
			while((line = br.readLine()) != null){
				String[] item = line.split(" ");
				String temp = "";
				for(int i = 0; i < item.length; i++){
					if(!item[i].equals("")){
						temp += item[i];
						temp += ",";
					}
				}
				String[] newitem = temp.split(",");
				if(newitem.length == 5 && newitem[newitem.length - 1].equals(device)){
					macList.add(newitem[newitem.length - 3]);
				}
			}
		} catch(IOException e){
			e.printStackTrace();
		}
		finally{
			if(fr != null){
				try {
					fr.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(br != null){
				try {
					br.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		//System.out.println("To be returned!");
		return macList;
	}
}