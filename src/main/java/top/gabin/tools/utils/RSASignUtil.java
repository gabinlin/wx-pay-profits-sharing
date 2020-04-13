//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package top.gabin.tools.utils;

import org.apache.commons.codec.binary.Base64;

import java.io.UnsupportedEncodingException;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

public class RSASignUtil {
    public static final String KEY_ALGORITHM = "RSA";
    public static final String CIPHER_ALGORITHM = "RSA/ECB/PKCS1Padding";
    public static final String PUBLIC_KEY = "publicKey";
    public static final String PRIVATE_KEY = "privateKey";
    public static final int KEY_SIZE = 2048;
    public static final String SIGNATURE_ALGORITHM = "SHA256withRSA";
    public static final String ENCODE_ALGORITHM = "SHA-256";
    public static final String PLAIN_TEXT = "test string test stringtest stringtest stringtest stringtest stringtest stringtest string";

    public RSASignUtil() {
    }

    public static Map<String, String> generateKeyBytes() {
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(2048);
            KeyPair keyPair = keyPairGenerator.generateKeyPair();
            RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
            RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
            Map<String, String> keyMap = new HashMap();
            keyMap.put("publicKey", Base64.encodeBase64String(publicKey.getEncoded()));
            keyMap.put("privateKey", Base64.encodeBase64String(privateKey.getEncoded()));
            return keyMap;
        } catch (NoSuchAlgorithmException var5) {
            var5.printStackTrace();
            System.out.println("签名验证失败");
            return null;
        }
    }

    public static PublicKey restorePublicKey(String publicKey) {
        PublicKey pubTypeKey = null;

        try {
            byte[] prikeyByte = Base64.decodeBase64(publicKey.getBytes("UTF-8"));
            X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(prikeyByte);
            KeyFactory factory = KeyFactory.getInstance("RSA");
            pubTypeKey = factory.generatePublic(x509EncodedKeySpec);
            return pubTypeKey;
        } catch (NoSuchAlgorithmException var5) {
            var5.printStackTrace();
            System.out.println("签名算法失败，不支持的编码");
        } catch (InvalidKeySpecException var6) {
            var6.printStackTrace();
            System.out.println("签名算法失败，不支持的签名算法");
        } catch (UnsupportedEncodingException var7) {
            var7.printStackTrace();
            System.out.println("签名算法失败，不支持的密钥");
        }

        return null;
    }

    public static PrivateKey restorePrivateKey(String privateKey) {
        PrivateKey priTypeKey = null;

        try {
            byte[] prikeyByte = Base64.decodeBase64(privateKey.getBytes("UTF-8"));
            PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(prikeyByte);
            KeyFactory factory = KeyFactory.getInstance("RSA");
            priTypeKey = factory.generatePrivate(pkcs8EncodedKeySpec);
            return priTypeKey;
        } catch (NoSuchAlgorithmException var5) {
            var5.printStackTrace();
            System.out.println("签名算法失败，不支持的编码");
        } catch (InvalidKeySpecException var6) {
            var6.printStackTrace();
            System.out.println("签名算法失败，不支持的签名算法");
        } catch (UnsupportedEncodingException var7) {
            var7.printStackTrace();
            System.out.println("签名算法失败，不支持的密钥");
        }

        return null;
    }

    public static String sign(String privateKey, String plain_text) {
        return Base64.encodeBase64String(sign(restorePrivateKey(privateKey), plain_text));
    }

    public static byte[] sign(PrivateKey privateKey, String plain_text) {
        byte[] signed = null;

        try {
            Signature Sign = Signature.getInstance("SHA256withRSA");
            Sign.initSign(privateKey);
            Sign.update(plain_text.getBytes());
            signed = Sign.sign();
        } catch (InvalidKeyException | SignatureException | NoSuchAlgorithmException var4) {
            var4.printStackTrace();
        }

        return signed;
    }

    public static boolean verifySign(String publicKey, String srcMsg, String signed) {
        PublicKey publicTypeKey = restorePublicKey(publicKey);
        return verifySign(publicTypeKey, srcMsg, signed);
    }

    private static boolean verifySign(PublicKey publicKey, String plain_text, String signed) {
        boolean SignedSuccess = false;

        try {
            Signature verifySign = Signature.getInstance("SHA256withRSA");
            verifySign.initVerify(publicKey);
            verifySign.update(plain_text.getBytes("UTF-8"));
            SignedSuccess = verifySign.verify(Base64.decodeBase64(signed.getBytes("UTF-8")));
        } catch (InvalidKeyException | SignatureException | NoSuchAlgorithmException var5) {
            var5.printStackTrace();
        } catch (UnsupportedEncodingException var6) {
            var6.printStackTrace();
        }

        return SignedSuccess;
    }

    public static String bytesToHexString(byte[] src) {
        StringBuilder stringBuilder = new StringBuilder("");
        if (src != null && src.length > 0) {
            for (int i = 0; i < src.length; ++i) {
                int v = src[i] & 255;
                String hv = Integer.toHexString(v);
                if (hv.length() < 2) {
                    stringBuilder.append(0);
                }

                stringBuilder.append(hv);
            }

            return stringBuilder.toString();
        } else {
            return null;
        }
    }

    public static byte[] hexStringToBytes(String hexString) {
        if (hexString != null && !hexString.equals("")) {
            int length = hexString.length() / 2;
            char[] hexChars = hexString.toCharArray();
            byte[] d = new byte[length];

            for (int i = 0; i < length; ++i) {
                int pos = i * 2;
                d[i] = chartobyte(hexChars[pos]);
            }

            return d;
        } else {
            return null;
        }
    }

    private static byte chartobyte(char c) {
        return (byte) "123456789abcdef".indexOf(c);
    }
}
