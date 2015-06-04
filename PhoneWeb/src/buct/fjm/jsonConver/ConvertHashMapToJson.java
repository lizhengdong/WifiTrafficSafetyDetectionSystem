package buct.fjm.jsonConver;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class ConvertHashMapToJson {

	public static String hashMapToJson(Map<String, String> readConfigResult) {
		String resultString = "{";
		for (Iterator it = readConfigResult.entrySet().iterator(); it.hasNext();) {
			Entry e = (Entry) it.next();
			resultString += "'" + e.getKey() + "':";
			resultString += "'" + e.getValue() + "',";
		}
		resultString = resultString.substring(0, resultString.lastIndexOf(","));
		resultString += "}";
		return resultString;
	}
}
