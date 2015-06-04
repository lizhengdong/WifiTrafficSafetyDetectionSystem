package buct.fjm.jsonConver;

import java.util.List;

import buct.fjm.jsonutil.JSONArray;
import buct.fjm.jsonutil.JSONObject;
import buct.fjm.model.Uri;







public class ConverUriListToJson {

	public ConverUriListToJson() {
		super();
	}
	
	public static String ConverListToPageJson(List<Uri> list, int count) {
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObject = null;
		for (Uri ui : list) {
			ui.setPkguris(null);
			ui.setTpkguris(null);
			jsonObject = new JSONObject(ui);
			jsonArray.put(jsonObject);
		}
		String json = jsonArray.toString();
		String jsonString = "{\"totalCount\":" + count + ",\"rows\":" + json
				+ "}";
		return jsonString;
	}
}