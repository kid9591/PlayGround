package com.kid.playground.activity

import android.util.Base64
import java.nio.charset.Charset
import java.security.KeyFactory
import java.security.PrivateKey
import java.security.PublicKey
import java.security.SecureRandom
import java.security.spec.PKCS8EncodedKeySpec
import java.security.spec.X509EncodedKeySpec
import javax.crypto.Cipher
import javax.crypto.SecretKey
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.PBEKeySpec

class EReceiptEncryption(private val debugMode: Boolean) {

    val DEBUG_PUBLIC_KEY =
        "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDWwnLgn81eHlDU+1/JZ7mjdSjio9rWTCjq1ghVeObQMOi/X6v0fSNUhKBXR5kIgLSDp3bMCIf+S0pypTtuWcD9OlRU2/bWBfZZ0jrRih/Qt4iVzgPS9IO0781HdgWKxDF0IAiXr2QfcIgHT4BZP2qXaDh1GmW6HRapalk7voZQwwIDAQAB"
    val DEBUG_PRIVATE_KEY =
        "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBANbCcuCfzV4eUNT7X8lnuaN1KOKj2tZMKOrWCFV45tAw6L9fq/R9I1SEoFdHmQiAtIOndswIh/5LSnKlO25ZwP06VFTb9tYF9lnSOtGKH9C3iJXOA9L0g7TvzUd2BYrEMXQgCJevZB9wiAdPgFk/apdoOHUaZbodFqlqWTu+hlDDAgMBAAECgYBAoeGn14AbiL0j9AkmWJimaG6nEtAb+WzBDCwS6SFx255YZgcevACDgaytx8b3J6DsFFys2A5xiiA8M50Yv1tUbSQp1zTjKXfLI/wgnZlF1cKq18/ejR25gWXDXisOr+8cHMffJZpWEymWHsetqSXdu2/3fer7GGDNfS381OnZpQJBAPqt7XAggyj2UtoUZ9gbycD49ElrA0vTWe9v2guFrQ7SFYf9u1lmI2b1pVh6ZDfNULxD8xdB1a24pTUrzMeB+vUCQQDbUVmWO0k1XV4SHKhTIYfDoTQo8kLBGysPbjt6f2M/YQsSmDHblviLwo8GWR4AaRFcd2gMY27ZgF/vBHUMDDnXAkAiPsp2C+r/GI9+/VSmYD1yoE/3C6h6nlfl7tjRxCQ9JxQL7OzALMTfVuWMrcOtH95Mqde2sKa5QcVH+0DRbH9VAkBZMma7IPWPTJ/bwef7l2F+AOwT0yOvIfuLVQu/sFuPBUrZjyiH5IjFXqWIsWnwRMsYzj90+mO09e8OaMOp2LcVAkBLy5Gyl8mzTHVk+1YzWMOy1MU0zqv8D3hbXHilsl/sdskVrnmNx7mi9v5o6+k6URFmCNcNnJuZe2F1XM/HZw6n"

    val RELEASE_PUBLIC_KEY =
        "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCVk68sHqo91Lo0dy3Hguwk1I/W/XHkzr368LNDM40RBcuxf7g7TXtJNO+FofbaTqRCj3Gxkt9FaO6a0K0snG2zIApWIkwut6vilxiTfe5KyZJs3nCPoRPjkWf5aCBRcEG4XcTQ1/9F6ZMa7mSpIU4XInmQWxQPYE4DcoR+7WueWwIDAQAB"

    companion object {
        const val AES_MODE = "AES/CBC/PKCS5Padding"
        const val AES_KEYGEN_ALGO = "PBKDF2withHmacSHA1"
        const val RSA_MODE = "RSA/ECB/PKCS1Padding"
        const val RSA_KEYGEN_ALGO = "RSA"
    }

    private fun genAesKey(
        salt: ByteArray,
        passphrase: CharArray,
        iterationCount: Int,
        keySize: Int
    ): SecretKey {
        val spec = PBEKeySpec(passphrase, salt, iterationCount, keySize)
        val factory = SecretKeyFactory.getInstance(AES_KEYGEN_ALGO)

        return factory.generateSecret(spec)
    }

    private fun encryptAes(
        input: String,
        iv: ByteArray,
        key: SecretKey
    ): String {
        val cipher = Cipher.getInstance(AES_MODE)
        cipher.init(Cipher.ENCRYPT_MODE, key, IvParameterSpec(iv))

        return Base64.encodeToString(cipher.doFinal(input.toByteArray()), Base64.NO_WRAP)
    }

    private fun decryptAes(
        cipherText: String,
        iv: ByteArray,
        key: SecretKey
    ): String {
        val cipher =
            Cipher.getInstance(AES_MODE)
        cipher.init(Cipher.DECRYPT_MODE, key, IvParameterSpec(iv))
        return String(cipher.doFinal(Base64.decode(cipherText, Base64.NO_WRAP)))
    }

