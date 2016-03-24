/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.shop.alipay.utils;


import com.shop.alipay.codec.Base64;
import com.shop.alipay.enums.Constants;

import java.security.*;
import java.security.spec.EncodedKeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * 签名验证工具类
 * 
 * @author junhua.pan
 * @version $Id: SignatureUtil.java, v 0.1 2013-1-25 上午11:22:42 junhua.pan Exp $
 */
public final class SignatureUtils {

    /**
     * 通过签名算法获取公钥对象
     * 
     * @param publicKeyData 公钥字符串
     * @param algorithm     签名算法
     * @return 公钥对象
     * @throws Exception 异常
     */
    private static PublicKey getPublicKey(String publicKeyData, String algorithm) throws Exception {

        KeyFactory keyFactory = KeyFactory.getInstance(algorithm);

        byte[] buffer = publicKeyData.getBytes(Constants.GBK.getValue());
        buffer = Base64.decodeBase64(buffer);

        EncodedKeySpec keySpec = new X509EncodedKeySpec(buffer);

        return keyFactory.generatePublic(keySpec);
    }

    /**
     * 通过签名算法获取私钥对象
     * 
     * @param privateKeyData    私钥字符串
     * @param algorithm         签名算法
     * @return 私钥对象
     * @throws Exception 异常
     */
    private static PrivateKey getPrivateKey(String privateKeyData, String algorithm)
                                                                                    throws Exception {

        KeyFactory keyFactory = KeyFactory.getInstance(algorithm);

        byte[] buffer = privateKeyData.getBytes(Constants.GBK.getValue());
        buffer = Base64.decodeBase64(buffer);

        EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(buffer);

        return keyFactory.generatePrivate(keySpec);
    }

