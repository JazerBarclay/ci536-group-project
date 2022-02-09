package timer.api;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestAPI {

  @Test
  public void testRequest() {
    // Send request for images matching the term orion
    HttpResponse res = API.request("https://images-api.nasa.gov/search?q=orion&media_type=image");
    
    // Assert 200 is received from response
    assertEquals(200, res.getResponseCode());
    
    System.out.println(res.getBody());
  }

}
