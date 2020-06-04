package spm.androidworld.all.mvvmWithDataBinding.base

import android.annotation.TargetApi
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.FrameLayout
import android.widget.ProgressBar
import androidx.annotation.LayoutRes
import androidx.annotation.Nullable
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import com.heyyy.main.utility.AppConstant
import kotlinx.android.synthetic.main.activity_base.*
import spm.androidworld.all.R
import spm.androidworld.all.base.BaseActivity
import spm.androidworld.all.utility.ImageUtil
import spm.androidworld.all.utility.NetworkUtil


abstract class MvvmBaseActivity<T : ViewDataBinding, V : MvvmBaseViewModel> : AppCompatActivity(),
    MvvmBaseFragment.Callback, MvvmBaseFragment.ToolbarListener {

    private var isSecondaryActivity = false
    private var enableToolbarIcons: Boolean = false
    private lateinit var onNavigationMenuCLickListener: BaseActivity.OnNavigationMenuClickListener

    private var mProgress: ProgressBar? = null
    private lateinit var mViewDataBinding: T
    private lateinit var mViewModel: V

    /**
     * Override for set binding variable
     *
     * @return variable id
     */
    abstract fun getBindingVariable(): Int

    /**
     * @return layout resource id
     */
    @LayoutRes
    abstract fun getLayoutId(): Int

    /**
     * Override for set view model
     *
     * @return view model instance
     */
    abstract fun getViewModel(): V

    override fun onFragmentAttached() {

    }

    override fun onFragmentDetached(tag: String?) {

    }

    override fun onCreate(@Nullable savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)
        performDataBinding()
        setUpDrawer()
    }

    open fun getViewDataBinding(): T {
        return mViewDataBinding
    }

    @TargetApi(Build.VERSION_CODES.M)
    open fun hasPermission(permission: String): Boolean {
        return checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED
    }

    open fun hideKeyboard() {
        val view: View? = this.currentFocus
        if (view != null) {
            val imm: InputMethodManager =
                getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    open fun hideLoading() {
        mProgress?.let {
            if (it.visibility == View.VISIBLE) {
                it.visibility = View.GONE
            }
        }
    }

    open fun isNetworkConnected(): Boolean {
        return NetworkUtil.isNetworkConnected(applicationContext)
    }

    open fun openActivityOnTokenExpire() {
//        startActivity(LoginActivity.newIntent(this))
//        finish()
    }

    @TargetApi(Build.VERSION_CODES.M)
    open fun requestPermissionsSafely(
        permissions: Array<String?>,
        requestCode: Int
    ) {
        requestPermissions(permissions, requestCode)
    }

    open fun showLoading() {
        hideLoading()
        // mProgressDialog = CommonUtils.showLoadingDialog(this)
    }

    open fun performDataBinding() {
        val inflater =
            getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        mViewDataBinding = DataBindingUtil.inflate(inflater, getLayoutId(), base_container, false)
//        mViewDataBinding = DataBindingUtil.setContentView(this, getLayoutId())
        this.mViewModel = getViewModel()
        val view = inflater.inflate(getLayoutId(), base_container, true)
        mViewDataBinding.let {
            it.setVariable(getBindingVariable(), mViewModel)
            it.executePendingBindings()
        }
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

    interface OnNavigationMenuClickListener {
        fun onNavigationMenuClick(type: Int)
    }
}