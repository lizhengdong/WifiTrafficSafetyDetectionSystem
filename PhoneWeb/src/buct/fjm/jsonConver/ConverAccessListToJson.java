package buct.fjm.jsonConver;

import java.util.List;

import buct.fjm.jsonutil.JSONArray;
import buct.fjm.jsonutil.JSONObject;

public class ConverAccessListToJson {
	public static String ConverListToPageJson(List list, int count) {
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObject = null;
		for (Object ui : list) {
			//ui.setForum(null);
			jsonObject = new JSONObject(ui);
			jsonArray.put(jsonObject);
		}
		String json = jsonArray.toString();
		String jsonString = "{\"totalCount\":" + count + ",\"rows\":" + json
		+ "}";
		return jsonString;
	}
}
