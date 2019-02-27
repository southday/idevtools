package cn.idevtools.util;

import java.security.MessageDigest;

/**
 * 密码加密工具
 * @author southday
 * @date 2019/2/27
 */
public class EncryptUtil {
    private static final String[] saltList = {
            "0", "s", "L", "x", "e", "9", "a", "2", "T", "A", "p",
            "m", "W", "5", "!", "i", "@", "4", "Y", "R", "r", "#",
            "*", "g", "z", "G", "u", "$", "M", "v", "k", "d", "F"
    };

    /**
     * 根据[密码]和saltList来制造盐
     * @param password
     * @return
     */
    public static String salt(String password) {
        if (password == null)
            return "";
        int delt = Math.abs(saltList.length - password.length());
        int halfLen = password.length() / 2;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < halfLen; i ++)
            sb.append(saltList[(i * delt) % saltList.length]);
        return sb.toString();
    }

    /**
     * 利用Java自带的MessageDigest类进行md5加密，并将结果转成32位16进制小写字母字符串
     * @param plaintext
     * @return
     */
    public static String md5(String plaintext) {
        MessageDigest md5;
        String ciphertext;
        try {
            md5 = MessageDigest.getInstance("MD5");
            byte[] md5Bytes = md5.digest(plaintext.getBytes());
            StringBuilder hexValue = new StringBuilder();
            for (int i = 0; i < md5Bytes.length; i++) {
                int val = ((int) md5Bytes[i]) & 0xff;
                if (val < 16)
                    hexValue.append("0");
                hexValue.append(Integer.toHexString(val));
            }
            ciphertext = hexValue.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return ciphertext;
    }

    /**
     * 返回加盐后加密过的密码
     * @param password
     * @return
     */
    public static String md5salt(String password) {
        return md5(password + salt(password));
    }
}
