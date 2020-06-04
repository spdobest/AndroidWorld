package spm.androidworld.all.mvvmArchitecture.ui.account.signup

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import spm.androidworld.all.R
import spm.androidworld.all.mvvmArchitecture.base.MvvmBaseDialogFragment


class MvvmSignupFragment :
    MvvmBaseDialogFragment<MvvmSignupViewModel>() {

    private lateinit var mvvmRegisterViewModel: MvvmSignupViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
        this.mvvmRegisterViewModel =
            ViewModelProviders.of(this, ViewModelProvider.NewInstanceFactory())
                .get(MvvmSignupViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setTitle("Sign Up")
    }

    companion object {
//        @JvmStatic
//        fun newInstance() = MvvmRegisterFragment()
    }

    override fun getLayoutId(): Int = R.layout.fragment_mvvm_signup

    override fun getViewModel(): MvvmSignupViewModel = mvvmRegisterViewModel
}