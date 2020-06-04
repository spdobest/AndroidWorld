package spm.androidworld.all.mvvmWithDataBinding.ui.account.register

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import spm.androidworld.all.BR
import spm.androidworld.all.R
import spm.androidworld.all.databinding.FragmentMvvmLoginBinding
import spm.androidworld.all.mvvmWithDataBinding.base.MvvmBaseDialogFragment


class MvvmRegisterFragment :
    MvvmBaseDialogFragment<FragmentMvvmLoginBinding, MvvmRegisterViewModel>() {

    private lateinit var mvvmRegisterViewModel: MvvmRegisterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
        this.mvvmRegisterViewModel =
            ViewModelProviders.of(this, ViewModelProvider.NewInstanceFactory())
                .get(MvvmRegisterViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setTitle("Sign Up")
    }

    companion object {
//        @JvmStatic
//        fun newInstance() = MvvmRegisterFragment()
    }

    override fun getBindingVariable(): Int = BR.registerViewmodel

    override fun getLayoutId(): Int = R.layout.fragment_mvvm_register

    override fun getViewModel(): MvvmRegisterViewModel = mvvmRegisterViewModel
}