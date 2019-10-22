package spm.androidworld.all.android_q

import android.os.Bundle
import android.text.Html
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_qfeature.*
import spm.androidworld.all.R

class QFeatureActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_qfeature)

        val link = "https://developer.android.com/about/versions/10/features"

        tvQFeatureLink.text = Html.fromHtml(link, Html.FROM_HTML_MODE_COMPACT)

    }
}
