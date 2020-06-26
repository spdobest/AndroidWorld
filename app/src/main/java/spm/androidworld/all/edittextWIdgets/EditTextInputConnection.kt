package spm.androidworld.all.edittextWIdgets

import android.view.inputmethod.InputConnection
import android.view.inputmethod.InputConnectionWrapper

internal class EditTextInputConnection(
    target: InputConnection?,
    mutable: Boolean
) : InputConnectionWrapper(target, mutable) {
    override fun commitText(
        text: CharSequence,
        newCursorPosition: Int
    ): Boolean {
        // some code which takes the input and manipulates it and calls editText.getText().replace() afterwards
        return true
    }
}