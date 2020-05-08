package spm.androidworld.all

import android.content.ComponentName
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import spm.androidworld.all.constants.BlankFragment1
import spm.androidworld.all.constants.BlankFragment2


class CustomPermissionActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom_permission)

        /* btnSendWhatsupMessage.setOnClickListener(this)
         btnOpenWhatsup.setOnClickListener(this)
         btnOpenOtherActivity.setOnClickListener(this)*/

        supportFragmentManager.beginTransaction()
            .add(R.id.container, BlankFragment1(), "BlankFragment1")
            .addToBackStack("BlankFragment1")
            .commit()

        supportFragmentManager.beginTransaction()
            .add(R.id.container, BlankFragment2(), "BlankFragment2")
            .addToBackStack("BlankFragment2")
            .commit()

    }

    override fun onBackPressed() {
        super.onBackPressed()
    }

    override fun onClick(v: View?) {
        /* when (v?.id) {
             R.id.btnSendWhatsupMessage -> {
                 sendWhatsapp("Hello...")
             }
             R.id.btnOpenWhatsup -> {
                 openFaceWithNumber("+919768235871")
             }
             R.id.btnOpenOtherActivity -> {
                 launchAnotherActivity()
             }
         }*/
    }

    private fun openFaceWithNumber(num: String) {
        val num = "+919768235871"
        val url = "https://api.whatsapp.com/send?phone=$num"
        val i = Intent(Intent.ACTION_VIEW)
        i.data = Uri.parse(url)
        startActivity(i)
    }

    private fun sendWhatsapp(message: String) {
        val sendIntent = Intent()
        sendIntent.action = Intent.ACTION_SEND
        sendIntent.putExtra(Intent.EXTRA_TEXT, message)
        sendIntent.type = "text/plain"
        sendIntent.setPackage("com.whatsapp")
        if (sendIntent.resolveActivity(packageManager) != null) {
            startActivity(sendIntent)
        }
    }

    /**
     * You can open any activity from another application if it don't have custom permission
     */

    private fun launchAnotherActivity() {
        val intent: Intent = Intent()
        intent.component = (ComponentName(
            "com.wipro.codingexcercise",
            "com.wipro.codingexcercise.HomeActivity"
        ))
        startActivity(intent)
    }

}
