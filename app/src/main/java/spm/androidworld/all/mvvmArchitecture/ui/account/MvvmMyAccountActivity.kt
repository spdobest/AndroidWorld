package spm.androidworld.all.mvvmArchitecture.ui.account

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_mvvm_account.*
import spm.androidworld.all.R
import spm.androidworld.all.mvvmArchitecture.base.MvvmBaseActivity
import spm.androidworld.all.mvvmArchitecture.ui.account.signin.MvvmSigninFragment
import spm.androidworld.all.mvvmArchitecture.ui.account.signup.MvvmSignupFragment

class MvvmMyAccountActivity : MvvmBaseActivity<MvvmMyAccountViewModel>(), View.OnClickListener {

    private lateinit var mvvmMyAccountViewModel: MvvmMyAccountViewModel

    override fun getLayoutId(): Int {
        return R.layout.activity_mvvm_account
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        buttonLogin.setOnClickListener(this)
        buttonRegister.setOnClickListener(this)

    }

    override fun getViewModel(): MvvmMyAccountViewModel {
        mvvmMyAccountViewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(MvvmMyAccountViewModel::class.java)
        return mvvmMyAccountViewModel
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.buttonLogin -> {
                MvvmSigninFragment().show(supportFragmentManager.beginTransaction(), "TAG")
            }
            R.id.buttonRegister -> {
                MvvmSignupFragment().show(supportFragmentManager.beginTransaction(), "TAG")
            }
        }
    }
}