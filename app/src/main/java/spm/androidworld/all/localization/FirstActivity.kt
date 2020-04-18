package spm.androidworld.all.localization


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

class FirstActivity : BaseActivity(), BaseActivity.OnNavigationMenuClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        useLayout(base_container, R.layout.activity_first)
        showToolbarIcons(true)

        LogUtil.showLog("use", "oncreate")

        setSecondaryActivity()
    }

    override fun onNavigationMenuClick(type: Int) {

    }
}
