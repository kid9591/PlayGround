package com.kid.playground.activity

import android.util.Base64
import java.security.KeyFactory
import java.security.PublicKey
import java.security.spec.X509EncodedKeySpec
import javax.crypto.Cipher

object RSA {

    val RSA_PUBLIC =
            "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAmL/VtIFd9c1lnhEVjj4DjKPH9wKeiYRazFtwrqwrEMkwxcxqoPb9xabZPU30RurH6jpkeyj8EBfO1WV5ojB88rxRK2a+XZntagrVYtdO7Shsj+bcUM1yrcgj/JI8GztslcOloNEH8DWzhShoyKDLawE8+sifYWgDO82NLBsPv6yL7Ofg0GQpDEP4yhczpw9rc1KFIW1xYGqeqwJcHPuU/DTgp+S8KJTPSve6p/GYegPxnTpgpHdJF5FKdeeYW/5ULnA4zpNh2PQu+zVdJbJdjMJFlHsYn1l87rvY2B8woKeW0lerUz6gAEUmRZF2cWvcCMx/ZaBIzrp1X24pa7g1HQIDAQAB"

//    @Throws(
//        NoSuchAlgorithmException::class,
//        InvalidKeySpecException::class
//    )
    private fun genPublicKeyFrom(b64EncodedPublicKey: String): PublicKey? {
        val publicKey: PublicKey?
        val keySpec =
            X509EncodedKeySpec(
                Base64.decode(
                    b64EncodedPublicKey.toByteArray(),
                    Base64.NO_WRAP
                )
            )
        val keyFactory = KeyFactory.getInstance("RSA")
        publicKey = keyFactory.generatePublic(keySpec)
        return publicKey
    }

//    @Throws(
//        BadPaddingException::class,
//        IllegalBlockSizeException::class,
//        InvalidKeyException::class,
//        NoSuchPaddingException::class,
//        NoSuchAlgorithmException::class
//    )
    fun encrypt(data: String, publicKey: String): String {
        val cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding")
        cipher.init(
            Cipher.ENCRYPT_MODE,
            genPublicKeyFrom(publicKey)
        )
        return Base64.encodeToString(
            cipher.doFinal(data.toByteArray()),
            Base64.NO_WRAP
        )
    }
}