package spm.androidworld.all.edittextWIdgets

import android.content.Context
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.util.AttributeSet
import android.util.Log
import androidx.appcompat.widget.AppCompatEditText
import androidx.lifecycle.MutableLiveData

class AddressSearchEdittext constructor(context: Context, attrs: AttributeSet) :
    AppCompatEditText(context, attrs) {

    public var observableCallBack = MutableLiveData<String>()
    private var onCharTypeListener: OnCharTypeListener? = null

    init {
        this.addTextChangedListener(object : TextWatcher {

            override fun beforeTextChanged(
                charSequesnce: CharSequence?,
                p1: Int,
                p2: Int,
                p3: Int
            ) {
            }

            override fun onTextChanged(
                charSequesnce: CharSequence?,
                start: Int,
                end: Int,
                count: Int
            ) {

            }

            override fun afterTextChanged(editable: Editable?) {
                editable?.let {
                    if (it.length % 3 == 0) {
                        Log.i("TAG", "afterTextChanged " + editable.toString())
                        onCharTypeListener?.onTypeThreeChar(editable.toString())
                        setReadOnly(true)
                        /* handler.postDelayed(Runnable {
                             setReadOnly(false, InputType.TYPE_CLASS_TEXT)
                         }, 3000)*/
                    }
                }
            }
        })
    }

    fun setResponseText(response: String) {
        Log.i("TAG", "RESPONSE " + response)
        setReadOnly(false, InputType.TYPE_CLASS_TEXT)
        setText(response)
        setSelection(response.length)
    }

    fun setListener(onCharTypeListener: OnCharTypeListener) {
        this.onCharTypeListener = onCharTypeListener
    }


    fun AppCompatEditText.setReadOnly(value: Boolean, inputType: Int = InputType.TYPE_NULL) {
        isFocusable = !value
        isFocusableInTouchMode = !value
        this.inputType = inputType
        this.text?.length?.let { this.setSelection(it) }
    }

    interface OnCharTypeListener {
        fun onTypeThreeChar(chars: String)
    }
}