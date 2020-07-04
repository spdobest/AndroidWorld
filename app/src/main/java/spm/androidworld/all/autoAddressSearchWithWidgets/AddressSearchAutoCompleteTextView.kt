package spm.androidworld.all.autoAddressSearchWithWidgets

import android.content.Context
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.util.Log
import androidx.appcompat.widget.AppCompatAutoCompleteTextView
import spm.androidworld.all.customwidget.AddressSearchWithTypingDelayEdittext
import java.util.*

class AddressSearchAutoCompleteTextView : AppCompatAutoCompleteTextView {

    constructor(context: Context?) : super(context)

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    private var onCharTypeListener: AddressSearchWithTypingDelayEdittext.OnCharTypeDelayListener? =
        null
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
                Log.i("TAG", "beforeTextChanged $text")
            }

            override fun onTextChanged(
                charSequesnce: CharSequence?,
                start: Int,
                end: Int,
                count: Int
            ) {
                Log.i("TAG", "onTextChanged $text")
            }

            override fun afterTextChanged(editable: Editable?) {

                Log.i("TAG", "afterTextChanged $text")

                if (text?.length!! >= 2 && isTypedText) {
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

    fun AppCompatAutoCompleteTextView.setReadOnly(value: Boolean) {
        isFocusable = !value
        isFocusableInTouchMode = !value
        requestFocus()
        this.text?.length?.let { this.setSelection(it) }
    }

}