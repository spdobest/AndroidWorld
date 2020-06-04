package spm.androidworld.all.mvvmWithDataBinding.ui.account.register

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
import spm.androidworld.all.mvvmWithDataBinding.base.MvvmBaseViewModel


/**
 * Created by Sibaprasad Mohanty on 03/06/20.
 * Spm Limited
 * sp.dobest@gmail.com
 */

class MvvmRegisterViewModel : MvvmBaseViewModel(), LifecycleObserver,
    Observable {

    var progressVisibility: ObservableBoolean = ObservableBoolean()

    @Bindable
    var emailObservable = ObservableField<String>()

    @Bindable
    var passwordObservable = ObservableField<String>()

    @Bindable
    var nameObservable = ObservableField<String>()

    @Bindable
    var mobileObservable = ObservableField<String>()

    var emailError = ObservableField<String>()
    var passwordError = ObservableField<String>()
    var nameError = ObservableField<String>()
    var mobileError = ObservableField<String>()

    var email: String = ""
    var password: String = ""
    var name: String = ""
    var mobile: String = ""

    fun setEmail_Id(email: String) {
        this.email = email
        emailObservable.set(email)
    }

    fun getEmail_Id(): String {
        return email
    }

    fun setPwd(pwd: String) {
        this.password = pwd
        passwordObservable.set(pwd)
    }

    fun getPwd(): String {
        return password
    }

    fun setNameStr(name: String) {
        this.name = name
        emailObservable.set(name)
    }

    fun getNameStr(): String {
        return name
    }

    fun setMobileStr(mobile: String) {
        this.mobile = mobile
        passwordObservable.set(mobile)
    }

    fun getMobileStr(): String {
        return mobile
    }

    fun onRegisterClicked(view: View, fragment: Fragment) {

        progressVisibility.set(true)

        if (!CommonUtils.isValidEmail(email)) {
            emailError.set("Invalid Email Id")
        }

        if (password.length < 6) {
            passwordError.set("Password can not be less than 6")
        }

        if (name.length < 3) {
            nameObservable.set("Name Must be more than 3 character")
        }

        if (mobile.length != 10) {
            mobileObservable.set("Mobile number must be 10 digits")
        }

        Handler().postDelayed({
            progressVisibility.set(false)
//            NavHostFragment.findNavController(fragment).navigate(R.id.homeTestFragment, null)
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

    /*@BindingAdapter("app:error")
    fun onError(edittext: AppCompatEditText, error: String) {
        edittext.error = error
    }

    @BindingAdapter("app:focusChange")
    fun onFocusChange(edittext: AppCompatEditText) {
        edittext.onFocusChangeListener
    }*/

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