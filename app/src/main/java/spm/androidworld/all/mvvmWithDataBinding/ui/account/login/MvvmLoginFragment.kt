package spm.androidworld.all.mvvmWithDataBinding.ui.account.login

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import spm.androidworld.all.BR
import spm.androidworld.all.R
import spm.androidworld.all.databinding.FragmentMvvmLoginBinding
import spm.androidworld.all.mvvmWithDataBinding.base.MvvmBaseDialogFragment

class MvvmLoginFragment : MvvmBaseDialogFragment<FragmentMvvmLoginBinding, MvvmLoginViewModel>() {

    private lateinit var mvvmLoginViewModel: MvvmLoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
        this.mvvmLoginViewModel =
            ViewModelProviders.of(this, ViewModelProvider.NewInstanceFactory())
                .get(MvvmLoginViewModel::class.java)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setTitle("Sign In")
    }

    companion object {
        // TODO: Rename and change types and number of parameters
    }


    override fun getBindingVariable(): Int = BR.loginViewmodel

    override fun getLayoutId(): Int = R.layout.fragment_mvvm_login

    override fun getViewModel(): MvvmLoginViewModel = mvvmLoginViewModel
}