import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class HttpClient {
  private static final String uri = "http://localhost:1880/req";
  private static final String method = "GET";

  public static void main(String[] args)
    throws MalformedURLException, ProtocolException, IOException {
    URL url = new URL(uri);

    HttpURLConnection urlconn = (HttpURLConnection)url.openConnection();
    urlconn.setRequestMethod(method);
    urlconn.setInstanceFollowRedirects(false);
    urlconn.setRequestProperty("Accept-Language", "ja;q=0.7,en;q=0.3");

    urlconn.connect();

    Map<String, List<String>> headers = urlconn.getHeaderFields();
    Iterator<String> it = headers.keySet().iterator();
    System.out.println("--Response Header--");
    while (it.hasNext()){
      String key= (String)it.next();
      System.out.println("  " + key + ": " + headers.get(key));
    }

    System.out.println(urlconn.getResponseCode() + ": "
      + urlconn.getResponseMessage());

    System.out.println("\n--Body--");

    BufferedReader reader =
    new BufferedReader(new InputStreamReader(urlconn.getInputStream()));

    String line;
    while ((line = reader.readLine()) != null){
      System.out.println(line);
    }

    reader.close();
    urlconn.disconnect();
  }
}
