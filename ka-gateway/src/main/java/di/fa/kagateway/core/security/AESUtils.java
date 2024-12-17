package di.fa.kagateway.core.security;

import org.bouncycastle.util.encoders.Base64;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class AESUtils {

    private static final String AES_CBC_MODE = "AES/CBC/PKCS5Padding";

    private AESUtils() {}

    public static String encrypt(String value, String secretKey, String iv) throws BadPaddingException, NoSuchPaddingException, NoSuchAlgorithmException,
            IllegalBlockSizeException, InvalidAlgorithmParameterException, InvalidKeyException, UnsupportedEncodingException {

        var cipher = Cipher.getInstance(AES_CBC_MODE);
        cipher.init(Cipher.ENCRYPT_MODE, generateKey(secretKey), generateIv(iv));

        return Base64.toBase64String(cipher.doFinal(value.getBytes()));
    }

    public static String decrypt(String encrypt, String secretKey, String iv) throws BadPaddingException, NoSuchPaddingException, NoSuchAlgorithmException,
            IllegalBlockSizeException, InvalidAlgorithmParameterException, InvalidKeyException, UnsupportedEncodingException {

        var cipher = Cipher.getInstance(AES_CBC_MODE);
        cipher.init(Cipher.DECRYPT_MODE, generateKey(secretKey), generateIv(iv));

        return new String(cipher.doFinal(Base64.decode(encrypt)));
    }

    private static SecretKey generateKey(String secretKey) throws UnsupportedEncodingException {
        return new SecretKeySpec(secretKey.getBytes(StandardCharsets.UTF_8.name()), "AES");
    }

    private static IvParameterSpec generateIv(String iv) throws UnsupportedEncodingException {
        return new IvParameterSpec(iv.getBytes(StandardCharsets.UTF_8.name()));
    }
}