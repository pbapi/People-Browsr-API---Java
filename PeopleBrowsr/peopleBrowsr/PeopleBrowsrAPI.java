package peopleBrowsr;

import java.util.*;
import java.util.Map.Entry;
import java.net.*;
import java.io.*;
import org.json.*;

/*
 Usage:
 
 PeopleBrowsrAPI consumer = new PeopleBrowsrAPI("[app_id]", "[app_key]");
 HashMap<String, String> queryParams = new HashMap<String, String>();
 queryParams.put("last",   "yesterday");
 queryParams.put("count",  "30");
 queryParams.put("source", "twitter");
 queryParams.put("term",   "zombies");
 queryParams.put("limit",  "300");
 JSONObject json = consumer.atNameCloud(queryParams);
*/
public class PeopleBrowsrAPI {
	private String id, key, baseURL;
	
	public PeopleBrowsrAPI(String id, String key) {
		this.id = id;
		this.key = key;
		this.baseURL = "http://api.peoplebrowsr.com";
	}
	public JSONObject atNameCloud(HashMap<String, String> queryParams) throws IOException, InterruptedException, JSONException {
        return fetch("atnamecloud", queryParams);
    }

    public JSONObject mentions(HashMap<String, String> queryParams) throws IOException, InterruptedException, JSONException {
        return fetch("mentions", queryParams);
    }

    public JSONObject density(HashMap<String, String> queryParams) throws IOException, InterruptedException, JSONException {
        return fetch("density", queryParams);
    }

    public JSONObject wordCloud(HashMap<String, String> queryParams) throws IOException, InterruptedException, JSONException {
        return fetch("wordcloud", queryParams);
    }

    public JSONObject hashTagCloud(HashMap<String, String> queryParams) throws IOException, InterruptedException, JSONException {
        return fetch("hashtagcloud", queryParams);
  }

    public JSONObject mentionsRetweets(HashMap<String, String> queryParams) throws IOException, InterruptedException, JSONException {
        return fetch("mentions-retweets", queryParams);

    }

    public JSONObject friendsAndFollowers(HashMap<String, String> queryParams) throws IOException, InterruptedException, JSONException {
        return fetch("friendsandfollowers", queryParams);
    }

    public JSONObject topFollowers(HashMap<String, String> queryParams) throws IOException, InterruptedException, JSONException {
        return fetch("top-followers", queryParams);
    }

    public JSONObject positiveTopFollowers(HashMap<String, String> queryParams) throws IOException, InterruptedException, JSONException {
        return fetch("Top-Positive-Followers", queryParams);
    }

    public JSONObject negativeTopFollowers(HashMap<String, String> queryParams) throws IOException, InterruptedException, JSONException {
        return fetch("wordcloud", queryParams);
    }

    public JSONObject popularity(HashMap<String, String> queryParams) throws IOException, InterruptedException, JSONException {
        return fetch("popularity", queryParams);
    }

    public JSONObject sentiment(HashMap<String, String> queryParams) throws IOException, InterruptedException, JSONException {
        return fetch("sentiment", queryParams);
    }

    public JSONObject topUsState(HashMap<String, String> queryParams) throws IOException, InterruptedException, JSONException {
        return fetch("top-usarea", queryParams);
    }

    public JSONObject topCountries(HashMap<String, String> queryParams) throws IOException, InterruptedException, JSONException {
        return fetch("topcountries", queryParams);
    }

    public JSONObject topUrls(HashMap<String, String> queryParams) throws IOException, InterruptedException, JSONException {
        return fetch("topurls", queryParams);
    }

    public JSONObject topPictures(HashMap<String, String> queryParams) throws IOException, InterruptedException, JSONException {
        return fetch("toppictures", queryParams);
    }

    public JSONObject topVideos(HashMap<String, String> queryParams) throws IOException, InterruptedException, JSONException {
        return fetch("topvideos", queryParams);
    }
    
    public JSONObject kredOutreach(HashMap<String, String> queryParams) throws IOException, InterruptedException, JSONException {
        return fetch("kredoutreach", queryParams);
    }

    public JSONObject kredInfluence(HashMap<String, String> queryParams) throws IOException, InterruptedException, JSONException {
        return fetch("kredinfluence", queryParams);
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
