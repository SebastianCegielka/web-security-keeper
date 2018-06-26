package com.github.sebastiancegielka.utility.cipher;

import com.google.common.base.Utf8;
import org.springframework.stereotype.Service;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

@Service
public class PasswordSecurity {

    private static final String ALGORITHM = "AES";
    private static final String UNICODE_FORMAT = "UTF-8";

    public String encrypt(String pass, SecretKey key){
        String result = "";

        try {
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] encrypted = cipher.doFinal(pass.getBytes(UNICODE_FORMAT));
            Base64.Encoder encoder = Base64.getEncoder();
            result = encoder.encodeToString(encrypted);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | BadPaddingException | IllegalBlockSizeException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }

    public String decrypt(String pass, SecretKey key){
        String result = "";

        try {
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            Base64.Decoder decoder = Base64.getDecoder();
            byte[] passByte = decoder.decode(pass);
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] decrypted = cipher.doFinal(passByte);
            result = new String(decrypted);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | BadPaddingException | IllegalBlockSizeException e) {
            e.printStackTrace();
        }
        return result;
    }


}
