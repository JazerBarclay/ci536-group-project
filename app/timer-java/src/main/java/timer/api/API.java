package timer.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

/**
 * A simple API class designed to manage any http request made to an internet
 * API and receive a response code and body of content.
 * 
 * Will need addition of sending post,put,etc requests and managing JSON
 * responses
 * 
 * @author Jazer
 */
public class API {

    // 
    private static HttpURLConnection conn;

    // 
    private String endpointURL;

    /**
     * Initalise api with a url
     * @param url
     */
    public API(String url) {
	endpointURL = url;
    }

    /**
     * Make a fixed request to endpoint
     * @return 
     */
    public HttpResponse getRequest() {
	return getRequest(endpointURL);
    }

    /**
     * Request data from a given address endpoint
     * @param address
     * @return HTTP Response
     */
    public static HttpResponse getRequest(String address) {

	// URL object
	URL url;

	// Default response status 406 (Not Acceptable)
	int resStatus = 406;

	// String response content
	StringBuilder resContent = new StringBuilder();

	try {

	    // Create a new URL object using address
	    url = new URL(address);

	    // Create connection using URL object
	    conn = (HttpURLConnection) url.openConnection();

	    // Request setup and timeout after 5000 milliseconds (5 seconds)
	    conn.setRequestMethod("GET");
	    conn.setConnectTimeout(5000);
	    conn.setReadTimeout(5000);

	    // Store response code in resStatus
	    resStatus = conn.getResponseCode();

	    // Input reader
	    BufferedReader reader;

	    // Line of input
	    String line;

	    // If status > 300 (aka an error) set reader to error stream
	    if (resStatus >= 300) reader = new BufferedReader(new InputStreamReader(conn.getErrorStream()));

	    // Otherwise set reader to input stream
	    else reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

	    // Read each line in buffer and append to content
	    while ((line = reader.readLine()) != null) resContent.append(line);

	    // Close reader
	    reader.close();

	} catch (MalformedURLException e) {
	    e.printStackTrace();
	} catch (IOException e) {
	    e.printStackTrace();
	} finally {
	    // Disconnect after try catch
	    conn.disconnect();
	}

	// Return resulting http response
	return new HttpResponse(resStatus, resContent.toString());

    }

    public static HttpResponse postRequest(String endpoint, Map<String,String> json) throws MalformedURLException,IOException {
	URL url;
	HttpResponse res;

	url = new URL(endpoint);
	HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	conn.setRequestMethod("POST");
	conn.setRequestProperty("Content-Type", "application/json; charset=utf-8");
	conn.setRequestProperty("Accept", "application/json");
	conn.setDoInput(true);
	conn.setDoOutput(true);
	conn.connect();

	// Convert json <string,string> map to json string
	String jsonInputString = "";
	jsonInputString += "{";
	int i = 0;
	for (String s : json.keySet()) {
	    jsonInputString+="\"" + s + "\":\""+json.get(s)+"\"";
	    i++;
	    if (i < json.keySet().size()) jsonInputString += ",";
	}
	jsonInputString += "}";


	try(OutputStream os = conn.getOutputStream()) {
	    byte[] input = jsonInputString.getBytes("utf-8");
	    os.write(input, 0, input.length);
	}
	
	if (conn.getResponseCode() >= 400) return new HttpResponse(conn.getResponseCode(), "");

	try(BufferedReader br = new BufferedReader(
		new InputStreamReader(conn.getInputStream(), "utf-8"))) {
	    StringBuilder response = new StringBuilder();
	    String responseLine = null;
	    while ((responseLine = br.readLine()) != null) {
		response.append(responseLine.trim());
	    }
	    res = new HttpResponse(conn.getResponseCode(), response.toString());
	}

	return res;
    }

}
