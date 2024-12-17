package di.fa.kaauth.core.security;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;

public class RSAUtils {

    private RSAUtils() {}

    public static String decrypt(String data, String key) throws BadPaddingException, IllegalBlockSizeException,
            InvalidKeyException, InvalidKeySpecException, NoSuchAlgorithmException, NoSuchPaddingException {

        return decrypt(Base64.getDecoder().decode(data.getBytes()), getPrivateKey(key));
    }

    private static String decrypt(byte[] data, PrivateKey privateKey) throws NoSuchPaddingException, NoSuchAlgorithmException,
            InvalidKeyException, BadPaddingException, IllegalBlockSizeException {

        var cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);

        return new String(cipher.doFinal(data));
    }

    private static PrivateKey getPrivateKey(String key) throws NoSuchAlgorithmException, InvalidKeySpecException {
        var keySpec = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(key.getBytes()));
        var keyFactory = KeyFactory.getInstance("RSA");

        return keyFactory.generatePrivate(keySpec);
    }
}
