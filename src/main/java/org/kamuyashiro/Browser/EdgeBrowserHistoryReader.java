package org.kamuyashiro.Browser;

import java.sql.*;
import java.util.*;

public class EdgeBrowserHistoryReader {

   public static List<String> getHistory() {
      List<String> history = new ArrayList<>();

      // 读取Edge浏览器的历史记录文件
      String homeFolder = System.getProperty("user.home");
      String edgeHistoryFile = homeFolder + "/AppData/Local/Microsoft/Edge/User Data/Default/History";
      try (Connection conn = DriverManager.getConnection("jdbc:sqlite:" + edgeHistoryFile)) {
         Statement statement = conn.createStatement();
         ResultSet rs = statement.executeQuery("SELECT * FROM urls");

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
