package com.android.assignment.network.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class WeatherData(val name: String, val age: Int) : Parcelable