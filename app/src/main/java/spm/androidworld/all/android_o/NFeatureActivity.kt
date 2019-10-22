package spm.androidworld.all.android_o

import android.os.Bundle
import android.text.Html
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_nfeature.*
import spm.androidworld.all.R

class NFeatureActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nfeature)

        val link = "https://developer.android.com/about/versions/oreo/android-8.0"
        tvNFeatureLink.text = Html.fromHtml(link, Html.FROM_HTML_MODE_COMPACT)
    }
}

