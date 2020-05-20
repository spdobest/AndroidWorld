package spm.androidworld.all

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import spm.androidworld.all.cameraX.CameraXActivity
import spm.androidworld.all.dataBinding.OneWayDataBindingActivity
import spm.androidworld.all.dataBinding.TwoWayDatabindingActivity
import spm.androidworld.all.navigation.NavigationHomeActivity
import spm.androidworld.all.pagingLibrary.PagingLibActivity
import spm.androidworld.all.slice.SliceActivity
import spm.androidworld.all.viewPager.NewViewPagerActivity
import spm.androidworld.all.workManager.WorkManagerActivity

class MainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        title = "JetPack Features"

        buttonCameraX.setOnClickListener(this)
        buttonSlice.setOnClickListener(this)
        buttonDataBinding1.setOnClickListener(this)
        buttonDataBinding2.setOnClickListener(this)
        buttonViewPager.setOnClickListener(this)
        buttonWorkManager.setOnClickListener(this)
        buttonPagingLibrary.setOnClickListener(this)
        buttonNavigation.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.buttonCameraX -> {
                startActivity(Intent(this, CameraXActivity::class.java))
            }
            R.id.buttonSlice -> {
                startActivity(Intent(this, SliceActivity::class.java))
            }
            R.id.buttonDataBinding1 -> {
                startActivity(Intent(this, OneWayDataBindingActivity::class.java))
            }
            R.id.buttonDataBinding2 -> {
                startActivity(Intent(this, TwoWayDatabindingActivity::class.java))
            }
            R.id.buttonWorkManager -> {
                startActivity(Intent(this, WorkManagerActivity::class.java))
            }
            R.id.buttonViewPager -> {
                startActivity(Intent(this, NewViewPagerActivity::class.java))
            }
            R.id.buttonPagingLibrary -> {
                startActivity(Intent(this, PagingLibActivity::class.java))
            }
            R.id.buttonNavigation -> {
                startActivity(Intent(this, NavigationHomeActivity::class.java))
            }
        }
    }
}
