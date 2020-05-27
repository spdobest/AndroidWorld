package spm.androidworld.all.broadcastReceiver

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_broadcast.*
import spm.androidworld.all.R

class BroadcastActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_broadcast)

        Log.i(TAG, "onCreate")

        btnNormalBroadcast.setOnClickListener(this)
        btnLocalBroadcast.setOnClickListener(this)
        btnBroadcastWithParmission.setOnClickListener(this)
        btnOrderedBroadcast.setOnClickListener(this)
        btnStickyBroadcast.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnNormalBroadcast -> {

            }
            R.id.btnLocalBroadcast -> {

            }
            R.id.btnBroadcastWithParmission -> {

            }
            R.id.btnOrderedBroadcast -> {

            }
            R.id.btnStickyBroadcast -> {

            }
        }
    }

    companion object {
        val TAG = this.javaClass.simpleName
    }
}
