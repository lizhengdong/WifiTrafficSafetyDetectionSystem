package buct.fjm.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.RandomAccessFile;

public class FileManage {

	private static BufferedReader bufrd;
	private static File fileName;

	public FileManage(String filePath) {
		fileName = new File(filePath);
	}

	public static void createFile() throws IOException {
		if (!fileName.exists()) {
			fileName.createNewFile();
		}else{
			fileName.delete();
			fileName.createNewFile();
		}
	}

	public static String readFile() throws IOException {
		String tempRead;
		FileReader myFileReader;
		String readResult = "";
		try {
			myFileReader = new FileReader(fileName);
			bufrd = new BufferedReader(myFileReader);
			try {
				while ((tempRead = bufrd.readLine()) != null) {
					readResult = readResult + tempRead + "\r\n";			
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return readResult;
	}

	public static boolean writeFile(String newStr) throws IOException {
		RandomAccessFile raf = null;
		try {
			
			createFile();
			raf = new RandomAccessFile(fileName, "rw");
			raf.writeBytes(newStr);
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		} finally {
			if (raf != null) {
				try {
					raf.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static boolean replaceFileContent(String oldStr, String newStr)
			throws IOException {
		String tempStr = "";
		StringBuffer myStringBuffer = new StringBuffer();
		try {
			FileReader myFileReader = new FileReader(fileName);
			BufferedReader myBufferedReader = new BufferedReader(myFileReader);
			while ((tempStr = myBufferedReader.readLine()) != null) {
				if (tempStr.equals(oldStr)) {
					myStringBuffer.append(newStr).append("\n");
					continue;
				}
				myStringBuffer.append(tempStr).append("\n");
			}
			myBufferedReader.close();
			FileOutputStream myFileOutputStream = new FileOutputStream(fileName);
			PrintWriter myPrintWriter = new PrintWriter(myFileOutputStream);
			myPrintWriter.write(myStringBuffer.toString().toCharArray());
			myPrintWriter.flush();
			myPrintWriter.close();
			myFileOutputStream.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
