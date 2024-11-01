package com.kid.playground.activity

import android.os.Bundle
import android.util.Base64
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.kid.playground.R
import com.kid.playground.databinding.ActivityMainBinding
import java.nio.charset.Charset
import java.security.SecureRandom


class MainActivity : AppCompatActivity() {

    private val TAG: String = "chi.trinh"
    private val viewmodel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main).apply {
            this.viewmodel = this@MainActivity.viewmodel
            this.lifecycleOwner = this@MainActivity

            val input = "{\"urls\":[\"Task/160.000.226.682.465.68/\$accept?ac=c5f760ed19f7968fb61ceb28e446023ed769043dba55ee6ef7161df1bfcb4034\"]}"

            val et = encSanicareTokens(input)
            logDebug { "chi.trinh encryptedTokens: $et" }
        }
    }

    private fun encSanicareTokens(input: String): String {

        val iterationCount = 1000
        val keySize = 128

        // Tạo salt ngẫu nhiên
        val salt = ByteArray(16)
        SecureRandom().nextBytes(salt)
        logDebug { "chi.trinh salt hex: ${salt.toHex()}" }
        logDebug { "chi.trinh salt base64: ${Base64.encodeToString(salt, Base64.NO_WRAP)}" }
        logDebug { "chi.trinh salt: ${String(salt, Charsets.UTF_8)}" }

        //Tạo passphrase ngẫu nhiên
        val chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789"
        val passphrase = CharArray(16) { chars[SecureRandom().nextInt(chars.length)] }
        logDebug { "chi.trinh passphrase: ${passphrase.joinToString("")}" }
        logDebug { "chi.trinh passphrase hex: ${passphrase.toHex()}" }

        // Tạo iv ngẫu nhiên
        val iv = ByteArray(16)
        SecureRandom().nextBytes(iv)
        logDebug { "chi.trinh iv hex: ${iv.toHex()}" }

        val aesKey = AES.genKey(salt, passphrase, iterationCount, keySize)
        val cipherText = AES.encrypt(input, iv, aesKey)
        logDebug { "chi.trinh cipherText: \n$cipherText" }

        val publicRSAKey =
            "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCVk68sHqo91Lo0dy3Hguwk1I/W/XHkzr368LNDM40RBcuxf7g7TXtJNO+FofbaTqRCj3Gxkt9FaO6a0K0snG2zIApWIkwut6vilxiTfe5KyZJs3nCPoRPjkWf5aCBRcEG4XcTQ1/9F6ZMa7mSpIU4XInmQWxQPYE4DcoR+7WueWwIDAQAB"
        val cipherKeySpec =
            RSA.encrypt("$iterationCount:::$keySize:::${iv.toHex()}:::${salt.toHex()}:::${passphrase.toHex()}", publicRSAKey)
        logDebug { "chi.trinh cipherKeySpec: $cipherKeySpec" }

        return "$cipherKeySpec:::$cipherText"
    }

    fun CharArray.toHex(charset: Charset = Charsets.UTF_8): String {
        val bytes = toString().toByteArray(charset)
        return bytes.joinToString("") {
            it.toString(16).padStart(2, '0')
        }
    }
}

inline fun Any.logDebug(crossinline block: () -> String) {
    Log.d(this::class.java.simpleName, block())
}

fun ByteArray.toHex(): String = joinToString(separator = "") { eachByte -> "%02x".format(eachByte) }
