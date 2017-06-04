
package com.common.util.md5;

import java.io.FileInputStream;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Md5Util {

    private static final Logger LOGGER = LoggerFactory.getLogger(Md5Util.class);

    /**
     * 获取md5串
     * @param sourceStr
     * @return
     */
    public static String getMd5Str(String sourceStr) {
        String md5Str = sourceStr;
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            md5Str = HexStringUtil.byteArrayToHexString(messageDigest.digest(sourceStr.getBytes()));
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("原始字符：" + sourceStr + "加密后字符：" + md5Str);
            }
        } catch (NoSuchAlgorithmException e) {
            if (LOGGER.isErrorEnabled()) {
                LOGGER.error("字符串MD5失败。" + e);
            }
        }
        return md5Str;
    }

    /**
     * 获取文件的md5值
     * @param fileName
     * @return
     */
    public static String getMd5Value(String fileName) {

        int bufferSize = 128 * 1024;
        FileInputStream in = null;
        DigestInputStream digestIn = null;

        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            in = new FileInputStream(fileName);
            digestIn = new DigestInputStream(in, messageDigest);
            byte[] buffer = new byte[bufferSize];
            while (digestIn.read(buffer) != -1)
                messageDigest = digestIn.getMessageDigest();
            byte[] resultMd5 = messageDigest.digest();
            return HexStringUtil.byteArrayToHexString(resultMd5);
        } catch (Exception e) {
            LOGGER.error("Compute md5 with inputstream failed");
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
                if (digestIn != null) {
                    digestIn.close();
                }
            } catch (Exception e) {
                LOGGER.error("Close input stream failed");
            }
        }

        return "";

    }

}
