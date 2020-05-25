package spm.androidworld.all.network.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


/**
 * Created by Sibaprasad Mohanty on 25/05/20.
 * Spm Limited
 * sp.dobest@gmail.com
 */

@Parcelize
data class MovieResponse(
    val page: Int,
    val results: List<Result>,
    val total_pages: Int,
    val total_results: Int
) : Parcelable

