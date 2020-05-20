package spm.androidworld.all.workManager

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import spm.androidworld.all.R


/**
 * https://developer.android.com/topic/libraries/architecture/workmanager/basics
 */
class WorkManagerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_work_manager)
    }
}
