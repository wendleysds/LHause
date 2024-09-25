package com.lhause.wend.LHouseWeb.Utils;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.springframework.stereotype.Component;

/**
 *
 * @author Wendley S
 */
@Component
public class Criptografar {
    public static String md5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digest = md.digest(input.getBytes(StandardCharsets.UTF_8));
            BigInteger number = new BigInteger(1, digest);
            String hash = number.toString(16);

            while (hash.length() < 32) {
                hash = "0" + hash;
            }

            return hash;
        } catch (NoSuchAlgorithmException ex) {
            System.out.println(ex);
            return null;
        }
    }
}
