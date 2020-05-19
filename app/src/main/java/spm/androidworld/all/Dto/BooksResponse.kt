package spm.androidworld.all.Dto

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class BooksResponse(
    val copyright: String,
    val last_modified: String,
    val num_results: Int,
//    val results: Results,
    val status: String
) : Parcelable
