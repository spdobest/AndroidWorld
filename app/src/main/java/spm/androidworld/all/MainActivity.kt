package spm.androidworld.all

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myexperiment.AddressSearchAdapter
import com.example.myexperiment.AddressSearchEdittext
import com.example.myexperiment.AddressSearchWithTypingDelayEdittext


class MainActivity : AppCompatActivity(), AddressSearchEdittext.OnCharTypeListener,
    AddressSearchWithTypingDelayEdittext.OnCharTypeDelayListener {

    private var listItems = ArrayList<String>()
    private val addressSearchAdapter = AddressSearchAdapter(listItems)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        for (i in 1..10) {
            listItems.add(" Search String $i")
        }
        etTesting.setListener(this)
        edittextTimeDelay.setListener(this)

        recyclerviewSearch.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.VERTICAL, false
        )
        recyclerviewSearch.adapter = addressSearchAdapter
        addressSearchAdapter.setDevider(recyclerviewSearch)
    }

    override fun onTypeThreeChar(chars: String) {
        Log.i("TAG", "chars " + chars)

        progress.visibility = View.VISIBLE

        listItems.clear()
        for (i in 1..10) {
            listItems.add("$chars Search String $i")
        }

        Handler().postDelayed({
            progress.visibility = View.GONE
            etTesting.setResponseText("sibaprasad")
            addressSearchAdapter.notifyDataSetChanged()
        }, 5000)
    }

    override fun onTypeDelayChar(chars: String) {
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
    }
}
