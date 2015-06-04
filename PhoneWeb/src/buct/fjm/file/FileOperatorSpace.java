package buct.fjm.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileOperatorSpace {
	private String path;
	private FileReader fr = null;
	private FileWriter fw = null;
	private BufferedReader br = null;
	private String line;
	//Default constructor
	public FileOperatorSpace(){
		
	}
	
	//设置路径
	public void setDir(String path){
		this.path = path;
	}
	//修改键值函数
	public boolean writeKeyValue(String key, String value){
		try {
			fr = new FileReader(path);
			fw = new FileWriter("bak.conf",true);
			br = new BufferedReader(fr);
			while((line = br.readLine()) != null){
				String[] item = line.split(" ");
				for(int i = 0;i < item.length;i++){
					int temp = i;
					if(item[i].equals(key)){
						temp++;
						item[temp] = value+";";
					}
					else if(item[i].equals("\t"+key)){
						temp++;
						item[temp] = value+";";
					}
				}
				for(int j = 0;j < item.length;j++){
					fw.write(item[j]+" ");
					fw.flush();
				}
				fw.write(FileOperator.SYSTEM_SEPARATOR);
				fw.flush();
			}
			return true;
		}
		catch(IOException e){
			e.printStackTrace();
			return false;
		}
		finally {
			if(fr != null){
				try {
					fr.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(fw != null){
				try {
					fw.close();
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
	}
	
	//改写地址池的函数
	public boolean writeRange(String value1, String value2){
		try {
			fr = new FileReader(path);
			fw = new FileWriter("bak.conf",true);
			br = new BufferedReader(fr);
			while((line = br.readLine()) != null){
				String[] item = line.split(" ");
				for(int i = 0;i < item.length;i++){
					int temp = i;
					if(item[i].equals("	range")){
						temp++;
						item[temp] = value1;
						temp++;
						item[temp] = value2 + ";";
					}
				}
				for(int j = 0;j < item.length;j++){
					fw.write(item[j]+" ");
					fw.flush();
				}
				fw.write(FileOperator.SYSTEM_SEPARATOR);
				fw.flush();
			}
			return true;
		}
		catch(IOException e){
			e.printStackTrace();
			return false;
		}
		finally {
			if(fr != null){
				try {
					fr.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(fw != null){
				try {
					fw.close();
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
	}
	//重写原文件
	public boolean writeback(){
		try {
			fr = new FileReader("bak.conf");
			fw = new FileWriter(path);
			br = new BufferedReader(fr);
			while((line = br.readLine()) != null){
				fw.write(line+FileOperator.SYSTEM_SEPARATOR);
				fw.flush();
			}
			return true;
		}
		catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
		finally {
			if(br != null){
				try {
					br.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(fw != null){
				try {
					fw.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(fr != null){
				try {
					fr.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	//删除副本
	public String deleteBak(){
		File file = new File("bak.conf");
		if(file.exists()){
			boolean d = file.delete();
			if(d)
				return "Success";
			else
				return "Failure";
		}
		else
			return "Failure";
	}
	
	//最终的修改函数
	public String setKeyValue(String key, String value){
		if(writeKeyValue(key,value)){
			if(writeback())
				deleteBak();
			else 
				return "Failure";
		}
		else
			return "Failure";
		return null;
	}
	
	//最终的修改地址池函数
	public String setRange(String value1, String value2){
		if(writeRange(value1, value2)){
			if(writeback())
				deleteBak();
			else 
				return "Failure";
		}
		else
			return "Failure";
		return null;
	}
}