package org.kamuyashiro.Browser;

import java.sql.*;
import java.util.*;

public class FirefoxBrowserHistoryReader {

   public static List<String> getHistory() {
      List<String> history = new ArrayList<>();

      // 读取Firefox浏览器的历史记录文件
      String homeFolder = System.getProperty("user.home");
      String firefoxHistoryFile = homeFolder + "/AppData/Roaming/Mozilla/Firefox/Profiles/*.default/places.sqlite";
      try (Connection conn = DriverManager.getConnection("jdbc:sqlite:" + firefoxHistoryFile)) {
         Statement statement = conn.createStatement();
         ResultSet rs = statement.executeQuery("SELECT * FROM moz_places");

         while (rs.next()) {
            String url = rs.getString("url");
            String title = rs.getString("title");
            history.add(title + " - " + url);
         }
      } catch (SQLException e) {
         e.printStackTrace();
      }

      return history;
   }
}
