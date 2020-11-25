package spm.androidworld.all.base.use


/**
 * Created by Sibaprasad Mohanty on 18/04/20.
 * Spm Limited
 * sp.dobest@gmail.com
 */


import android.os.Bundle
import kotlinx.android.synthetic.main.activity_base.*
import spm.androidworld.all.R
import spm.androidworld.all.base.BaseActivity
import spm.androidworld.all.utility.LogUtil

class SecondaryActivity : BaseActivity(), BaseActivity.OnNavigationMenuClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        useLayout(base_container, R.layout.activity_useofbase)
        showToolbarIcons(true)

        LogUtil.showLog("use", "oncreate")

        setSecondaryActivity()


        supportFragmentManager.beginTransaction()
            .setCustomAnimations(
                R.anim.slide_in_from_right, R.anim.slide_out_to_left,
                R.anim.slide_in_from_left, R.anim.slide_out_to_right
            )
            .replace(R.id.fragmentContainerView, UseOfBaseFragment())
            .commit()
    }

    override fun onNavigationMenuClick(type: Int) {

    }
}
