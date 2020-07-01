package spm.androidworld.all.edittextWidgets.fragmentWithSearchWidget

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_auto_address_search.*
import spm.androidworld.all.R
import spm.androidworld.all.edittextWidgets.addressSearchImplementation.AddressSearchWidgetImplementationFragment
import spm.androidworld.all.edittextWidgets.fragments.SearchContainerFragment

class AutoAddressSearchActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auto_address_search)

        buttonAutoAddressWidgetImplementation.setOnClickListener(this)
        buttonFragmentWithSearchWidget.setOnClickListener(this)
        buttonFragmentWithoutSearchWidget.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        container.visibility = View.VISIBLE

        when (v?.id) {
            R.id.buttonAutoAddressWidgetImplementation -> {
                supportFragmentManager.beginTransaction().add(
                    R.id.container,
                    AddressSearchWidgetImplementationFragment(),
                    "AddressSearchWidgetImplementationFragment"
                )
                    .addToBackStack("AddressSearchWidgetImplementationFragment")
                    .commit()
            }
            R.id.buttonFragmentWithSearchWidget -> {
                supportFragmentManager.beginTransaction().add(
                    R.id.container,
                    SearchContainerFragment.newInstance(2),
                    "SearchContainerFragment2"
                )
                    .addToBackStack("SearchContainerFragment1")
                    .commit()
            }
            R.id.buttonFragmentWithoutSearchWidget -> {
                supportFragmentManager.beginTransaction().add(
                    R.id.container,
                    SearchContainerFragment.newInstance(1),
                    "SearchContainerFragment1"
                )
                    .addToBackStack("SearchContainerFragment2")
                    .commit()
            }
        }
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount == 1) {
            supportFragmentManager.popBackStack()
            container.visibility = View.GONE
        } else {
            super.onBackPressed()
        }
    }
}