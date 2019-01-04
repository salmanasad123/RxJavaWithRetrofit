package com.example.teamas.loginfunctionality.Login.utils;

import android.util.Base64;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SecurityUtility {
    public static String getSHA512PasswordHash(String passwordToHash) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA512");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        byte[] textBytes = new byte[0];
        try {
            textBytes = passwordToHash.getBytes("iso-8859-1");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        md.update(textBytes, 0, textBytes.length);
        byte[] sha512hash = md.digest();
        return convertToHex(sha512hash);
    }

    private static String convertToHex(byte[] data) {
        StringBuilder buf = new StringBuilder();
        for (byte b : data) {
            int halfbyte = (b >>> 4) & 0x0F;
            int two_halfs = 0;
            do {
                buf.append((0 <= halfbyte) && (halfbyte <= 9) ? (char) ('0' + halfbyte) : (char) ('a' + (halfbyte - 10)));
                halfbyte = b & 0x0F;
            } while (two_halfs++ < 1);
        }
        return buf.toString().toLowerCase();
    }

    public static String getEncodedString(String password) {
        try {
            return Base64.encodeToString(password.getBytes("UTF-8"), Base64.NO_WRAP);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }

}
