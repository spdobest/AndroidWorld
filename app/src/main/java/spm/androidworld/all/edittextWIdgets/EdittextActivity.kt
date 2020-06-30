package spm.androidworld.all.edittextWIdgets

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_edittext.*
import spm.androidworld.all.R


class EdittextActivity : AppCompatActivity(),
    AddressSearchWithTypingDelayEdittext.OnCharTypeDelayListener {

    private var listItems = ArrayList<String>()
    private val addressSearchAdapter = AddressSearchAdapter(listItems)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edittext)
        for (i in 1..10) {
            listItems.add(" Search String $i")
        }
        edittextTimeDelay.setListener(this)

        recyclerviewSearch.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerviewSearch.adapter = addressSearchAdapter
        addressSearchAdapter.setDevider(recyclerviewSearch)
    }

    override fun onTypeDelayChar(chars: String) {

        Log.i("TAG onTypeDelayChar", chars)

        if (chars.length >= 2) {
            progress.visibility = View.VISIBLE
            listItems.clear()
            for (i in 1..10) {
                listItems.add("$chars Search String $i")
            }

            Handler().postDelayed({
                progress.visibility = View.GONE
                edittextTimeDelay.setText(listItems[0])
                addressSearchAdapter.notifyDataSetChanged()
            }, 3000)
        } else {

        }
    }
}
