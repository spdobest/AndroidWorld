package spm.androidworld.all.ui.fragment

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.os.SystemClock
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.fragment_list.*
import spm.androidworld.all.R
import spm.androidworld.all.network.model.Result
import spm.androidworld.all.ui.adapter.MovieAdapter
import java.io.IOException
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL

/**
 * JUST() - is ConcatActivity function in RX java to make any object observable
 * this will emmit the original and emit the on
 */
class JustFragment : Fragment() {

    var listMovieResult = ArrayList<Result>()
    lateinit var movieAdapter: MovieAdapter

    val disposables =
        CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)
        recyclerviewMovies.layoutManager = LinearLayoutManager(activity)
        movieAdapter = MovieAdapter(listMovieResult)
        recyclerviewMovies.adapter = movieAdapter
        movieAdapter.notifyDataSetChanged()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onDetach() {
        super.onDetach()
    }

    fun getUrls(text: String): Observable<List<String>> {
        val urlsList: MutableList<String> =
            ArrayList()
        urlsList.add("$text- http://google.com")
        urlsList.add("$text- http://droidcon.in")
        urlsList.add("$text- http://jsfoo.in")
        urlsList.add("$text- http://hasgeek.tv")
        urlsList.add("$text- http://metarefresh.in")
        urlsList.add("$text- http://facebook.com")
        urlsList.add("$text- http://foursquare.com")
        return Observable.just(urlsList)
    }

    fun getTitle(URL: String): Observable<String> {
        return Observable.just(URL.substring(13))
    }

    private val bitmap: Bitmap?
        private get() {
            val map: Bitmap?
            map = downloadImage("")
            return map
        }

    private fun downloadImage(url: String): Bitmap? {
        var bitmap: Bitmap? = null
        val stream: InputStream?
        val bmOptions = BitmapFactory.Options()
        bmOptions.inSampleSize = 1
        try {
            stream = getHttpConnection(url)
            bitmap = BitmapFactory.decodeStream(stream, null, bmOptions)
            stream!!.close()
        } catch (e1: IOException) {
            e1.printStackTrace()
        }
        return bitmap
    }

    @Throws(IOException::class)
    private fun getHttpConnection(urlString: String): InputStream? {
        var stream: InputStream? = null
        val url = URL(urlString)
        val connection = url.openConnection()
        try {
            val httpConnection =
                connection as HttpURLConnection
            httpConnection.requestMethod = "GET"
            httpConnection.connect()
            if (httpConnection.responseCode == HttpURLConnection.HTTP_OK) {
                stream = httpConnection.inputStream
            }
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
        return stream
    }

    companion object {
        private const val TAG = "JustFragment"
        fun newInstance(): JustFragment {
            val fragment = JustFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }

        fun sampleObservable(): Observable<String> {
            return Observable.defer { // Do some long running operation
                SystemClock.sleep(5000)
                Observable.just(
                    "one",
                    "two",
                    "three",
                    "four",
                    "five"
                )
            }
        }
    }
}