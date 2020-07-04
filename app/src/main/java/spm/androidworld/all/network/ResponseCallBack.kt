package spm.androidworld.all.network

interface ResponseCallBack<S> {
    fun onSuccess(value: S)
    fun onError(error: String)
}