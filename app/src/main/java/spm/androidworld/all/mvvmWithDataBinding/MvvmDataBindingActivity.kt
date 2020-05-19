package spm.androidworld.all.mvvmWithDataBinding

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import spm.androidworld.all.R

class MvvmDataBindingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mvvm_data_binding)

        title = "MVVM With Data Binding  Flow"
    }
}
