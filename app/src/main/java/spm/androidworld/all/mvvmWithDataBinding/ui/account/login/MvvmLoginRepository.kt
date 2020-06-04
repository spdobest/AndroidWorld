package spm.androidworld.all.mvvmWithDataBinding.ui.account.login

import androidx.lifecycle.MutableLiveData
import spm.androidworld.all.mvvmWithDataBinding.base.MvvmBaseRepository


/**
 * Created by Sibaprasad Mohanty on 03/06/20.
 * Spm Limited
 * sp.dobest@gmail.com
 */

class MvvmLoginRepository : MvvmBaseRepository() {

    override fun showProgress(): MutableLiveData<Boolean> {
        TODO("Not yet implemented")
    }

    override fun showError(): MutableLiveData<String> {
        TODO("Not yet implemented")
    }

}