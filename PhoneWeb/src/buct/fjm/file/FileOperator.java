package buct.fjm.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileOperator {
	public static final String SYSTEM_SEPARATOR = System.getProperty("line.separator");
	private String path;
	private String oldCopyPath;
	private FileReader fr = null;
	private FileWriter fw = null;
	private StringBuffer buff = null;
	private BufferedReader br = null;
	private BufferedWriter bw = null;
	private String line;
	//Default constructor
	public FileOperator(){
		
	}
	//设置路径
	public void setDir(String path){
		this.path = path;
	}
	//设置拷贝路径
		public void initCopyPath(String oldPath){
			this.oldCopyPath = oldPath;
		}
	//修改键值函数
	public boolean writeKeyValue(String key, String value){
		try {
			fr = new FileReader(path);
			fw = new FileWriter("bak.conf",true);
			br = new BufferedReader(fr);
			while((line = br.readLine()) != null){
				String[] item = line.split("=");
				if(item[0].equals(key)){
					item[1] = value;
					fw.write(item[0]+"="+item[1]+SYSTEM_SEPARATOR);
					fw.flush();
				}
				else {
					fw.write(line+SYSTEM_SEPARATOR);
					fw.flush();
				}
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
	
	//分隔符为制表符的修改函数
		public boolean writeKeyValueWithTab(String key, String value){
			try {
				fr = new FileReader(path);
				fw = new FileWriter("bak.conf",true);
				br = new BufferedReader(fr);
				while((line = br.readLine()) != null){
					if(!line.startsWith("#")){
						String[] item = line.split("\t");
						if(item[0].equals("\"" + key + "\"")){
							item[2] = "\"" + value + "\"";
//							fw.write(item[0]+"="+item[1]+SYSTEM_SEPARATOR);
//							fw.flush();
							for(int i = 0; i < item.length-1; i++){
								fw.write(item[i]+"\t");
								fw.flush();
							}
							fw.write(item[item.length-1] + SYSTEM_SEPARATOR);
						}
						else {
							item[0] = "\"" + key + "\"";
							item[2] = "\"" + value + "\"";
							for(int i = 0; i < item.length-1; i++){
								fw.write(item[i]+"\t");
								fw.flush();
							}
							fw.write(item[item.length-1] + SYSTEM_SEPARATOR);
						}
					}
					else {
						fw.write(line + SYSTEM_SEPARATOR);
						fw.flush();
					}
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
				fw.write(line+SYSTEM_SEPARATOR);
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
	
	//最终的修改函数(分隔符为Tab)
		public String setKeyValueWithTab(String key, String value){
			if(writeKeyValueWithTab(key,value)){
				if(writeback())
					deleteBak();
				else 
					return "Failure";
			}
			else
				return "Failure";
			return null;
		}
		
		//拷贝初始文件到目标路径
		public boolean copyFile(String fileName, String newCopyPath){
			try {
				fr = new FileReader(oldCopyPath + fileName);
				br = new BufferedReader(fr);
				buff = new StringBuffer(4096);
				//String line = null;
				while ((line = br.readLine()) != null) {
					buff.append(line).append(FileOperator.SYSTEM_SEPARATOR);
				}
				fw = new FileWriter(newCopyPath + fileName);
				bw = new BufferedWriter(fw);
				bw.write(buff.toString());
				br.close();
				bw.close();
				return true;
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				return false;
			}
		}
	//显示所有键值
	public void showKeyValue(){
		try {
			fr = new FileReader(path);
			br = new BufferedReader(fr);
			while((line = br.readLine()) != null){
				System.out.println(line);
			}
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
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
	}

}