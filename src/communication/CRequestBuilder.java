package communication;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import ui.CWarningDialog;

public class CRequestBuilder {
	
	public static String executePost(String targetURL, String body) throws IOException {
		
		try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {
			HttpPost request = new HttpPost(targetURL);
			StringEntity params = new StringEntity(body);
			request.addHeader("content-type", "application/json");
			request.setEntity(params);
			HttpResponse result = httpClient.execute(request);
			
			String json = EntityUtils.toString(result.getEntity(), "UTF-8");
			JsonParser parser = new JsonParser();
			JsonObject o = parser.parse(json).getAsJsonObject();
						
			if(o.get("id").getAsInt() < 0)
			{
				new CWarningDialog("Wrong name or password.");
			}			
			
			//Gson gson = new Gson();
			//Response jsonResponse = gson.fromJson(json, Response.class);
			
		}				
		return null;		
	}
}
