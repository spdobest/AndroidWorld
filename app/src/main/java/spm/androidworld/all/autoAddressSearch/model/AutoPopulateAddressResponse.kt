package spm.androidworld.all.autoAddressSearch.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class AutoPopulateAddressResponse(
    val data: AutoPopulateAddressData,
    val statusCode: String,
    val statusMessage: String,
    val transactionKey: String
) : Parcelable

@Parcelize
data class AutoPopulateAddressData(
    val addresses: List<String>,
    val status: String
) : Parcelable