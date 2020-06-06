package spm.androidworld.all.mvpArchitecture.base

class BasePresenterImpl<V : BaseView?> : BasePresenter<V> {
    var view: V? = null
        protected set

    override fun attachView(view: V) {
        this.view = view
    }

    override fun detachView() {
        view = null
    }

}