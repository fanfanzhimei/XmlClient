package com.zhi.utils;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

/**
 * Created by Administrator on 2016/10/18.
 */
public class IOUtil {
    public static String getBytes(InputStream in) throws Exception {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] bytes = new byte[1024];
        int len = 0;
        while ((len = in.read(bytes)) != -1) {
            bos.write(bytes, 0, len);
        }
        return  new String(bos.toByteArray());
    }
}
