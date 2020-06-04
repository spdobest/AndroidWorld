package spm.androidworld.all.mvvmArchitecture.ui.account.signin

import android.os.Handler
import android.util.Log
import android.view.View
import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.android.assignment.utility.CommonUtils
import spm.androidworld.all.mvvmArchitecture.base.MvvmBaseViewModel


/**
 * Created by Sibaprasad Mohanty on 03/06/20.
 * Spm Limited
 * sp.dobest@gmail.com
 */

class MvvmSigninViewModel : MvvmBaseViewModel(), LifecycleObserver,
    Observable {

    var progressVisibility: ObservableBoolean = ObservableBoolean()

    @Bindable
    var emailStr = ObservableField<String>()

    @Bindable
    var passwordStr = ObservableField<String>()

    @Bindable
    var emailError = ObservableField<String>("")

    @Bindable
    var passwordError = ObservableField<String>("")

    var email: String = ""
    var password: String = ""

    fun setEmail_Id(email: String) {
        this.email = email
        emailStr.set(email)
    }

    fun getEmail_Id(): String {
        return email
    }

    fun setPwd(pwd: String) {
        this.password = pwd
        passwordStr.set(pwd)
    }

    fun getPwd(): String {
        return password
    }

    fun onLoginClicked(view: View, fragment: Fragment) {
        progressVisibility.set(true)

        if (!CommonUtils.isValidEmail(email)) {
            emailError.set("Invalid Email Id")
        }

        if (password.length < 6) {
            passwordError.set("Password can not be less than 6")
        }


        Handler().postDelayed({
            progressVisibility.set(false)
        }, 3000)
    }

    fun onTextChangedEmail(
        s: CharSequence,
        start: Int,
        before: Int,
        count: Int
    ) {
        Log.w("tag", "onTextChanged $s")
    }

    fun onTextChangedPassword(
        s: CharSequence,
        start: Int,
        before: Int,
        count: Int
    ) {
        Log.w("tag", "onTextChanged $s")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun getData() {
//        progressVisibility = true
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun start() {
        Log.e("TAG", "================================>>>> START lifecycle owner STARTED")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun stop() {
        Log.e("TAG", "================================>>>> STOP lifecycle owner STOPED")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun resume() {
        Log.e("TAG", "================================>>>> RESUME lifecycle owner STARTED")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun pause() {
        Log.e("TAG", "================================>>>> PAUSE lifecycle owner STARTED")
    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

}