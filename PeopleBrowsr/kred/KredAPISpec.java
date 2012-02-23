package kred;

import java.util.HashMap;
import org.json.JSONObject;

public class KredAPISpec {
	public static void main(String[] args) throws Exception {
        KredAPI consumer = new KredAPI("[app_id]", "[app_key]");
        HashMap<String, String> queryParams = new HashMap<String, String>();
        queryParams.put("source", "twitter");
        queryParams.put("term",   "zombies");

        String[] methods = new String[] {
            "kred",
            "kredScore"
        };
        for (String method : methods) {
	        JSONObject json = (JSONObject)KredAPI
	        		.class
	        		.getMethod(method, queryParams.getClass())
	        		.invoke(consumer, queryParams);
	        
	        Object data = json.get("data");
	        if (data == null)
	            throw new Exception("No data!");
	        System.out.println(data.toString());
        }
    }
}
