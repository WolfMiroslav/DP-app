package utils;

import java.io.IOException;
import java.nio.charset.Charset;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

public class CUtils {
	
	public static String MD5(char[] md5) {
		try {
			java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
			byte[] array = md.digest(new String(md5).getBytes(Charset.forName("UTF-8")));
		    StringBuffer sb = new StringBuffer();
		    for (int i = 0; i < array.length; ++i) {
		    	sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
		    }
		    return sb.toString();
		} 
		catch (java.security.NoSuchAlgorithmException e) 
		{
			e.printStackTrace();
		}
		return null;
	}
	
	public static String executePost(String targetURL, String body) throws IOException {
		
		try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {
			HttpPost request = new HttpPost(targetURL);
			StringEntity params = new StringEntity(body);
			request.addHeader("content-type", "application/json");
			request.setEntity(params);
			HttpResponse result = httpClient.execute(request);
			
			String json = EntityUtils.toString(result.getEntity(), "UTF-8");
			System.out.println(json);
			//Gson gson = new Gson();
			//Response jsonResponse = gson.fromJson(json, Response.class);
			
		}				
		return null;		
	}
}
