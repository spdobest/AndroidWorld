package spm.androidworld.all

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.AppCompatTextView
import androidx.fragment.app.FragmentActivity
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.android.synthetic.main.activity_main.*
import spm.androidworld.all.base.BaseActivity
import spm.androidworld.all.localization.FirstActivity
import spm.androidworld.all.localization.SecondActivity
import spm.androidworld.all.utility.LocaleHelper
import java.util.*

class MainActivity : BaseActivity(), BaseActivity.OnNavigationMenuClickListener,
    View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // setSecondaryActivity()

        buttonFirstScreen.setOnClickListener(this)
        buttonSecondScreen.setOnClickListener(this)
        changeLanguage.setOnClickListener(this)

        val primaryLocale: Locale = this.resources.configuration.locales[0]
        val locale: String = primaryLocale.displayName

        Toast.makeText(this, locale, Toast.LENGTH_LONG).show()
    }

    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(LocaleHelper.onAttach(newBase))
    }

    override fun onNavigationMenuClick(type: Int) {

    }

    fun showLanguageBottomsheet() {

        val mBottomSheetDialogLanguage =
            BottomSheetDialog(this as FragmentActivity, R.style.BaseBottomSheetDialog)
        mBottomSheetDialogLanguage.setCancelable(true)
        mBottomSheetDialogLanguage.setContentView(R.layout.bottomsheet_language)
        val tvHindi = mBottomSheetDialogLanguage.findViewById<AppCompatTextView>(R.id.tvHindi)
        val tvEng = mBottomSheetDialogLanguage.findViewById<AppCompatTextView>(R.id.tvEnglish)
        val tvFrance = mBottomSheetDialogLanguage.findViewById<AppCompatTextView>(R.id.tvFrance)
        tvHindi?.setOnClickListener {
            mBottomSheetDialogLanguage.dismiss()
            LocaleHelper.setLocale(this, "hi");
            changeLanguage.text = getString(R.string.hindi)
            recreate();
        }

        tvFrance?.setOnClickListener {
            mBottomSheetDialogLanguage.dismiss()
            LocaleHelper.setLocale(this, "fr");
            changeLanguage.text = getString(R.string.france)
            recreate()
        }

        tvEng?.setOnClickListener {
            mBottomSheetDialogLanguage.dismiss()
            LocaleHelper.setLocale(this, "en");
            changeLanguage.text = getString(R.string.english)
            recreate()
        }
        mBottomSheetDialogLanguage.show()
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.buttonFirstScreen -> {
                startActivity(Intent(this, FirstActivity::class.java))
            }

            R.id.buttonSecondScreen -> {
                startActivity(Intent(this, SecondActivity::class.java))
            }

            R.id.changeLanguage -> {
                showLanguageBottomsheet()
            }
        }
    }

}
