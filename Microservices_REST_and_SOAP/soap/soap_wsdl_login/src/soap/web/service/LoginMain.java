package soap.web.service;

import javax.xml.ws.Endpoint;

public class LoginMain {
  public static void main(String[] args)
  {
    Endpoint.publish("http://127.0.0.1:9090/",
    new Login("fabio", "admin"));
  }
}