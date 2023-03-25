package org.kamuyashiro.Browser;

import java.sql.*;
import java.util.*;

public class QihooBrowserHistoryReader {

   public static List<String> getHistory() {
      List<String> history = new ArrayList<>();

      // 读取360浏览器的历史记录文件
      String homeFolder = System.getProperty("user.home");
      String qihooHistoryFile = homeFolder + "/AppData/Local/Qihoo/360safe/backup/newnavi.db";
      try (Connection conn = DriverManager.getConnection("jdbc:sqlite:" + qihooHistoryFile)) {
         Statement statement = conn.createStatement();
         ResultSet rs = statement.executeQuery("SELECT * FROM navievents");

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
