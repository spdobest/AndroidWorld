package com.android.assignment.network.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class WeatherResponse(val weatherData: WeatherData) : Parcelable