package spm.androidworld.all.android_p

import android.os.Bundle
import android.text.Html
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_pfeature.*
import spm.androidworld.all.R

class PFeatureActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pfeature)

        val link = "https://developer.android.com/about/versions/pie/android-9.0"
        tvpFeatureLink.text = Html.fromHtml(link, Html.FROM_HTML_MODE_COMPACT)
    }
}
