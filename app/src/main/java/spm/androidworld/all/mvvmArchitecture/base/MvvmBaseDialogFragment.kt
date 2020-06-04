package spm.androidworld.all.mvvmArchitecture.base

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.FrameLayout
import android.widget.RelativeLayout
import androidx.annotation.LayoutRes
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import kotlinx.android.synthetic.main.dialogfragment_base.*
import kotlinx.android.synthetic.main.include_error.*
import kotlinx.android.synthetic.main.include_progress.*
import spm.androidworld.all.R
import spm.androidworld.all.base.BaseActivity


/**
 * Created by Sibaprasad
 */
abstract class MvvmBaseDialogFragment<V : MvvmBaseViewModel> : DialogFragment(),
    View.OnClickListener {

    lateinit var baseActivity: MvvmBaseActivity<V>
        private set

    private lateinit var mRootView: View
    private lateinit var mViewModel: V


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
            val mActivity = context
            baseActivity = (mActivity as? MvvmBaseActivity<V>)!!
            mActivity.onFragmentAttached()
        }
    }

    override fun onStart() {
        super.onStart()
        dialog?.let {
            it.window.setWindowAnimations(
                R.style.styleDialogFragment
            )
            val width = ViewGroup.LayoutParams.MATCH_PARENT
            val height = ViewGroup.LayoutParams.MATCH_PARENT
            it.window.setLayout(width, height)
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        // the content
        val root = RelativeLayout(activity)
        root.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )

        // creating the fullscreen dialog
        val dialog = Dialog(context!!)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(root)
        dialog?.let {
            it.window?.let { window ->
                window
                    .setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                window.setLayout(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
            }
        }
        dialog.setCanceledOnTouchOutside(false)
        return dialog
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.dialogfragment_base, container, false)
        val inflater = activity?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val viewChild: View = inflater.inflate(getLayoutId(), container_dialogfragment, false)
        val frameLayout = view.findViewById<FrameLayout>(R.id.container_dialogfragment)
        frameLayout.addView(viewChild.rootView)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        imageViewBack_basedialogfragment.setOnClickListener(this)
        textViewTitle_basedialogfragment.text
    }

    protected fun setTitle(title: String) {
        textViewTitle_basedialogfragment.text = title
    }

    override fun onDetach() {
//        baseActivity = null
        super.onDetach()
    }

    override fun show(
        fragmentManager: FragmentManager,
        tag: String?
    ) {
        val transaction =
            fragmentManager.beginTransaction()
        val prevFragment = fragmentManager.findFragmentByTag(tag)
        if (prevFragment != null) {
            transaction.remove(prevFragment)
        }
        transaction.addToBackStack(null)
        show(transaction, tag)
    }

    fun dismissDialog(tag: String?) {
        dismiss()
        baseActivity.onFragmentDetached(tag)
    }

    fun hideKeyboard() {
        if (baseActivity != null) {
            baseActivity.hideKeyboard()
        }
    }

    fun hideLoading() {
        if (baseActivity != null) {
            baseActivity.hideLoading()
        }
    }

    val isNetworkConnected: Boolean
        get() = baseActivity != null && baseActivity.isNetworkConnected()


    fun showLoading() {
        if (baseActivity != null) {
            baseActivity.showLoading()
        }
    }

    public fun showHideProgress(visible: Boolean) {
        progressbarLoading.visibility = if (visible) View.VISIBLE else View.GONE
    }

    public fun showError(message: String) {
        relativeLayoutError.visibility = View.VISIBLE
        textViewErrorTitle.text = message
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.imageViewBack_basedialogfragment -> {
                dismissAllowingStateLoss()
            }
        }
    }

}