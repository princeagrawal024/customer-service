package com.demo.project.converter;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.spec.AlgorithmParameterSpec;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
public class CipherMaker {
    @Value("${cipher.name}")
    private String CIPHER_INSTANCE_NAME;
    @Value("${cipher.algo}")
    private String SECRET_KEY_ALGORITHM;
    public Cipher configureAndGetInstance(int encryptionMode, String key)
            throws  NoSuchPaddingException,
            NoSuchAlgorithmException,
            InvalidKeyException, InvalidAlgorithmParameterException {

        Cipher cipher = Cipher.getInstance(CIPHER_INSTANCE_NAME);
        Key secretKey = new SecretKeySpec(key.getBytes(), SECRET_KEY_ALGORITHM);

        byte[] ivBytes = new byte[cipher.getBlockSize()];
        AlgorithmParameterSpec algorithmParameters = new IvParameterSpec(ivBytes);

        cipher.init(encryptionMode, secretKey, algorithmParameters);
        return cipher;
    }
}