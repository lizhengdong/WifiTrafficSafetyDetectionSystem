package buct.fjm.getLinuxInfo;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Enumeration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GetLinuxIP {
	/**
	 * Get host IP address
	 * 
	 * @return IP Address
	 */
	public static String getAddress() {
		String ip = "";
		String tempStr = "";
		try {
			Enumeration<NetworkInterface> enumeration = NetworkInterface.getNetworkInterfaces();
			while (enumeration.hasMoreElements()) {
			    NetworkInterface networkInterface = enumeration.nextElement();
			 
			    if (networkInterface.isUp()) {
			        //System.out.println(networkInterface.getDisplayName());
			        Enumeration<InetAddress> addressEnumeration = networkInterface.getInetAddresses();
			 
			        while (addressEnumeration.hasMoreElements()) {
			        	tempStr = addressEnumeration.nextElement().toString();
			        	int j = tempStr.indexOf("/");
			        	tempStr = tempStr.substring(j+1, tempStr.length());
			        	if(isIpv4(tempStr)){
			        		if(!tempStr.equals("127.0.0.1")){
			        			ip = tempStr;
			        		}
			        	}
			        }
			    }
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return ip;
	}
//	public static void main(String[] args){
//		getAddress();
//	}
	public static boolean isIpv4(String ipAddress) {
		
        String ip = "^(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|[1-9])\\."
                + "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\."
                + "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\."
                + "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)$";
        Pattern pattern = Pattern.compile(ip);
        Matcher matcher = pattern.matcher(ipAddress);
        return matcher.matches();
    }
}
