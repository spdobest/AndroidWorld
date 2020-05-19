package spm.androidworld.all.mviArchitecture

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import spm.androidworld.all.R

class MviActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mvi)

        title = "MVI Architecture Flow"
    }
}
