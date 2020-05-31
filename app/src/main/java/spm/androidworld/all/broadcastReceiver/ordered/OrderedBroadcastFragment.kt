package spm.androidworld.all.broadcastReceiver.ordered

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_ordered_broadcast.*
import spm.androidworld.all.R


/**
 * A simple [Fragment] subclass.
 */
class OrderedBroadcastFragment : Fragment(), View.OnClickListener {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ordered_broadcast, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        buttonSendOrderedBroadcast.setOnClickListener(this)
    }

    fun onReceiveFirebaseMessage() {
        val intent = Intent("spm.androidworld.all.action.NOTIFICATION")
        activity?.sendOrderedBroadcast(
            intent,
            null,
            NotificationResultReceiver(),
            null,
            Activity.RESULT_OK,
            null,
            null
        )
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.buttonSendOrderedBroadcast -> {
                onReceiveFirebaseMessage()
            }
        }
    }

}
