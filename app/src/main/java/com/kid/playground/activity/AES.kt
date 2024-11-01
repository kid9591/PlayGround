package com.kid.playground.activity

import android.util.Base64
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.PBEKeySpec
import javax.crypto.spec.SecretKeySpec

object AES {

    //    @Throws(
//        NoSuchAlgorithmException::class
//    )

    fun genKey(salt: ByteArray,
               passphrase: CharArray,
               iterationCount: Int,
               keySize: Int): ByteArray  {
        // Tạo khóa mã hóa từ passphrase và salt
        val spec = PBEKeySpec(passphrase, salt, iterationCount, keySize)
        val factory = SecretKeyFactory.getInstance("PBKDF2withHmacSHA1")
        val key = factory.generateSecret(spec)
            .encoded

        logDebug { "chi.trinh keysize: ${key.size}" }

        return key
    }

    fun encrypt(
        input: String,
        iv: ByteArray,
        key: ByteArray
    ): String {
        val cipher = Cipher.getInstance("AES/CBC/PKCS5Padding")
        cipher.init(Cipher.ENCRYPT_MODE, SecretKeySpec(key, "AES"), IvParameterSpec(iv))

        return Base64.encodeToString(cipher.doFinal(input.toByteArray()), Base64.NO_WRAP)
    }

    fun genKey(): SecretKey? {
        val secretKey: SecretKey?

        val keyGen = KeyGenerator.getInstance("AES")
        keyGen.init(128)
        secretKey = keyGen.generateKey()
        return secretKey
    }

    //    @Throws(
//        Exception::class
//    )
    fun encrypt(secretKey: SecretKey, input: String): String {
        val cipher = Cipher.getInstance("AES/ECB/PKCS5Padding")
        cipher.init(Cipher.ENCRYPT_MODE, secretKey)
        return Base64.encodeToString(cipher.doFinal(input.toByteArray()), Base64.NO_WRAP)
    }

    //    @Throws(
//        Exception::class
//    )
    fun decrypt(base64EncodedKey: String, input: String): String {
        val cipher =
            Cipher.getInstance("AES/ECB/PKCS5PADDING")
        val decodedKey =
            Base64.decode(base64EncodedKey, Base64.NO_WRAP)
        val key: SecretKey =
            SecretKeySpec(decodedKey, 0, decodedKey.size, "AES")
        cipher.init(Cipher.DECRYPT_MODE, key)
        return String(
            cipher.doFinal(
                Base64.decode(
                    input,
                    Base64.NO_WRAP
                )
            )
        )
    }
}