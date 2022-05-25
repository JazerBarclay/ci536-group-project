package timer.api;

/**
 * Stores the response object and response code from an API request
 * 
 * @author Jazer
 *
 */
public class HttpResponse {

  private int responseCode;
  private String body;

  public HttpResponse(int responseCode, String body) {
    this.responseCode = responseCode;
    this.body = body;
  }

  public int getResponseCode() {
    return responseCode;
  }

  public String getBody() {
    return body;
  }

}
