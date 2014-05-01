package com.npu.shareinfo;

import java.util.Map.Entry;

import com.google.appengine.api.datastore.Entity;
import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.appengine.labs.repackaged.org.json.JSONObject;

public class ConvertEntityToJson {

	public static JSONObject getJson(Entity result) {
		JSONObject jsonObject = new JSONObject();

		try {
			if (result.getKey().getName() != null) {
				jsonObject.put("ID", result.getKey().getName());
				System.out.println("##NAME###");
			} else {
				jsonObject.put("ID", result.getKey().getId());
				System.out.println("##ID###");
			}
			for (Entry<String, Object> entry : result.getProperties()
					.entrySet()) {
				jsonObject.put(entry.getKey(), entry.getValue());
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return jsonObject;
	}

}