package spm.androidworld.all.cleanArchitecture

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import spm.androidworld.all.R

class CleanArchitectureActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_clean_architecture)

        title = "Clean Architecture Flow"
    }
}