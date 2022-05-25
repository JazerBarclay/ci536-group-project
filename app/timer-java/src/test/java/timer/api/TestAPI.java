package timer.api;

import static org.junit.Assert.*;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.HashMap;

import org.junit.Test;

public class TestAPI {

  @Test
  /**
   * Test getRequest()
   */
  public void testGetRequest() {
    // Send request for images matching the term orion
    HttpResponse res = API.getRequest("https://dev.api.quark.rocks/");

    // Assert 200 is received from response
    assertEquals(200, res.getResponseCode());

    System.out.println(res.getBody());
  }

  @Test
  /**
   * Test postRequest()
   */
  public void testPostRequest() {
    // Send request for images matching the term orion
    HttpResponse res = null;
    try {
      res = API.postRequest("https://dev.api.quark.rocks/login", new HashMap<String, String>() {
        private static final long serialVersionUID = 1L;
        {
          put("email", "user@quark.rocks");
          put("password", "pass");
        }
      });
    } catch (IOException e) {
      e.printStackTrace();
    }

    assertNotEquals(null, res);

    // Assert 200 is received from response
    assertEquals(200, res.getResponseCode());

    System.out.println(res.getBody());
  }

  @Test
  public void testTimestamp() {
    Timestamp ts = new Timestamp(System.currentTimeMillis());
    System.out.println((ts.toString()) + "000");
  }

}
