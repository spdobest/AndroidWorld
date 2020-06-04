package spm.androidworld.all.mvvmArchitecture.base

import androidx.lifecycle.MutableLiveData


/**
 * Created by Sibaprasad Mohanty on 02/06/20.
 * Spm Limited
 * sp.dobest@gmail.com
 */

abstract class MvvmBaseRepository {

    abstract fun showProgress(): MutableLiveData<Boolean>

    abstract fun showError(): MutableLiveData<String>


}