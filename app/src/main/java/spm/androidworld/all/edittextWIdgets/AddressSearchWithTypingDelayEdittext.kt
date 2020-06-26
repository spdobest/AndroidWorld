package spm.androidworld.all.edittextWIdgets

import android.content.Context
import android.os.Handler
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.util.AttributeSet
import android.util.Log
import androidx.appcompat.widget.AppCompatEditText
import java.util.*

class AddressSearchWithTypingDelayEdittext constructor(context: Context, attrs: AttributeSet) :
    AppCompatEditText(context, attrs) {

    private var onCharTypeListener: OnCharTypeDelayListener? = null
    private var timer: Timer = Timer()
    private val DELAY: Long = 1000 // milliseconds
    private var isTypedText: Boolean = true

    init {
        val handler = Handler()
        this.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(
                charSequesnce: CharSequence?,
                p1: Int,
                p2: Int,
                p3: Int
            ) {
                Log.i("TAG", "beforeTextChanged ")
            }

            override fun onTextChanged(
                charSequesnce: CharSequence?,
                start: Int,
                end: Int,
                count: Int
            ) {
                Log.i("TAG", "onTextChanged ")
            }

            override fun afterTextChanged(editable: Editable?) {
                Log.i("TAG", "afterTextChanged " + editable.toString())
                if (editable.toString().length >= 2 && isTypedText) {
                    handler.removeCallbacksAndMessages(null)
                    handler.postDelayed({
                        editable?.let {
                            onCharTypeListener?.onTypeDelayChar(editable.toString())
                            setReadOnly(true)
                        }
                    }, DELAY)
                }
            }
        })
    }

    fun setResponseText(response: String) {
        Log.i("TAG", "RESPONSE " + response)
        // setReadOnly(false, InputType.TYPE_CLASS_TEXT)
        setText(response)
        setSelection(response.length)
    }

    fun setListener(onCharTypeListener: OnCharTypeDelayListener) {
        this.onCharTypeListener = onCharTypeListener
    }

    fun AppCompatEditText.setReadOnly(value: Boolean, inputType: Int = InputType.TYPE_NULL) {
        isFocusable = !value
        isFocusableInTouchMode = !value
        this.inputType = inputType
        this.text?.length?.let { this.setSelection(it) }
    }

    interface OnCharTypeDelayListener {
        fun onTypeDelayChar(chars: String)
    }

    override fun setText(text: CharSequence?, type: BufferType?) {
        isTypedText = false
        super.setText(text, type)
        text?.length?.let { setSelection(it) }
        Log.i("TAG", "setText $text")
        isTypedText = true
    }
}