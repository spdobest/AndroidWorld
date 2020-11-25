package spm.androidworld.all.base

import android.content.Context
import android.content.DialogInterface
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.annotation.NonNull
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import spm.androidworld.all.R


class BaseBottomSheetDialog : BottomSheetDialog {

    private lateinit var behavior: BottomSheetBehavior<FrameLayout>
    @BottomSheetBehavior.State
    private var defaultState = BottomSheetBehavior.STATE_COLLAPSED

    constructor(@NonNull context: Context) : super(context)

    constructor(@NonNull context: Context, theme: Int) : super(context, theme)

    protected constructor(
        @NonNull context: Context, cancelable: Boolean,
        cancelListener: DialogInterface.OnCancelListener
    ) : super(context, cancelable, cancelListener)

    override fun setContentView(view: View, params: ViewGroup.LayoutParams?) {
        super.setContentView(view, params)
        val bottomSheetInternal = this.findViewById<FrameLayout>(R.id.design_bottom_sheet)
        behavior = BottomSheetBehavior.from(bottomSheetInternal!!)
    }

    override fun setContentView(view: View) {
        super.setContentView(view)
        val bottomSheetInternal = this.findViewById<FrameLayout>(R.id.design_bottom_sheet)
        behavior = BottomSheetBehavior.from(bottomSheetInternal!!)
    }

    override fun setContentView(layoutResId: Int) {
        super.setContentView(layoutResId)
        val bottomSheetInternal = this.findViewById<FrameLayout>(R.id.design_bottom_sheet)
        behavior = BottomSheetBehavior.from(bottomSheetInternal!!)
    }

    override fun onStart() {
        super.onStart()
        if (behavior.state == BottomSheetBehavior.STATE_HIDDEN) {
            behavior.state = defaultState
        }
    }

    /**
     * @return the [BottomSheetBehavior] of the bottom sheet.
     */
    @NonNull
    override fun getBehavior(): BottomSheetBehavior<FrameLayout> {
        return behavior
    }

    /**
     * This method sets the default state of the [BottomSheetBehavior].
     * @param defaultState from which is the default state to be shown when dialog is created.
     */
    fun setDefaultState(@BottomSheetBehavior.State defaultState: Int) {
        this.defaultState = defaultState
    }
}