package com.kid.playground.activity

import android.app.Activity
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.kid.playground.R
import de.akquinet.health.service.cardlink.sdk.*


class MainActivity : Activity(), ICardlinkCallback {

    var selectedButtonId = R.id.button_all

    val keyStore = "MIISsQIBAzCCEncGCSqGSIb3DQEHAaCCEmgEghJkMIISYDCCCI8GCSqGSIb3DQEHBqCCCIAwggh8AgEAMIIIdQYJKoZIhvcNAQcBMBwGCiqGSIb3DQEMAQYwDgQIEmRO+Vp3drICAggAgIIISN6XpJTiSJhJlPTmrD0xbzgpac5G6/C3bhy2tkUead0tx3oB8tEcabt7iltizd0vVpFAFuvhfduspJU54GKwqg5Hi7NjROUz2vLliTrnbVBONM2TI4ERuLJ6N0wL73WIJE0lumUIYq700wK3wEkQqmX3Y5E8Kn1YkgcyIOEBY96AVz3p/b9YWxUMN8v/iwgqdL0PdiUUHWScbDBCUcYxt2qCS567+yl4P1ehWI+2g+UwjvvfV43jLtZYoQpkgzdFQLMIkSLHORvxRdEFOBJ/VgtbA14ygztDQ6HPM7n3+/9GoBcP6yiu8EOYOqx2/MIZqAEp8mL3Za84qfZhtN5XWSVpUy9uFEIQ46WHu4XO+vi4aBISXY/VdHi7S1YcyOzQpJC6yMrwTyBKWSsQi8ByZ1HdkSM0dr7c5Jpkp1NtYbQSshFh0FTVav0iB0RjCJaOJOYDTdIJybPpTF+drUpEythMIrUpIyEWGRD2Tsgdj5RfL5wS3QYuV+x8q7eoxUzZ8j8Du4iSVYgZ4QyKA+aTQxkN42JsG4RhozmL7ueL+HOZFcaA51kgX9Ceu85hD4myhKJL+xAXxK18h7BWiGZZWzjEiSEn8OmeucCK0PSDRbyn6O39FLzhsAAs7zW7JOph+ZQh+0V8KMTCD7lFnxzZe+JqhQ946Ei8L3l3vNcKlCZkgDUaeFKWMg2hS2b2CMALXXmzX6YV6XbDQ9k4zfbyh6Cy6wede6g0ERPPoahzhpGmHa0VW1S4THXgy+Dnqk+PTZ4KzBhfA82cf0c5QaDbuJsDV9tLCMY0lnZt+Xx+jANBXHojKyjBqzSFXOiEuVPEP3h9PvyEtP9ZrtPI1KfMyGSfxJ84GoXtOEAOVoCuvNnfJCKQ52gUNNmsyOcPeIIbcRxxvy+jMgalAeWqBQ2Ns01PlDqlBz88idnGi2sluKPmyU1o/fQlIMRNG8FEBglpmYFoC/Y/bYVzXeJ5Y9ndR7qYXvU6w6a3buuukIh+H9G4VEpy3A9gR28dkAbyzx310duLIOQc+v33+enY0C2ROI3PCsIBgMEBge0bKbMMpbKSQV78HwqhMj+8wCeizkLQJjNXxHEOkpANE4QDVpt/cuQVU13fHstnGe9GLcww7HpqA0MnPZH2Y+7M2SJGihpFYqtFmg0IyZ6pcfNpEWAgvKQTznX62K10SPX/Njjlx8bnGRu24qZyko+hQb/Ucnk1NOfFpDUPDHvgsq3vIRGoLkAfYjroTuKUhSpRIocvw+5sl/U+4pO/Jf7niX2IOefqlq9is417YxzRifTSRkU/ZcvJocJfe1yxxJybxm9Y1fr6mz3ljEr3QoQ91NNV47WOBafRb1HdozaYxspeHsfBtiXUY6IhG4PHz/8v/rpwLDnXqy4k5zgtBnCuI/Jy6Wl3Kz4VsY2TWafatlE6rCivtkayJBSbC6MDMrpKV9wAADSITc+7TNTk1Pmdt3OM/f2bEktbtJjFxWFx1NbMI/kjKTvkjtTYG5CTwEtZyKZ5m0eAZ/f8LRSOfK67u158f7QXp9x15WqIskwmud/7WfxArhGyyuDVWMDlvSH3GuE4S4mGpHdyB7qgthMHY39uGhKoBIbG+8xsEbVKV2G6mBiO0SkroiIa5LCWz9GqX9G0o65FXfm5VqAz8FNoG7w37KzKWwivobUpHbWpQQPRR4ANXob1na/H9jZbD8e+wCIdfhJ44yzgZ4bYe8kFM3MLPWdiYidPFIyASMuhLzrVYV7kaYi6J4nnf0BmhWNi541fwaYhuCaWYN30kjHpepoYWjzPxiTUVGrHctFFpZfMMLn0HbIGnb+0QeLR0EHbM9TcSJTmJ0c4M9l4IMXbLftBC8ejt/MoFb+53nxbFjBf6ehks0Jt2qpuoE7A32aznkQJ1MJ4blXtjgzKIWaObTMf499fNm8RjRWWZBckO5NuyXrqlFA6w02a+PfhzJc7fLqVDvAHe/3Yls6o+jC95NI7NAdOsz0gEUPvgZXDlsIRkphfnprJW0lgjZ+W8IZJzuU1N5QINKxK6OFnlDcxVgrbf1b11c8ft/n7zpL8GW6FZ55E1hYo9sxkLkzEFkmbKGJHaWr3MM/wsMgqOL1e1z/Eu20y+77EDdipNeIx6aPe9AWxh9KcH+Yva5CXZUaLmq7Kh2Weh6FhCuC1JkIdqEIavWqF3PFeBrX9Ay7C9+I4sJzNTzRIYP0vCf5ookfN28qJWRnbYy4r+pyAmHTmcorAr79IKQkGLsi60ZlSA0LTqwqjyl+daYNYPTmCMQSn7OMb/zbvEbvyU/5OLVH4KzkuYhG/F7EQCGXw+3Ewu3IE1sDEkvBKySyJQlymr/nNFoj843ZcWbslwpV8sRrS1mwmupKdf1Qv0RUOpv1g7F7OcMWVVG7H9LQQpOQhLDr1VZ8y7W7Gyw2Y4prPxyFEK69urmEpI4DhGIe0NdGpSNN+AAdpFUGcDyz3pxIgugGakd/ZCPvhbXwIgab1F/IJhmlZjM14bEnwjGke9wFz42kX7QskH9WKdKvgxV+FCUaWg2ReKH7nXQz7IGGmnmPMAa6GP7ALtytutis8bd9S+T0fXXGqAQsPFBzt9yrQ27b7ZLgR4sEy3RfIV8kxJaJo4IivO30oxGGjFV6O4Dpt7+QbgcaFPtPhNBw08R8awkQydE+Wn9ZDK02GG7M34Qw+QXhyOpXrmw3VSGhgdQMKQRmPJICCvf49w0mI6yyarPHFZ4Y2JOmnK1KZw8UBOdNOBz3LoAyZGBK7DchKohFw1CCHl0E+T8RbZesKS2iZEoDQHIpPonS7godaraRnEaoCztEJi0nMt/iAbueNwiGyMIIJyQYJKoZIhvcNAQcBoIIJugSCCbYwggmyMIIJrgYLKoZIhvcNAQwKAQKgggl2MIIJcjAcBgoqhkiG9w0BDAEDMA4ECL9cAXuWND88AgIIAASCCVCdh3TFwo1ermncYDbscIKGVbixif79IFy/4TkMSQ7Q4Enes1Vw9vVlQM6XU5F2Aq+mHYQ6TMeKGecfe3AXGCTU2G3AjUzFiYaDNFSX2tvGGNCmv4fyyNgvYNaEE1bqd7U5Wmb/yos8nq0P/F7hmtuSSOjNk6i4UKzGHwfOW8VuQgTo3NVAEfUo+AusIBBVfDYHDBVpNwjk8wpzTD7ytmzfM+dH7HR75C1Ubo3u/oR3YA7AJ/7yRpFzukQJG6zLF4F8/1NRVsKeb/ziFU+zFcJfHoX93IAbLIppUSlW3bp3xf0onI6pasfqpNl8l6BqN+DPRfe+WmgNrV0HPxdAKtojvjgKUEZcOp9ki83nt2Q0cINqP/AtX8rMeSVkq45MCLF724iOzXE340TB4a4fPZeaP7ddOfUXfBZ41+jH+4C1jqzhcm3BciP8NlmNxcxHn+IfffsD6xZtdgjrne8q3NLFo3isxLybWsjb4hNSi0G2Uul5BUqE72lwYnboWuquYR71n/YHRmgf6JejKMankFMBnSQbGKcdZKBorcHKmTfVBvS0r8mU/UTMnNDtbig4rTNZ8Z0MS9st/nPCoQxb+ofXAwCJuLqim62uM+yMCva5aTMg3hYFe/rkKL2MkpitRjzlj2xZtTDNzu2DU4tWapKyJQASnyq/uDhc14iKFFXkisQcPSW8Mq1xpX4IPqdd9ES0gYl6D4MAyxMdhnkQtjSjRVy8gUQdpAbU7Jd6m99duTMh4ZNduWi8Fa4nvv0NMUyQXf5/m9QTJifmioTE9lTemObBdR0+B7YCzvt4HlPuC5mfXuF7jRgcpXN4avU+ApTIlct+ed0gYPXxaEqX4dXRaZ4WWQRpz3FLv+0yGiw5VDuZ/YH8gke9/u6t3dZeD9pjYIio3C6iUop1/eE6Fr6YZiaMXTD7elGod/CPpMgKCN+YURcq8eVEf9OZyLdnpkOZCzFQHkOiV1Flyhbf2OePbZZP43FAaY0e9NxufJqwtXUtYvHWxFoLH1cEaR1KJn61+g30BpKBmTZBZPNw2Cd+dwBFJ30E3UAHGxvxGAE4Wp0cvOyDsrdG4eA/Bt0UHJgguyZU8XUVd34fQWcwJMkQUFbUa8fnDD7n3cFh7zPtX/+2zcFwdiC3iALzgfbH9tUpSNZroUM7W0f+NfFyyfCnvUr5QJEZv+BdVuHAXUxqlLdmHotTCDfFWSgScrowJNBNsIPPx+wk6N7Ix7DrffMhW6533sJ/I317nHV8oM9SlxjgQLLetXW0QsogY9li5uvZcljrSCzivGKdQicHKAEWteXkj3X3YMDFekn0EllBzv7SheNnIJlKUqvDhvlmD1W2Ynx7m8opLwraedRQfOLm24jv9hcWyPOhsOM74h7aec+0ZUFQNvkVQOgrP1GhD8qWATi5p4HM8a6/VEFn2bfR6n9o9J2pgLqFb8jKnt2xzXnVF8r0LYI75NxFD33ARD9Z9vJvsoXtDqmgAai/SQ7g8w7QxxrkDijCBDpGUvxQEOlnJjYgBpIhG016h0XnbBzxhm0D5oqfeC65MlXv7MnWmFIZgjsxZI08uqHmtnaCgBLAeQaY9sRi6FVPQYoIfrEEHpR9pNq0v9iMa3I5aHXx/I8ZMMmvPYcUBUtpQtJKUSMz/xdbsmqpQrLYC8kcgDMOcm+BPP6GYYA157h6DOTE6cYGnwcaiy9DGB2UUpyt6vFK6oY04wZ+w6iJn1r361nNZODreuJnoqrudG9Oc0Kw6AT/RBJTdDHMC3wY608MH4AdRM1j4odfyYCAgw6LdIyD6UT8sO+KuBr51hwxkmDAPu2xiwwzv1zKJqKoR3/pJskV4WB+6Amq2l8ijaKufapYy5C00CFxxEg1xoflaiixMxKLOw+0QT+RvngXV/OoKZRhtEdE/ZVNCx+fK5Y8v2cFGjAWnOv1556kyb/hy/Rw4kbh7XFECJb7gK5WTSLSFSPuUnvLx1FeCFMqXyxMkMtl5x6LN7yUKg9sM7dUN5FR/e+DeC8TYn2trlntH3F8txnqYN57p/nuRxJ5bOIhcpXiwPsRKbK8ghtZetJOX5ueYsNcdZ7tP8hqmAe6PzFM/bM1Nn+QEzlkGuh730+EfJd6UPXSNHoEhXiq+feMXLzjCYrXAqH7UH/KXmNYw0Q5ob2/QRXL3PctDzgNbruhlpTcnY7U5aUMRAMnq7j/rYtPY7CB8JOgnRJe2/oHIZ1E+ZLPE4vDZ5KezlXk6n1X0jXNZqrpyEJ8NkONtDKhw654jeStb7xt3lEWhbvB+XsgIpy8r4ngLHBH+oeuBilelFC0MgvZUpvQ4SyT+7+QMO4LVKyH/pqSEJvSrfaXjOMbpAuEdkTm2Om/LbM4dJjeksNwR0W9OqUgTf/8GDYUaz1UleSF5W6VE+Lk6vJbAfk9IdqjoFqN1QnxC36ryinXYOL9ubLABvP+NUmDaz/ImoEbCa4S8P8+ck72Z3mYuVaznnvs4xvUIuq2QAeCy9KPEp6q4wtqznftQopogirLFXbDPPwQI7PMgpom7oKxR14OnEF/C9Kh4CMDcWY0+B82AiKQohepSzEBXtzjkyBvpI6+ZjWuax0O4OnnuwswyQ1m18csx0tl3bw6talQAEni5JKZBgPHvx0nFKGO4Rf6CMLqg3Q7odrUTqv9HQdc3+D/xSfccDDC/wpJSLfYk5pXg0yVkdS5eLk7CJm1lHeCK1MifLnpP41+lqeRwPSCHxDs4s2ZEw/HsAF6ESgOn+NqTzQusaD9L0h/hOwgXMPRWD3aIriRbaE49//TsxwVH4Pt5nee/Nu2trA55JorrW+OxgfzNDQ7XEU5ZZlZOz/VjmRc63e7Ks2+DEhyAz3qLhG+4MzxH2kFQtzjIiZ+xZ+rjv3T6g2UudlSskKv6+D0obbjEzVuwavr4utx+lu7mxKyuSAOcu13hGsKVL+79QVd+73jmVwwLKI/u867E7Lh/c7m3M+UgtKwADzohsMnAQksbW8SOe0F8urabc+qrobzgBwlrKilqry9f3Bc5gNiso3LNOf+eCJAl03zEi00T6uG2/kNFE2BZknORmETkB4goIPNIDLI/OavdpWfQZdbdqs+6u/V8iEsetTdOIrDX9mzz6mKGBjpjFqgtR2v+QUVW1WHTpSosv1DH6CrZyTh4E4G0WUSYHOdaqnDuvW6+yrqMzElMCMGCSqGSIb3DQEJFTEWBBRgdZqZi8Qn2xWpyTxAcFGhOUk5mDAxMCEwCQYFKw4DAhoFAAQUZYdKlxVvEJ3cMVEzHuPwgVcobcUECHoYkPjNniSAAgIIAA=="

