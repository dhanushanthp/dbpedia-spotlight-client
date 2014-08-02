package db.spot.client;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Properties;
import java.util.Scanner;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.json.JSONArray;
import org.json.JSONObject;

public class Client {
	//private final static String API_URL = "http://spotlight.dbpedia.org/";
	// private final static String API_URL = "http://10.52.128.116:2222/";

	private static HttpClient client = new HttpClient();

	public JSONArray process(String serviceUrl,String input,double confidence,int support) {
		JSONObject resultJSON = null;
		JSONArray entities = null;
		String spotlightResponse;
		try {
			
			GetMethod getMethod = new GetMethod(serviceUrl + "rest/annotate/?"
					+ "confidence=" + confidence + "&support=" + support
					+ "&text=" + URLEncoder.encode(input, "utf-8"));
			getMethod
					.addRequestHeader(new Header("Accept", "application/json"));

			getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
					new DefaultHttpMethodRetryHandler(3, false));

			// Execute the method.
			int statusCode = client.executeMethod(getMethod);

			if (statusCode != HttpStatus.SC_OK) {
				System.out.println("Method failed: "
						+ getMethod.getStatusLine());
			}

			// Read the response body.
			byte[] responseBody = getMethod.getResponseBody();
			spotlightResponse = new String(responseBody);

			resultJSON = new JSONObject(spotlightResponse);
			entities = resultJSON.getJSONArray("Resources");

			for (int i = 0; i < entities.length(); i++) {
				System.out.println(entities.getJSONObject(i).get("@URI").toString().substring(28));
				System.out.println(entities.getJSONObject(i)
						.get("@surfaceForm"));
				System.out.println(entities.getJSONObject(i).get("@offset"));
				System.out.println(entities.getJSONObject(i).get("@types")
						+ "\n\n");
			}
			
			System.out.println(spotlightResponse);
		} catch (IOException e) {
			System.out.println("Error");
		}
		return entities;
	}

}
