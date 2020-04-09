package spm.androidworld.all.base.use

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import spm.androidworld.all.R

/**
 * Created by Sibaprasad Mohanty on 2020-01-16.
 * Spm Limited
 * sp.dobest@gmail.com
 */

class SplashDialogFragment : DialogFragment() {
    override fun onStart() {
        super.onStart()
        val dialog = dialog
        if (dialog != null) {

            dialog.window!!.setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(
            R.layout.dialogfragment_splash,
            container,
            false
        )
    }

    companion object {
        val TAG = SplashDialogFragment::class.java.simpleName
    }
}