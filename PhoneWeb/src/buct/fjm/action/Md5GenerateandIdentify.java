package buct.fjm.action;

import java.security.MessageDigest;

public class Md5GenerateandIdentify {
	//十六进制数字到字符映射数组
	private final static String[] hexDigits = {"0","1","2","3","4","5","6","7","8","9","a","b","c","d","e","f"};
	//加密inputString
	public static String generatePassword(String inputString){
		return encodeByMD5(inputString);
	}
	//验证密码
	public static boolean identifyPassword(String password, String inputString){
		if(password.equals(encodeByMD5(inputString))){
			return true;
		}
		else {
			return false;
		}
	}
	//实现加密方法
	private static String encodeByMD5(String originString){
		if(originString != null){
			try{
				//算法信息摘要
				MessageDigest md = MessageDigest.getInstance("MD5");
				//用指定的字节数组更新摘要并完成计算
				byte[] result = md.digest(originString.getBytes());
				//得到的字节数组变成字符串并返回
				String resultString = byteArrayToHexString(result);
				return resultString.toUpperCase();
			}
			catch (Exception ex){
				ex.getStackTrace();
			}
		}
		return null;
	}
	//字节数组转化为十六进制字符串
	private static String byteArrayToHexString(byte[] b){
		StringBuffer resultSb = new StringBuffer();
		for(int i = 0;i < b.length; i++){
			resultSb.append(byteToHexString(b[i]));
		}
		return resultSb.toString();
	}
	//单个字节转化成十六进制字符串
	private static String byteToHexString(byte b){
		int n = b;
		if(n < 0)
			n = 256 + n;
		int d1 = n / 16;
		int d2 = n % 16;
		return hexDigits[d1] + hexDigits[d2];
	}

}
