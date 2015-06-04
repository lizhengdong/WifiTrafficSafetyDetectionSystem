package buct.fjm.jsonConver;

import java.util.List;

import buct.fjm.jsonutil.JSONArray;
import buct.fjm.jsonutil.JSONObject;
import buct.fjm.model.Pkg;







public class ConverPkgListToJson {

	public ConverPkgListToJson() {
		super();
	}
	
	public static String ConverListToPageJson(List<Pkg> list, int count) {
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObject = null;
		for (Pkg ui : list) {
			//ui.setForum(null);
			ui.setPkguris(null);
			jsonObject = new JSONObject(ui);
			jsonArray.put(jsonObject);
		}
		String json = jsonArray.toString();
		String jsonString = "{\"totalCount\":" + count + ",\"rows\":" + json
				+ "}";
		return jsonString;
	}
}