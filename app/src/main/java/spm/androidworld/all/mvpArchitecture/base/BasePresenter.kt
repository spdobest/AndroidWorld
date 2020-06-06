package spm.androidworld.all.mvpArchitecture.base

interface BasePresenter<V : BaseView?> {
    fun attachView(view: V)
    fun detachView()
}