/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public final class PasswordHasherUtils {

    private PasswordHasherUtils(){
        
    }
    
    public static String createHashPassword(String password, String salt) throws NoSuchAlgorithmException, InvalidKeySpecException {

        byte[] byteSalt = Base64.getDecoder().decode(salt);
        KeySpec spec = new PBEKeySpec(password.toCharArray(), byteSalt, 65536, 128);
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");

        byte[] hash = factory.generateSecret(spec).getEncoded();

        String stringHash = Base64.getEncoder().encodeToString(hash);
   
        return stringHash;
    }

    public static String createSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);

        String strSalt = Base64.getEncoder().encodeToString(salt);

        return strSalt;
    }

}
