package buct.fjm.ipAnalyse;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;

import net.sf.json.JSONObject;


public class SearchIp {

	public static IpAddress search(String ip)
	{
		String result="{}";
		String url="http://int.dpool.sina.com.cn/iplookup/iplookup.php?format=json&ip="+ip;
		try {
			HttpClient client = new HttpClient();
			GetMethod get = new GetMethod(url);
			client.executeMethod(get);
			result=get.getResponseBodyAsString();
	        get.releaseConnection();
		}catch (Exception e) {
            return null;
		}
		JSONObject jsonObject = JSONObject.fromObject(result);
		IpAddress ipaddress=new IpAddress();
        String start=new String(jsonObject.get( "start" ).toString());
        ipaddress.setStart(start);
        //System.out.println(start);
        String end=new String(jsonObject.get( "end" ).toString());
        ipaddress.setEnd(end);
        //System.out.println(end);
        String country=new String(jsonObject.get( "country" ).toString());
        ipaddress.setCountry(country);
        //System.out.println(country);
        String province=new String(jsonObject.get( "province" ).toString());
        ipaddress.setProvince(province);
        //System.out.println(province);
        String city=new String(jsonObject.get( "city" ).toString());
        ipaddress.setCity(city);
        //System.out.println(city);
        String district=new String(jsonObject.get( "district" ).toString());
        ipaddress.setDistrict(district);
        //System.out.println(district);
        String isp=new String(jsonObject.get( "isp" ).toString());
        ipaddress.setIsp(isp);
        //System.out.println(isp);
        
        return ipaddress;
	}
}
