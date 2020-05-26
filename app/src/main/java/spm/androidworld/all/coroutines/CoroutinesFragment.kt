package spm.androidworld.all.coroutines

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_list.*
import spm.androidworld.all.R
import spm.androidworld.all.network.model.Result
import spm.androidworld.all.ui.adapter.MovieAdapter


class CoroutinesFragment : Fragment() {

    var listMovieResult = ArrayList<Result>()
    lateinit var movieAdapter: MovieAdapter

    lateinit var coroutineViewModel: CoroutineViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        coroutineViewModel = ViewModelProviders.of(this).get(CoroutineViewModel::class.java)
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
}