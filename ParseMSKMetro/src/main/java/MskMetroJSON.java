import com.vaadin.external.jsoup.Jsoup;
import com.vaadin.external.jsoup.nodes.Document;
import com.vaadin.external.jsoup.nodes.Element;
import com.vaadin.external.jsoup.select.Elements;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Formatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

public class MskMetroJSON extends JSONObject {

  JSONObject stations = new JSONObject();

  JSONArray lines = new JSONArray() {
    @Override
    public boolean contains(Object o) {
      for (Object line : this) {
        if (((JSONObject) line).get("number").equals(o)) {
          return true;
        }
      }
      return false;
    }
  };

  JSONArray connections = new JSONArray() {
    @SuppressWarnings("unempty")
    public int getHash(JSONArray connects) {
      int sum = 0;
      for (Object connect : connects) {
        sum += ((String) ((JSONObject) connect).get("line")).chars().reduce(Integer::sum).orElse(0);
        sum += ((String) ((JSONObject) connect).get("station")).chars().reduce(Integer::sum)
            .orElse(0);
      }
      return sum;
    }

    @Override
    public boolean contains(Object o) {
      int hash = getHash((JSONArray) o);
      for (Object connects : this) {
        if (hash == getHash((JSONArray) connects)) {
          return true;
        }
      }
      return false;
    }
  };

  @SuppressWarnings("unused")
  public void parseURL(String url) {
    try {
      Document doc = Jsoup.connect(url).maxBodySize(0).get();
      parseDoc(doc);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @SuppressWarnings("unused")
  public void parseFile(String filePath) {
    try {
      Document doc = Jsoup.parse(new File(filePath), "UTF8");
      parseDoc(doc);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }


  private void parseDoc(Document doc) {
    linePars(doc);
    stationPars(doc);
    connectionPars(doc);

    this.put("stations", stations);
    this.put("lines", this.lines);
    this.put("connections", connections);
  }


  private void linePars(Document doc) {
    try {
      Elements elements = doc.select("span[data-line]");
      for (int i = 0; i < elements.size(); i++) {
        String nameLine = elements.get(i).text();
        String numberLine = String.valueOf(i + 1);
        putLine(numberLine, nameLine);
      }
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  private void stationPars(Document doc) {
    try {
      Elements elements = doc.select("div[data-line]");
      for (int i = 0; i < elements.size(); i++) {
        Element element = elements.get(i);
        Elements cols = element.select("span.name");
        String numberLine = String.valueOf(i + 1);
        for (Element col : cols) {
          String nameStation = col.text();
          putStation(numberLine, nameStation);
        }
      }
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  private void connectionPars(Document doc) {
    Elements elementsLine = doc.select("div[data-line]");
    for (int j = 0; j < elementsLine.size(); j++) {
      Elements station = elementsLine.get(j).select("a[data-metrost]");
      for (Element element : station) {
        int a = element.childNodeSize();
        if (a > 2) {
          Elements url1 = element.select("span[title]");
          String numberLine = String.valueOf(j + 1);
          String name = element.select("span.name").text();
          putConnections(url1, numberLine, name);
        }
      }
    }
  }


  private void putConnections(Elements elements, String line, String name) {
    StringBuilder connectionsJSON = new StringBuilder(
        "[{\"line\":\"" + line + "\", \"station\":\"" + name + "\"}");
    for (Element el : elements) {
      Matcher match = Pattern.compile("(?<=переход на станцию «)[А-Я а-я]+(?=» [А-Я])")
          .matcher(el.attr("title"));
      line = getNumberLineFromUrl(el.attr("class"));
      if (!line.isEmpty() && match.find()) {
        connectionsJSON.append(",{\"line\":\"").append(line).append("\", \"station\":\"")
            .append(match.group()).append("\"}");
      }
    }
    JSONArray connects = (JSONArray) JSONValue.parse(connectionsJSON.append("]").toString());
    if (connects.size() > 1 && !connections.contains(connects)) {
      connections.add(connects);
    }
  }

  private static String getNumberLineFromUrl(String url)
  {
    url = url.substring(url.lastIndexOf("t-icon-metroln_ln-") + 19);
    try {
      url = java.net.URLDecoder.decode(url, StandardCharsets.UTF_8.name()); //декодируем URL из ASCII
      return url;
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }
    return "";
  }

  private void putLine(String number, String name) {
    if (!number.isEmpty() && !name.isEmpty()) {
      if (!lines.contains(number)) {
        lines.add(JSONValue.parse(new Formatter().format("{\"number\":\"%s\", \"name\":\"%s\"}",
            number, name).toString()));
      }
    }
  }


  private void putStation(String line, String name) {
    if (!line.isEmpty() && !name.isEmpty()) {
      if (!stations.containsKey(line)) {
        stations.put(line, new JSONArray());
      }
      ((JSONArray) stations.get(line)).add(name);
    }
  }
}
