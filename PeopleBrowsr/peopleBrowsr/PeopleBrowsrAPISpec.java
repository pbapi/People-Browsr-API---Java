package peopleBrowsr;

import java.util.*;
import org.json.JSONObject;

public class PeopleBrowsrAPISpec {
    public static void main(String[] args) throws Exception {
        PeopleBrowsrAPI consumer = new PeopleBrowsrAPI("[app_id]", "[app_key]");
        HashMap<String, String> queryParams = new HashMap<String, String>();
        queryParams.put("last",   "yesterday");
        queryParams.put("count",  "30");
        queryParams.put("source", "twitter");
        queryParams.put("term",   "zombies");
        queryParams.put("limit",  "300");

        String[] methods = new String[] {
        	"atNameCloud",
        	"mentions",
            "density",
            "wordCloud",
            "hashTagCloud",
            "mentionsRetweets",
            "friendsAndFollowers",
            "topFollowers",
            "positiveTopFollowers",
            "negativeTopFollowers",
            "popularity",
            "sentiment",
            "topUsState",
            "topCountries",
            "topUrls",
            "topPictures",
            "topVideos",
            "kredOutreach",
            "kredInfluence"
        };
        for (String method : methods) {
	        JSONObject json = (JSONObject)PeopleBrowsrAPI
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
