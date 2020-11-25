package com.android.assignment.base


/**
 * Created by Sibaprasad Mohanty on 2020-01-14.
 * sp.dobest@gmail.com
 * SPM limited
 */

interface BaseInterface {
    fun showProgress()
    fun hideProgress()
    fun showError(error: String)
    fun showMessage()
}