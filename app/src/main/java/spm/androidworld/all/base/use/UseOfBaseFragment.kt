package spm.androidworld.all.base.use

import android.os.Bundle
import android.view.View
import com.android.assignment.base.BaseFragment
import spm.androidworld.all.R


class UseOfBaseFragment : BaseFragment() {
    override fun getLayoutId(): Int {
        return R.layout.fragment_useofbase
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showLoadMore()
    }
}
