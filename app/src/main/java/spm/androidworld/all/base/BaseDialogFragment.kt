package spm.androidworld.all.base

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.annotation.LayoutRes
import androidx.fragment.app.DialogFragment
import kotlinx.android.synthetic.main.dialogfragment_base.*
import spm.androidworld.all.R
import spm.androidworld.all.utility.LogUtil


/**
 * Created by sibaprasad on 20/06/17.
 */

abstract class BaseDialogFragment : DialogFragment() {

    private var mActivity: BaseActivity? = null

    private var mLayoutId = 0

    /**
     * @return layout resource id
     */
    @LayoutRes
    abstract fun getLayoutId(): Int


    open fun getBaseActivity(): BaseActivity? {
        return mActivity
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        LogUtil.showLog("BaseFragment", "onCreate")
    }

    override fun onStart() {
        super.onStart()
        val dialog = dialog
        if (dialog != null) {
            dialog.window!!.setWindowAnimations(
                R.style.styleDialogFragment
            )

            dialog.window!!.setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }
    }

    fun setToolbarTitle(title: String) {
        textViewTitle_basedialogfragment.text = title
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is BaseActivity) {
            mActivity = context
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_base, container, false)
        val container = view.findViewById<FrameLayout>(R.id.container_dialogfragment)
        inflater.inflate(mLayoutId, container)
        Log.i(TAG, "oncreateVIew Parent")
        return view
        /*val view = inflater.inflate(R.layout.dialogfragment_base, container, false)
        mViewDataBinding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false)
        inflater.inflate(getLayoutId(), container_dialogfragment)
        return mViewDataBinding?.root */
    }

    override fun onDestroy() {
        if (dialog != null && retainInstance) {
            dialog!!.setOnDismissListener(null)
        }
        super.onDestroy()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.i(TAG, "onViewCreated Parent")
    }

    protected fun setLayout(layoutId: Int) {
        mLayoutId = layoutId
    }

    protected fun showProgress() {

    }

    protected fun hideProgress() {

    }

    companion object {

        val TAG = "BaseDialogFragment"
    }
}