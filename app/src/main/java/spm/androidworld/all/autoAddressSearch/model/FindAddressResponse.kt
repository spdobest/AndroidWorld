package spm.androidworld.all.autoAddressSearch.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FindAddressResponse(
    val data: FindAddressData,
    val statusCode: String,
    val statusMessage: String,
    val transactionKey: String
) : Parcelable

@Parcelize
data class FindAddressData(
    val addressFound: Boolean,
    val addressOptions: List<AddressOption>,
    val reformattedAddress: String,
    val status: String
) : Parcelable

@Parcelize
data class AddressOption(
    val displayName: String,
    val id: Int,
    val searchName: String
) : Parcelable