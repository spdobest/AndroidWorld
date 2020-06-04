package spm.androidworld.all.mvvmWithDataBinding.ui.account

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_mvvm_account.*
import spm.androidworld.all.BR
import spm.androidworld.all.R
import spm.androidworld.all.databinding.ActivityMvvmAccountBinding
import spm.androidworld.all.mvvmWithDataBinding.base.MvvmBaseActivity
import spm.androidworld.all.mvvmWithDataBinding.ui.account.login.MvvmLoginFragment
import spm.androidworld.all.mvvmWithDataBinding.ui.account.register.MvvmRegisterFragment

class MvvmAccountActivity : MvvmBaseActivity<ActivityMvvmAccountBinding, MvvmAccountViewModel>(),
    View.OnClickListener {

    private lateinit var mvvmAccountViewModel: MvvmAccountViewModel

    override fun getBindingVariable(): Int {
        return BR.accountViewModel
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_mvvm_account
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        buttonLogin.setOnClickListener(this)
        buttonRegister.setOnClickListener(this)

    }

    override fun getViewModel(): MvvmAccountViewModel {
        mvvmAccountViewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(MvvmAccountViewModel::class.java)
        return mvvmAccountViewModel
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.buttonLogin -> {
                MvvmLoginFragment().show(supportFragmentManager.beginTransaction(), "TAG")
            }
            R.id.buttonRegister -> {
                MvvmRegisterFragment().show(supportFragmentManager.beginTransaction(), "TAG")
            }
        }
    }

    /*
    fun newIntent(context: Context?): Intent? {
        return Intent(context, ActivityMvvmAccountBinding::class.java)
    }*/
}