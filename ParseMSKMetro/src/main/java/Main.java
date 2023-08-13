import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

public class Main {

  public static final String url = "https://www.moscowmap.ru/metro.html#lines";
  public static final String fileName = "src/main/resources/map.json";
  public static MskMetroJSON map = new MskMetroJSON();

  public static void main(String[] args) {

    map.parseURL(url);
    try (FileWriter writer = new FileWriter(fileName)) {
      JsonObject json = new Gson().fromJson(map.toString(), JsonObject.class);
      writer.write(new GsonBuilder().setPrettyPrinting().create().toJson(json));
      writer.flush();
    } catch (IOException e) {
      e.printStackTrace();
    }

    try {
      JSONObject fileJSON = (JSONObject) JSONValue.parse(Files.readString(Paths.get(fileName)));
      int countAllStation = 0;
      for (String numberLine : (Iterable<String>) ((JSONObject) fileJSON.get("stations"))
          .keySet()) {
        int countStations = ((JSONArray) ((JSONObject) fileJSON.get("stations")).get(numberLine))
            .size();
        countAllStation += countStations;
        String nameLine = getNameLine(numberLine, fileJSON);
        System.out.println(
            nameLine + " № " + numberLine + ", количество станции на ветке: " + countStations);
      }
      System.out.println("Всего станции: " + countAllStation);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private static String getNameLine(String numberLine, JSONObject json) {
    for (Object line : (JSONArray) json.get("lines")) {
      if (((JSONObject) line).get("number").equals(numberLine)) {
        return ((JSONObject) line).get("name").toString();
      }
    }
    return "";
  }
}

