package spm.androidworld.all.mvvmArchitecture.ui.account

import androidx.lifecycle.MutableLiveData
import spm.androidworld.all.mvvmWithDataBinding.base.MvvmBaseRepository


/**
 * Created by Sibaprasad Mohanty on 03/06/20.
 * Spm Limited
 * sp.dobest@gmail.com
 */

class MvvmMyAccountRepository : MvvmBaseRepository() {

    private var progressStatus: MutableLiveData<Boolean> = MutableLiveData()
    private var errorMessage: MutableLiveData<String> = MutableLiveData()

    override fun showProgress(): MutableLiveData<Boolean> = progressStatus

    override fun showError(): MutableLiveData<String> = errorMessage

}