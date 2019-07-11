package com.damonleexh.util;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.charset.Charset;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class FileUtil {

    public static synchronized boolean write(Context context, String filename, String content) {
        FileOutputStream fos = null;
        try {
            fos = context.openFileOutput(filename, Context.MODE_PRIVATE);
            fos.write(content.getBytes());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
                fos = null;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public static String read(Context context, String filename) {
        FileInputStream fis = null;
        try {
            fis = context.openFileInput(filename);
            int length = fis.available();//获取文件长度
            byte[] buffer = new byte[length];//创建byte数组用于读入数据
            fis.read(buffer);
            return new String(buffer, Charset.defaultCharset());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fis.close();
                fis = null;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return "";
    }
}
