package buct.fjm.util;
import java.util.Map;
public class ConfigCreate {

	public static void create(String filePath) throws Exception{
		FileManage myFileManage = new FileManage(filePath);
		myFileManage.createFile();
	}
}
