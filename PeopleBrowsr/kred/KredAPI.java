package kred;

import java.util.*;
import java.util.Map.Entry;
import java.net.*;
import java.io.*;
import org.json.*;

/*
 Usage:
 
 KredAPI consumer = new KredAPI("[app_id]", "[app_key]");
 HashMap<String, String> queryParams = new HashMap<String, String>();
 queryParams.put("source", "twitter");
 queryParams.put("term",   "zombies");
 JSONObject json = consumer.kredScore(queryParams);
*/
public class KredAPI {
	private String id, key, baseURL;
	
	public KredAPI(String id, String key) {
		this.id = id;
		this.key = key;
		this.baseURL = "http://api.kred.com";
	}
	
	public JSONObject kred(HashMap<String, String> queryParams) throws IOException, InterruptedException, JSONException {
        return fetch("kred", queryParams);
    }

    public JSONObject kredScore(HashMap<String, String> queryParams) throws IOException, InterruptedException, JSONException {
        return fetch("kredscore", queryParams);
    }

    private JSONObject fetch(String page, HashMap<String, String> queryParams) throws IOException, InterruptedException, JSONException {
        String addr = String.format("%1$s/%2$s?app_id=%3$s&app_key=%4$s", this.baseURL, page, id, key);
        Iterator<Entry<String, String>> iter = queryParams.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry param = iter.next();
            addr += String.format("&%1$s=%2$s", param.getKey(), param.getValue());
        }

        URL url = new URL(addr);
        JSONObject data;
        int numTries = 0;

        do {
          URLConnection connection = url.openConnection();
          BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
          String strData = "", line;

          try {
              if (numTries++ > 0)
                  Thread.sleep(500);
              while ((line = in.readLine()) != null)
                  strData += line;
          } finally {
              in.close();
          }

          data = new JSONObject(strData);
      }
      while (!data.get("status").equals("complete"));

      return data;
    }
}
