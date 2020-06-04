package spm.androidworld.all.mvpArchitecture.base

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.ViewGroup
import android.view.Window
import android.widget.RelativeLayout
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import spm.androidworld.all.base.BaseActivity
import spm.androidworld.all.utility.NetworkUtil


/**
 * Created by Sibaprasad Mohanty on 01/06/20.
 * Spm Limited
 * sp.dobest@gmail.com
 */

abstract class BaseMvpDialogFragment<P : BaseMvpPresenter> : DialogFragment() {

    lateinit var mActivity: BaseMvpActivity<P>

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is BaseActivity) {
            val mActivity = context as BaseMvpActivity<P>
            this.mActivity = mActivity
//            mActivity.onFragmentAttached()
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        // the content
        val root = RelativeLayout(activity)
        root.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )

        // creating the fullscreen dialog
        val dialog = Dialog(context)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(root)

        dialog?.window?.let {
            it.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            it.setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
        }

        dialog.setCanceledOnTouchOutside(false)
        return dialog
    }

    override fun show(fragmentManager: FragmentManager, tag: String?) {
        val transaction: FragmentTransaction = fragmentManager.beginTransaction()
        val prevFragment: Fragment? = fragmentManager.findFragmentByTag(tag)
        prevFragment?.let {
            transaction.remove(prevFragment)
        }
        transaction.addToBackStack(null)
        show(transaction, tag)
    }


    open fun dismissDialog(tag: String?) {
        dismiss()
        // getBaseActivity().onFragmentDetached(tag)
    }

    open fun getBaseActivity(): BaseMvpActivity<P> {
        return mActivity
    }

    open fun hideKeyboard() {
        mActivity?.let {
            it.hideKeyboard()
        }
    }

    open fun hideLoading() {
        mActivity?.let {
            it.hideLoading()
        }
    }

    open fun isNetworkConnected(): Boolean {
        return mActivity != null && NetworkUtil.isNetworkConnected(mActivity)
    }

    open fun openActivityOnTokenExpire() {
        mActivity?.let {
            // it.openActivityOnTokenExpire()
        }
    }

    open fun showLoading() {
        mActivity?.let {
            it.showLoading()
        }
    }
}