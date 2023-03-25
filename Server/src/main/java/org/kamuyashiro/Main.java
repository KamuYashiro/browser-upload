package org.kamuyashiro;


import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.text.*;
import java.util.*;

/***********************
 *   @Author: Rain
 *   @Date: ${DATE}
 * **********************
 */
public class Main {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(2333)) {
            while (true) {
                Socket socket = serverSocket.accept();
                handleConnection(socket);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void handleConnection(Socket socket) throws IOException {
        try (InputStream inputStream = socket.getInputStream();
             DataInputStream dataInputStream = new DataInputStream(inputStream)) {

            // 获取当前时间戳
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
            String timestamp = dateFormat.format(new Date());

            // 创建目录
            Path directory = Paths.get(timestamp);
            Files.createDirectory(directory);

            // 读取历史记录并分别写入不同的txt文件中
            while (true) {
                String browserName = dataInputStream.readUTF();
                if (browserName.equals("END")) {
                    break;
                }

                int numRecords = dataInputStream.readInt();

                // 创建浏览器名字的子目录
                Path subdirectory = directory.resolve(browserName);
                Files.createDirectory(subdirectory);

                // 写入历史记录
                for (int i = 0; i < numRecords; i++) {
                    String record = dataInputStream.readUTF();
                    String fileName = "history_" + i + ".txt";
                    Path file = subdirectory.resolve(fileName);
                    Files.write(file, Collections.singletonList(record), StandardCharsets.UTF_8);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}