    /**
     * 采用提供的算法签名数据
     * 
     * @param text              待签名内容
     * @param privateKeyData    私钥字符串信息
     * @param algorithm         签名算法
     * @return 签名后的字节数组
     */
    public static byte[] sign(String text, String privateKeyData) {

        if (StringUtil.isBlank(text)) {
            return null;
        }
        try {
            Signature sign = Signature.getInstance(Constants.SIGNATURE_ALGORITHM.getValue());

            sign.initSign(getPrivateKey(privateKeyData, Constants.KEY_ALGORITHM.getValue()));

            sign.update(text.getBytes(Constants.GBK.getValue()));

            return sign.sign();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (SignatureException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 采用提供的签名算法验证签名
     * 
     * @param text              待验证内容
     * @param signature         已签名内容字节数组
     * @param publicKeyData     公钥内容
     * @param algorithm         签名算法
     * @return 验证结果 true/false
     */
    public static boolean verify(String text, byte[] signature, String publicKeyData,
                                 String algorithm) {

        try {
            Signature sign = Signature.getInstance(StringUtil.equals(algorithm,
                Constants.KEY_ALGORITHM.getValue()) ? Constants.SIGNATURE_ALGORITHM.getValue()
                : algorithm);

            sign.initVerify(getPublicKey(publicKeyData, algorithm));

            sign.update(text.getBytes(Constants.GBK.getValue()));

            return sign.verify(signature);

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (SignatureException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void main(String[] args) {

        //String pid = "2088102122069225";
        String algorithm = "RSA";
        String privateKeyData = "MIIEuwIBADANBgkqhkiG9w0BAQEFAASCBKUwggShAgEAAoIBAQCEgueapmGN1Ug4aoR/QV/RIIAVM9zbkZsUmBQpvUZqN8Pj5k4Z5x9bKdk23o7OozTlMfCFXXIbSyXynKhDNBHIsymNNicsRYBFTG7CgFoJQFDAJlJboejO/5x7nhVhP3FfqlXVXfUm7Ca5gAowk5Ep25kES1MPpA3B/USvgBdpHDgujw4bYUSMC1yKW6lTabED3UoTqKgNuohKUgswypgZjW2ukkJKRUr+a0wdUBGoaVd2WULAsMdqfJhVrqK5iKEvvqil0w3134eZG9mzKiMIZZaJDvLhN4B23nYzStlUhp51Rntmh4Z8AvaxXx2Bh1wHdbvHrkUuoDQJLBNy2JQnAgMBAAECgf8irh/vWZwugqNkIHyM0Y9CLD9ZTTmAPZtYyHR+eAr1waKDAsNuBA8wzMMMmTLG4e/2DBvmy44RlplX5agj7ny5CirVylZ2P7pSaK6UOQNXaH25zE3BrZ5AjqVltPnjebcyUmIkdXKzvRplch04mWZkjhR+Czix3z26h45yYBPAbkGwHhCYzSt2zzlISx5s2HRD6/lEOxGI5iRWlJTABqBzg22PQVihvRvrCfEumEhBRj7Sl1WagLi4FSycrPYdfnP1y2f+QXc0OihdP2JPbyyvvUyyFp+QPs+owE7cNeXosaIaSZARN/8Q3HqQx3yrVgbxPatFHDtkrg6DLcmtVzECgYEAw7SJVYz86E/xWsokRMKtivMwQpnOw4xwTTJn2imEzZCwoCWZexyML194pEcw29IomD/XNNVH8iv8UtebrAGaj/TC0DJJHsDy/lebyseKtD9ofx7tm/ntGL0DmTN9Z+yWKstUL3aNMoag4L7FIy3Xh82i1SUfOOmP+qXTRBWBJvkCgYEArVY0hCe9IegfBTiCRxLIEomCFgpDmPuuzzO41BBqj2XwXnNBvCXBvWjj5/KjKvKxdj7GOwbHHP5LL7vILQKbqFTIxihpnTYZAUPFy9VkREs6R2x4AFprXKDH4k4PUmHbRpclPx6p2Jo5y7hNW/TXMq8/XvOkDiqnVzhqxHPGvB8CgYEAhknZetS1rgs9CBlw7aXQQ2pAEVNNJ620WaRe0rpuoEfKivmB6ZJbNx6Bv+gBxLTemiokV+KJ7cj06Tt9XHf1QRaonwgCWJH5q0Eea/SkXq6r256zWLYUVUdNstA8GxWirVa8O51TofnzZoRA7bTFEjCurSc912WdIFatMyi2aLECgYAffr5kFORkuMyKIk5po5v+4kVkw9Csh26n73nx7lmuXJNkVgTnruSZCen/RzMmpppgJd/W8dBUEFCRB8nDg+nMmDsy5jzqd93rQOk7tDZXwmOAftD/H27Kkdw/3nciPO2e0e/OnD+4P62ouoAz6TUA3J7jHdn9SPrG+lVMBM1jeQKBgDgGggj/DN2poADxEbggt/RHkJTm6w4YgGzMvZBQQ8gkBGf3cn4wyXfvQrNz+lOkyVSRaZ9p+CfC9lhk5U4YXoC+IhWl8S8R8zc/+fkXokPg+f+BqvP4xDSC7AuXExm8QvXfMVkzsw8X9BZ4Jg8jAgvKtHtkKKc4sqnYAVh6h21M";
        String publicKeyData = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAhILnmqZhjdVIOGqEf0Ff0SCAFTPc25GbFJgUKb1GajfD4+ZOGecfWynZNt6OzqM05THwhV1yG0sl8pyoQzQRyLMpjTYnLEWARUxuwoBaCUBQwCZSW6Hozv+ce54VYT9xX6pV1V31JuwmuYAKMJORKduZBEtTD6QNwf1Er4AXaRw4Lo8OG2FEjAtcilupU2mxA91KE6ioDbqISlILMMqYGY1trpJCSkVK/mtMHVARqGlXdllCwLDHanyYVa6iuYihL76opdMN9d+HmRvZsyojCGWWiQ7y4TeAdt52M0rZVIaedUZ7ZoeGfAL2sV8dgYdcB3W7x65FLqA0CSwTctiUJwIDAQAB";
        try {
            System.out.println(privateKeyData.length());
            System.out.println(publicKeyData.length());
            String text = "_input_charset=UTF-8&partner=哈阿红2088102122069225&service=alipay.mobile.alipass.create&timestamp=2013-05-30 17:39:08&user_id=13053017390787346139";
            byte[] signature = sign(text, privateKeyData);
            System.out.println("签名结果:\n" + Base64.encodeBase64String(signature));

            System.out.println("验签结果：" + verify(text, signature, publicKeyData, algorithm));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
