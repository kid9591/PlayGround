package com.kid.playground.activity

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.kid.playground.R
import com.kid.playground.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private val TAG: String = "chi.trinh"
    private val viewmodel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main).apply {
            this.viewmodel = this@MainActivity.viewmodel
            this.lifecycleOwner = this@MainActivity

            val input = "{\"urls\":[\"Task/160.000.226.682.465.68/\$accept?ac=c5f760ed19f7968fb61ceb28e446023ed769043dba55ee6ef7161df1bfcb4034\"]}"

            val enc = EReceiptEncryption(true)
            val cipherToken = enc.encryptEReceipt(input)

            logDebug { "chi.trinh cipherToken: $cipherToken" }

//            val plainInput = enc.decryptEReceipt(cipherToken)

            val padding5 = "RUq1Pu0LN2MWubnmtViIPGAkIfECl9POLlX4hYZ81x+pRD1EM5pzx/dk0Dh+Ewp4dDSpsAgU+ln8CWkjEJ/Hixcatz6TDMWx8MsJwu/bM7XmUe02gna5IF9V2JMXuIF7JpzhBKRLrD2MZomjWFQlzNbSsYTg8FNyhtSsQj4RFK8=:::VoFg/iCc+fA03xYU28Xi8tZZt53l9Y9cSTAFLKiUCZE="
//            09:58:26.957 💙 INFO CardLinkManager.createAES():682 - IV: 9528d0acfa571e2c47ba826004b23920
//            09:58:26.957 💙 INFO CardLinkManager.createAES():683 - Salt: cfb4b9f609042d34414263fe8f442abc
//            09:58:26.957 💙 INFO CardLinkManager.createAES():684 - Passphrase: e5cd6baddf5456103548b8ee119043c4
//            09:58:27.405 💙 INFO CardLinkManager.generateKey():709 - Calculated key: bdb2a9b0211fe881dd64d5f9baa98e68

            val padding7 = "fx+FWggK9Qc56G/A7J8GqIhdvCQsAw69GpTa0vBaeP6SbmAfEhQPhoUdAWy08vF0GA0MSc4A3Lw7gBCsm43TNYiNkidfgJLIZEbnovd3bOl70/fvIABv+kLZ6vGL3/NKHelu9iRgVwuBm5izlDKCx2fT6yIffn1Wjun0Y0K+0QU=:::e3hXjOPHkl7+SZTNjxHFA7IJhQ2J1jtOP5bubWplFNA="
//            10:04:25.024 💙 INFO CardLinkManager.createAES():682 - IV: bfd3197d6d70eaf6869dee5bccb38b0a
//            10:04:25.024 💙 INFO CardLinkManager.createAES():683 - Salt: 91681d0da5f2340b75f4d3246a7cb7f6
//            10:04:25.024 💙 INFO CardLinkManager.createAES():684 - Passphrase: 7981239df9984249be426e64fa203299
//            10:04:25.492 💙 INFO CardLinkManager.generateKey():709 - Calculated key: 27585f9d90ea563de7d0f266cf5a0138


            val plainInput = enc.decryptEReceipt(padding5)

            logDebug { "chi.trinh plainInput: $plainInput" }
        }
    }


}

inline fun Any.logDebug(crossinline block: () -> String) {
    Log.d(this::class.java.simpleName, block())
}

