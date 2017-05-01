package utils;

import java.io.IOException;
import java.nio.charset.Charset;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import ui.CWarningDialog;

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
}
