package spm.androidworld.all.mvvmArchitecture

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import spm.androidworld.all.R

class MvvmActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mvvm)

        title = "MVVM Architecture Flow"
    }
}
