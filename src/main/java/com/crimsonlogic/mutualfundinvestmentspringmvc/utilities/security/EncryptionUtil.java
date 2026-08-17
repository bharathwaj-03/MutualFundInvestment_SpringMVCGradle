package com.crimsonlogic.mutualfundinvestmentspringmvc.utilities.security;

import javax.crypto.Cipher;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Base64;

public class EncryptionUtil {

    /*
     * For this training project.
     *
     * In production, this secret should come from:
     * environment variable / secret manager / secure configuration.
     */
    private static final String SECRET_KEY =
            "MutualFundInvestment@2026SecureKey";


    private static final String ALGORITHM =
            "AES/GCM/NoPadding";

    private static final int GCM_TAG_LENGTH =
            128;

    private static final int IV_LENGTH =
            12;


    private static SecretKeySpec getSecretKey()
            throws Exception {

        MessageDigest digest =
                MessageDigest.getInstance("SHA-256");

        byte[] key =
                digest.digest(
                        SECRET_KEY.getBytes(
                                StandardCharsets.UTF_8));

        return new SecretKeySpec(
                key,
                "AES");
    }


    public static String encrypt(String value) {

        try {

            if (value == null ||
                    value.trim().isEmpty()) {

                return value;
            }

            byte[] iv =
                    new byte[IV_LENGTH];

            SecureRandom secureRandom =
                    new SecureRandom();

            secureRandom.nextBytes(iv);


            Cipher cipher =
                    Cipher.getInstance(
                            ALGORITHM);


            GCMParameterSpec spec =
                    new GCMParameterSpec(
                            GCM_TAG_LENGTH,
                            iv);


            cipher.init(
                    Cipher.ENCRYPT_MODE,
                    getSecretKey(),
                    spec);


            byte[] encrypted =
                    cipher.doFinal(
                            value.getBytes(
                                    StandardCharsets.UTF_8));


            /*
             * Store IV + encrypted value together.
             *
             * Format:
             *
             * BASE64(IV):BASE64(CIPHERTEXT)
             */

            return Base64.getEncoder()
                    .encodeToString(iv)
                    + ":"
                    + Base64.getEncoder()
                    .encodeToString(encrypted);

        } catch (Exception e) {

            throw new RuntimeException(
                    "Unable to encrypt sensitive data.",
                    e);
        }
    }


    public static String decrypt(String encryptedValue) {

        try {

            if (encryptedValue == null ||
                    encryptedValue.trim().isEmpty()) {

                return encryptedValue;
            }


            String[] parts =
                    encryptedValue.split(":");


            if (parts.length != 2) {

                throw new IllegalArgumentException(
                        "Invalid encrypted value.");
            }


            byte[] iv =
                    Base64.getDecoder()
                            .decode(parts[0]);


            byte[] encrypted =
                    Base64.getDecoder()
                            .decode(parts[1]);


            Cipher cipher =
                    Cipher.getInstance(
                            ALGORITHM);


            GCMParameterSpec spec =
                    new GCMParameterSpec(
                            GCM_TAG_LENGTH,
                            iv);


            cipher.init(
                    Cipher.DECRYPT_MODE,
                    getSecretKey(),
                    spec);


            byte[] decrypted =
                    cipher.doFinal(encrypted);


            return new String(
                    decrypted,
                    StandardCharsets.UTF_8);

        } catch (Exception e) {

            throw new RuntimeException(
                    "Unable to decrypt sensitive data.",
                    e);
        }
    }
}