    companion object {
        const val TAG = "chi.trinh"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val buttonOpen = findViewById<Button>(R.id.button_open)
        val buttonClose = findViewById<Button>(R.id.button_close)
        val buttonAll = findViewById<Button>(R.id.button_all)
        val buttons = listOf(buttonAll, buttonOpen, buttonClose)
        buttons.forEach { button ->
            button.setOnClickListener {
                buttons.uncheckAll()
                button.toggleCheckAppearance(true)
                selectedButtonId = button.id
            }
        }
        buttonAll.performClick()

        Cardlink.initialize(
            "wss://cardlink.akquinet.de/82774e1c-ec10-4f00-8f6d-7cf9253b19ee/websocket/80276883661000000486-20210407",
            this,
            keyStore,
            "IypRwrQBxobQw52vqdgfg1K0qgGbYIlg"
        )
    }

    private fun List<Button>.uncheckAll() {
        forEach { button ->
            button.toggleCheckAppearance(false)
        }
    }

    private fun Button.toggleCheckAppearance(isChecked: Boolean) {
        if (isChecked) {
            backgroundTintList = ColorStateList.valueOf(Color.BLUE)
            setTextColor(Color.WHITE)
        } else {
            backgroundTintList = ColorStateList.valueOf(Color.GRAY)
            setTextColor(Color.BLACK)
        }
    }

    override fun onError(error: CardlinkError, message: String, expectedAction: CardlinkAction) {
        Log.d(TAG, "onError: $message")
    }

    override fun onPrescriptionBundles(bundles: List<String>) {
        TODO("Not yet implemented")
    }

    override fun onPrescriptionTokens(tokens: String) {
        TODO("Not yet implemented")
    }

    override fun onProgressUpdate(progress: Int) {
        Log.d(TAG, "progress: $progress")
    }

    override fun onStateChanged(state: CardlinkState) {
        Log.d(TAG, "state: $state")
    }
}