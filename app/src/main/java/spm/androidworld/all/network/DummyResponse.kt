package spm.androidworld.all.network

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DummyResponse(val requestCode: Int, val responseMessage: String) : Parcelable