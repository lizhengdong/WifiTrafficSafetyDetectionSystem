package buct.fjm.jsonConver;

import buct.fjm.jsonutil.JSONObject;

/**
 * 将对象转为json数据
 * 
 * @author Administrator
 * 
 */
public class ConvertObjectToJson {

	public ConvertObjectToJson() {
		super();
	}

	/**
	 * 将薪酬发放单详细信息转换成json数据格式
	 * 
	 * @param mpd
	 *            薪酬发放单详细信息实�?
	 * @return json数据
	 */
	public static String convertToJson(
			Object mpd) {
		JSONObject jo = new JSONObject(mpd);
		String json = jo.toString();
		return json;
	}

}
