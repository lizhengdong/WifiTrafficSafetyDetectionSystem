package buct.fjm.po;

import java.io.File;
import java.io.IOException;

import buct.fjm.analyse.FileAnalyse;
import buct.fjm.config.SystemConfig;

public class InputData {

	private SystemConfig config=null;
	/**
	 * @author fjm
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws InterruptedException, IOException{
		// TODO Auto-generated method stub
		InputData inputData=new InputData();
		int fileCount=0;
		//��ʼ�������ļ�
		inputData.InitConfig();
		while(inputData.getConfig().isContinue()){
			//ÿ�α�����Ϣʱ��
			//Thread.sleep(inputData.getConfig().getDelay());
			Thread.sleep(1000);
			//���������Ŀ¼�ļ�
			
			File file = new File(inputData.getConfig().getConfig().getFilePath());
			//�������ñ���Ŀ¼�ļ�
			System.out.println(file.getName());
			File[] subFile = file.listFiles();
			System.out.println(subFile.length);
			int fileCounttest=0;
			if(subFile.length>inputData.getConfig().getMaxFileCount()){
				fileCount=inputData.getConfig().getMaxFileCount();
			}else{
				fileCount=subFile.length;
			}
			
			File theNewOne=null;
			File excuteNow=null;
			int i=0;
			for (int iFileLength = 0; iFileLength < fileCount; iFileLength++) {
				if(i==fileCount-1){
					continue;
				}
				Thread.sleep(1000);
				if(theNewOne==null){
					theNewOne=subFile[iFileLength];
				}else if(theNewOne.lastModified()<subFile[iFileLength].lastModified())
				{
					excuteNow=theNewOne;
					theNewOne=subFile[iFileLength];
					
				}else{
					excuteNow=subFile[iFileLength];
				}
				if(excuteNow==null){
					continue;
				}
				if(!excuteNow.renameTo(excuteNow)){
					Thread.sleep(10);
					System.out.println("正在占用");
				}
				if(excuteNow!=null&&excuteNow.isFile() &&excuteNow.exists()){
					System.out.println("begin do task");
					System.out.println(excuteNow.getName());
					inputData.doTask(excuteNow);
					System.out.println("end do task");
					inputData.removeFileToDis(excuteNow.getAbsolutePath());
					i++;
				}
				
			}
			
			fileCounttest++;
			//System.out.println(fileCounttest);
		}
		
	}
	/**
	 * �����ļ�������Ŀ¼
	 * @author fjm
	 */
	public void removeFileToDis(String srcPath){
		 // File (or directory) to be moved 
		  File file = new File(srcPath); 
		  
		  // Destination directory 
		  File dir = new File(config.getCopyPath()); 
		  
		  // Move file to new directory 
		  boolean success = file.renameTo(new File(dir, file.getName())); 
		  if(!success){
			  System.out.println("����ʧ��");
		  }
	}
	/**
	 * ����ִ�о�������
	 * @author fjm
	 * @throws IOException 
	 */
	public void doTask(File file) throws IOException{
		//�����ļ�
		FileAnalyse fileAnalyse=new FileAnalyse();
		fileAnalyse.Analyse(file);
	}
	
	/**
	 * ��ʼ�������ļ�
	 * @author fjm
	 * @param config
	 * @return null
	 */
	private void InitConfig(){
		config=SystemConfig.getConfig();
	}

	public SystemConfig getConfig() {
		return config;
	}
}
