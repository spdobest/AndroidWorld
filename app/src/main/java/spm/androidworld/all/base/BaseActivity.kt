package spm.androidworld.all.base


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.FrameLayout
import androidx.annotation.LayoutRes
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.android.assignment.base.BaseInterface
import com.google.android.material.navigation.NavigationView
import com.heyyy.main.utility.AppConstant
import kotlinx.android.synthetic.main.activity_base.*
import spm.androidworld.all.R
import spm.androidworld.all.utility.ImageUtil
import spm.androidworld.all.utility.LogUtil


abstract class BaseActivity : AppCompatActivity(), BaseInterface, BaseFragment.ToolbarListener {

    private var isSecondaryActivity = false
    private var enableToolbarIcons: Boolean = false
    private lateinit var onNavigationMenuCLickListener:OnNavigationMenuClickListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)
        setUpDrawer()
        LogUtil.showLog("Base", "oncreate")
        onNavigationMenuCLickListener = this as OnNavigationMenuClickListener
    }

    fun setSecondaryActivity() {
        isSecondaryActivity = true
        lockDrawer()
        drawerToggleDelegate?.setActionBarUpIndicator(
            ContextCompat.getDrawable(
                this,
                R.drawable.ic_back
            ), R.string.back
        )
        toolbar.setNavigationOnClickListener(View.OnClickListener {
            onBackPressed()
        })
    }

    protected fun useLayout(container: FrameLayout, @LayoutRes layout: Int): View? {
        val inflater =
            getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        return inflater.inflate(layout, container, true)
    }

    protected open fun showSplashDialog() {
       /* val splashDialog = SplashDialogFragment()
        try {
            splashDialog.show(supportFragmentManager, SplashDialogFragment.TAG)
        } catch (e: IllegalStateException) {
            Toast.makeText(this, R.string.something_went_wrong, Toast.LENGTH_SHORT).show()
        }

        Handler().postDelayed({
            splashDialog.dismiss()
        }, 2000)*/
    }

    protected open fun lockDrawer() {
        drawerLayoutBase.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
    }

    override fun showProgress() {

    }

    override fun hideProgress() {

    }

    override fun showError(error: String) {
    }

    override fun showMessage() {
    }

    private fun setUpDrawer() {

        setSupportActionBar(toolbar)

        val ab: ActionBar? = supportActionBar
        val mDrawerToggle = ActionBarDrawerToggle(
            this, drawerLayoutBase, toolbar,
            R.string.drawer_open, R.string.drawer_close
        )
        with(ab) {
            if (!isSecondaryActivity) {
                this?.setHomeAsUpIndicator(R.drawable.ic_menu)
                drawerLayoutBase.addDrawerListener(mDrawerToggle)
                supportActionBar?.setDisplayHomeAsUpEnabled(true)
                supportActionBar?.setHomeButtonEnabled(true)
                mDrawerToggle.syncState()
                setupDrawerContent(navigationViewHome)
            } else {
                this?.setHomeAsUpIndicator(R.drawable.ic_back)
            }
            this?.setDisplayHomeAsUpEnabled(true)
        }


    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        if (enableToolbarIcons) {
            menuInflater.inflate(R.menu.menu_search, menu)
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.home -> {

                return true
            }
            /*R.id.share -> {
                val shareIntent = Intent()
                shareIntent.action = Intent.ACTION_SEND
                shareIntent.type = "text/plain"
                shareIntent.putExtra(Intent.EXTRA_TEXT, "This is my text to send.")
                startActivity(
                    Intent.createChooser(
                        shareIntent,
                        getString(R.string.send_to)
                    )
                )
                return true
            } */
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setupDrawerContent(navigationView: NavigationView) {

        navigationView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                //HOME WITH TABS
                R.id.menuleft_men -> {
                    onNavigationMenuCLickListener.onNavigationMenuClick(AppConstant.NAV_TYPE_MEN)
                }
                R.id.menuleft_women -> {

                }
                R.id.menuleft_kids -> {

                }
            }
            menuItem.isChecked = true
            drawerLayoutBase.closeDrawers()
            true
        }
    }

    protected open fun showBackButton() {
        setSupportActionBar(toolbar)
    }

    override fun setToolbarTitle(title: String?) {
        supportActionBar?.let {
            it.title = title
        }
    }

    override fun setToolbarVisibility(value: Int) {
        toolbar.visibility = value
    }

    protected fun showToolbarIcons(value: Boolean) {
        this.enableToolbarIcons = value
    }

    override fun setToolbar() {
        setHomeUpButtonColor(R.color.black)
        setSupportActionBar(toolbar)
        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
    }

    protected open fun setHomeUpButtonColor(colorId: Int) {
        val upArrow =
            ContextCompat.getDrawable(this, R.drawable.ic_delete)
        supportActionBar?.setHomeAsUpIndicator(
            ImageUtil.changeTintColor(
                this,
                upArrow,
                colorId
            )
        )
    }
    interface OnNavigationMenuClickListener{
        fun onNavigationMenuClick(type:Int)
    }
}
