package db.spot.client;

import java.io.IOException;
import java.net.URLEncoder;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.json.JSONArray;
import org.json.JSONObject;

public class Client{
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
			
			//System.out.println(spotlightResponse);
		} catch (IOException e) {
			System.out.println("Error");
		}
		return entities;
	}
	
	public String [] getCate(String input){
		
		String[] in = input.split(",");
		String[] result = new String[in.length];
		for (int i =0; i< in.length; i++) {
			System.out.println(in[i].replace("DBpedia:", ""));
			result[i] = in[i].replace("DBpedia:", "");
			result[i] = in[i].replace("Freebase:/", "");
		}
		return result ;
	}
	
	public static void main(String[] args) {
		
	}

}
