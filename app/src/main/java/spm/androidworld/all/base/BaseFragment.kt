package spm.androidworld.all.base


import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_base.*
import spm.androidworld.all.R
import spm.androidworld.all.utility.LogUtil


abstract class BaseFragment : Fragment() {

    private var mActivity: BaseActivity? = null

    private var mLayoutId = 0
    private var toolbarListener: ToolbarListener? = null


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


    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is BaseActivity) {
            mActivity = context
        }

        try {
            toolbarListener = context as ToolbarListener
        } catch (e: ClassCastException) {
            throw ClassCastException(
                (context as Activity).localClassName
                        + " must implement MenuClickListener, ToolbarListener, LoginSuccessListener"
            )
        }
    }


    override fun onCreateView(inflater: LayoutInflater, @Nullable container: ViewGroup?, @Nullable savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.fragment_base, container, false)
        inflater.inflate(getLayoutId(), fragment_layout_container)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }


    protected fun hideProgressBar() {
        progressbarLoading.visibility = View.GONE
    }

    protected fun showProgressBar() {
        progressbarLoading.visibility = View.VISIBLE
    }

    protected fun hideLoadMore() {
        progressbarLoadMore.visibility = View.GONE
    }

    protected fun showLoadMore() {
        progressbarLoadMore.visibility = View.VISIBLE
    }


    protected fun setLayout(layoutId: Int) {
        mLayoutId = layoutId
    }

    override fun onDetach() {
        super.onDetach()
    }

    interface ToolbarListener {
        fun setToolbarTitle(title: String?)
        fun setToolbarVisibility(value: Int)
        fun setToolbar()
    }

}
