package spm.androidworld.all.mvvmArchitecture.ui.account.signin

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import spm.androidworld.all.R
import spm.androidworld.all.mvvmArchitecture.base.MvvmBaseDialogFragment

class MvvmSigninFragment : MvvmBaseDialogFragment<MvvmSigninViewModel>() {

    private lateinit var mvvmLoginViewModel: MvvmSigninViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
        this.mvvmLoginViewModel =
            ViewModelProviders.of(this, ViewModelProvider.NewInstanceFactory())
                .get(MvvmSigninViewModel::class.java)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setTitle("Sign In")
    }

    companion object {
        // TODO: Rename and change types and number of parameters
    }

    override fun getLayoutId(): Int = R.layout.fragment_mvvm_signin

    override fun getViewModel(): MvvmSigninViewModel = mvvmLoginViewModel
}