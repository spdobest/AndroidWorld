package spm.androidworld.all.localization

import android.content.Context
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_base.*
import spm.androidworld.all.R
import spm.androidworld.all.base.BaseActivity
import spm.androidworld.all.utility.LocaleHelper

class SecondActivity : BaseActivity(), BaseActivity.OnNavigationMenuClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        useLayout(base_container, R.layout.activity_second)
        showToolbarIcons(true)

        setSecondaryActivity()


/*        supportFragmentManager.beginTransaction()
            .setCustomAnimations(R.anim.slide_in_from_right, R.anim.slide_out_to_left,
                R.anim.slide_in_from_left, R.anim.slide_out_to_right)
            .replace(R.id.fragmentContainerView,
                UseOfBaseFragment()
            )
            .commit()*/
    }

    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(LocaleHelper.onAttach(newBase))
    }

    override fun onNavigationMenuClick(type: Int) {
        /* supportFragmentManager.beginTransaction()
             .setCustomAnimations(R.anim.slide_in_from_right, R.anim.slide_out_to_left,
                 R.anim.slide_in_from_left, R.anim.slide_out_to_right)
             .replace(R.id.fragmentContainerView,
                 UseOfBaseFragment()
             )
             .commit()*/
    }
}
