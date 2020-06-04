package spm.androidworld.all.mvvmArchitecture.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_base.*
import kotlinx.android.synthetic.main.include_error.*
import kotlinx.android.synthetic.main.include_progress.*
import spm.androidworld.all.R
import spm.androidworld.all.base.BaseActivity


abstract class MvvmBaseFragment<V : MvvmBaseViewModel> :
    Fragment() {
    lateinit var baseActivity: MvvmBaseActivity<V>
        private set
    private lateinit var mRootView: View
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


    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is BaseActivity) {
            val activity = context
            baseActivity = activity as MvvmBaseActivity<V>
            activity.onFragmentAttached()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        performDependencyInjection()
        super.onCreate(savedInstanceState)
        mViewModel = getViewModel()
        setHasOptionsMenu(false)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_base, container, false)
        val inflater = activity?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val viewChild: View = inflater.inflate(getLayoutId(), fragment_layout_container, false)
        val frameLayout = view.findViewById<FrameLayout>(R.id.fragment_layout_container)
        frameLayout.addView(viewChild.rootView)
        return view
    }

    override fun onDetach() {
//        baseActivity = null
        super.onDetach()
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)
    }

    fun hideKeyboard() {
        baseActivity.hideKeyboard()
    }

    val isNetworkConnected: Boolean
        get() = baseActivity != null

    fun openActivityOnTokenExpire() {
        if (baseActivity != null) {
            // baseActivity.openActivityOnTokenExpire()
        }
    }

    private fun performDependencyInjection() {
//        AndroidSupportInjection.inject(this)
    }

    interface Callback {
        fun onFragmentAttached()
        fun onFragmentDetached(tag: String?)
        fun showProgress()
        fun hideProgress()
        fun showError(error: String)
        fun showMessage()
    }

    interface ToolbarListener {
        fun setToolbarTitle(title: String?)
        fun setToolbarVisibility(value: Int)
        fun setToolbar()
    }

    public fun showHideProgress(visible: Boolean) {
        progressbarLoading.visibility = if (visible) View.VISIBLE else View.GONE
    }

    public fun showError(message: String) {
        relativeLayoutError.visibility = View.VISIBLE
        textViewErrorTitle.text = message
    }

}