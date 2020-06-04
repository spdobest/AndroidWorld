package spm.androidworld.all

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import spm.androidworld.all.cleanArchitecture.CleanArchitectureActivity
import spm.androidworld.all.mviArchitecture.MviActivity
import spm.androidworld.all.mvpArchitecture.MvpActivity
import spm.androidworld.all.mvvmWithDataBinding.ui.account.MvvmAccountActivity

class MainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        title = "Architecture Design Patterns"

        buttonMvp.setOnClickListener(this)
        buttonMvvm.setOnClickListener(this)
        buttonMvvmDatabinding.setOnClickListener(this)
        buttonMvi.setOnClickListener(this)
        buttonClean.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.buttonMvp -> {
                startActivity(Intent(this, MvpActivity::class.java))
            }
            R.id.buttonMvvm -> {
                startActivity(
                    Intent(
                        this,
                        spm.androidworld.all.mvvmArchitecture.ui.account.MvvmMyAccountActivity::class.java
                    )
                )
            }
            R.id.buttonMvvmDatabinding -> {
                startActivity(Intent(this, MvvmAccountActivity::class.java))
            }
            R.id.buttonMvi -> {
                startActivity(Intent(this, MviActivity::class.java))
            }
            R.id.buttonClean -> {
                startActivity(Intent(this, CleanArchitectureActivity::class.java))
            }
        }
    }
}
