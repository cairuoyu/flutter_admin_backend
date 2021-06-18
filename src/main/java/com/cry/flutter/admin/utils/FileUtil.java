package com.cry.flutter.admin.utils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class FileUtil {
    public static void main(String[] args) {
    }

    public static String codeFileName(String filename) {
        return codeFileName(filename, "jpg");
    }

    public static String codeFileName(String filename, String defaultExt) {
        String ext = defaultExt;
        if (filename.lastIndexOf(".") > -1) {
            ext = filename.substring(filename.lastIndexOf(".") + 1);
        }
        filename = "u-" + CryStringUtil.genStringByTime() + "." + ext;
        return filename;
    }

    public static void downLoadFromUrl(String urlStr, String filename, String savePath) throws IOException {
        URL url = new URL(urlStr);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setConnectTimeout(3 * 1000);
        conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
        conn.setRequestProperty("lfwywxqyh_token", "testToken");

        InputStream inputStream = conn.getInputStream();
        byte[] getData = readInputStream(inputStream);
        File saveDir = new File(savePath);
        if (!saveDir.exists()) {
            saveDir.mkdir();
        }
        File file = new File(saveDir + File.separator + filename);
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(getData);
        if (fos != null) {
            fos.close();
        }
        if (inputStream != null) {
            inputStream.close();
        }

        System.out.println("info:" + url + " download success");
    }

    public static byte[] readInputStream(InputStream inputStream) throws IOException {
        byte[] buffer = new byte[1024];
        int len = 0;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        while ((len = inputStream.read(buffer)) != -1) {
            bos.write(buffer, 0, len);
        }
        bos.close();
        return bos.toByteArray();
    }


}
