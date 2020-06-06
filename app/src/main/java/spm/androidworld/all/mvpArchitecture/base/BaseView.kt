package spm.androidworld.all.mvpArchitecture.base

import android.view.View

interface BaseView {
    fun showProgress()
    fun hideProgress()
    fun getContentView(): View?
    fun showError(error: String?)
    fun showMessage()
}