package spm.androidworld.all.pagingLibrary

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import spm.androidworld.all.R

/**
 * https://developer.android.com/topic/libraries/architecture/paging
 */
class PagingLibActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_paging_lib)
    }
}
