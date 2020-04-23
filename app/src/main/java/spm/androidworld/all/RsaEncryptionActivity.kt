package spm.androidworld.all

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_rsa_encryption.*
import spm.androidworld.all.security.KeyStoreHelper


class RsaEncryptionActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rsa_encryption)

        KeyStoreHelper.createKeys(this, BuildConfig.KEY_ALIAS)

        buttonEncrypt.setOnClickListener(this)
        buttonDncrypt.setOnClickListener(this)
        tvEncryptOrDecrypt.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.buttonEncrypt -> {
                val dataToEncrypt = appCompatEditText.text.toString()
                tvEncryptOrDecrypt.text =
                    KeyStoreHelper.rsaEncryptKey(BuildConfig.KEY_ALIAS, dataToEncrypt)

                KeyStoreHelper.put(this, "key", dataToEncrypt)

            }
            R.id.buttonDncrypt -> {
                val dataToEncrypt = appCompatEditText.text.toString()
                tvEncryptOrDecrypt.text =
                    KeyStoreHelper.rsaDecryptKey(BuildConfig.KEY_ALIAS, dataToEncrypt)
                tvEncryptOrDecrypt.text = KeyStoreHelper.get(this, "key")
            }
            R.id.tvEncryptOrDecrypt -> {
                val clipboard: ClipboardManager =
                    getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                val clip = ClipData.newPlainText("Text Copied", tvEncryptOrDecrypt.text)
                clipboard.primaryClip = clip
            }
        }
    }
}
