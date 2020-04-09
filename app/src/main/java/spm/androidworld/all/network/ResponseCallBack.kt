package com.android.assignment.network

interface ResponseCallBack<S> {
    fun onSuccess(value: S)
    fun onError(error: String)
}