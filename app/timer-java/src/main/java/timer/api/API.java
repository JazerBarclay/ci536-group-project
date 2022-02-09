package timer.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * A simple API class designed to manage any http request made to an internet API
 * and receive a response code and body of content.
 * 
 * Will need addition of sending post,put,etc requests and managing JSON responses
 * 
 * @author Jazer
 */
public class API {

  private static HttpURLConnection conn;
  private String endpointURL;
  
  public API(String url) {
    endpointURL = url;
  }
  
  
  public HttpResponse request() {
    return request(endpointURL);
  }
  
  public static HttpResponse request(String address) {
    
    URL url;
    int resStatus = 406;
    StringBuilder resContent = new StringBuilder();
    
    try {
      
      url = new URL(address);
      conn = (HttpURLConnection) url.openConnection();
      
      // Request setup
      conn.setRequestMethod("GET");
      conn.setConnectTimeout(5000);// 5000 milliseconds = 5 seconds
      conn.setReadTimeout(5000);
      
      resStatus = conn.getResponseCode();
      
      BufferedReader reader;
      String line;
      
      if (resStatus >= 300) {
        reader = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        while ((line = reader.readLine()) != null) {
          resContent.append(line);
        }
        reader.close();
      }
      else {
        reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        while ((line = reader.readLine()) != null) {
          resContent.append(line);
        }
        reader.close();
      }
      
    } catch (MalformedURLException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      conn.disconnect();
    }
    
    return new HttpResponse(resStatus, resContent.toString());
    
  }
  
}