    private fun genRsaPublicKey(b64EncodedPublicKey: String): PublicKey {
        val publicKey: PublicKey?
        val keySpec =
            X509EncodedKeySpec(
                Base64.decode(
                    b64EncodedPublicKey.toByteArray(),
                    Base64.NO_WRAP
                )
            )
        val keyFactory = KeyFactory.getInstance(RSA_KEYGEN_ALGO)
        publicKey = keyFactory.generatePublic(keySpec)
        return publicKey
    }

    private fun genRsaPrivateKey(b64EncodedPrivateKey: String): PrivateKey {
        val privateKey: PrivateKey?
        val keySpec =
            PKCS8EncodedKeySpec(
                Base64.decode(
                    b64EncodedPrivateKey.toByteArray(),
                    Base64.NO_WRAP
                )
            )
        val keyFactory = KeyFactory.getInstance(RSA_KEYGEN_ALGO)
        privateKey = keyFactory.generatePrivate(keySpec)
        return privateKey
    }

    private fun encryptRsa(key: PublicKey, input: String): String {
        val cipher = Cipher.getInstance(RSA_MODE)
        cipher.init(
            Cipher.ENCRYPT_MODE,
            key
        )
        return Base64.encodeToString(
            cipher.doFinal(input.toByteArray()),
            Base64.NO_WRAP
        )
    }

    private fun decryptRsa(key: PrivateKey, cipherText: String): String {
        val cipher = Cipher.getInstance(RSA_MODE)
        cipher.init(
            Cipher.DECRYPT_MODE,
            key
        )
        return String(
            cipher.doFinal(
                Base64.decode(
                    cipherText,
                    Base64.NO_WRAP
                )
            )
        )
    }

    fun decryptEReceipt(cipherText: String): String {

        if (!debugMode) throw java.lang.IllegalStateException("No private key for release mode!")

        val parts = cipherText.split(":::")
        val cipherKeySpecs = parts[0]
        val cipherAesText = parts[1]

        val privateKey = genRsaPrivateKey(DEBUG_PRIVATE_KEY)
        val keySpecs = decryptRsa(privateKey, cipherKeySpecs).split(":::")

        val iterationCount = keySpecs[0].toInt()
        val keySize = keySpecs[1].toInt()
        val iv = keySpecs[2].hexToByteArray()
        val salt = keySpecs[3].hexToByteArray()
        val passphrase = keySpecs[4].hexToByteArray().toString(Charsets.UTF_8).toCharArray()

        logDebug { "chi.trinh keySpec: ${"${keySpecs[0]}:::${keySpecs[1]}:::${keySpecs[2]}:::${keySpecs[3]}:::${keySpecs[4]}"}" }

        val aesKey = genAesKey(salt, passphrase, iterationCount, keySize)

        logDebug { "chi.trinh calculated key: ${aesKey.encoded.toHex()}" }

        return decryptAes(cipherAesText, iv, aesKey)
    }

    fun encryptEReceipt(input: String): String {

        val iterationCount = 1000
        val keySize = 128

        val salt = ByteArray(16)
        SecureRandom().nextBytes(salt)

        val chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789"
        val passphrase = CharArray(16) { chars[SecureRandom().nextInt(chars.length)] }

        val iv = ByteArray(16)
        SecureRandom().nextBytes(iv)

        val aesKey = genAesKey(salt, passphrase, iterationCount, keySize)
        logDebug { "chi.trinh calculated key: ${aesKey.encoded.toHex()}" }
        val cipherText = encryptAes(input, iv, aesKey)

        val base64publicKey = if (debugMode) DEBUG_PUBLIC_KEY else RELEASE_PUBLIC_KEY
        val rsaPublicKey = genRsaPublicKey(base64publicKey)
        val cipherKeySpec = encryptRsa(
            rsaPublicKey,
            "$iterationCount:::$keySize:::${iv.toHex()}:::${salt.toHex()}:::${passphrase.toHex()}",
        )
        logDebug { "chi.trinh keySpec: ${"$iterationCount:::$keySize:::${iv.toHex()}:::${salt.toHex()}:::${passphrase.toHex()}"}" }

        return "$cipherKeySpec:::$cipherText"
    }

    private fun CharArray.toHex(charset: Charset = Charsets.UTF_8): String {
        return joinToString("").toByteArray(charset).toHex()
    }

    private fun String.hexToByteArray(): ByteArray {
        check(length % 2 == 0) { "Must have an even length" }

        return chunked(2)
            .map { it.toInt(16).toByte() }
            .toByteArray()
    }

    private fun ByteArray.toHex(): String = joinToString(separator = "") { eachByte -> "%02x".format(eachByte) }
}


