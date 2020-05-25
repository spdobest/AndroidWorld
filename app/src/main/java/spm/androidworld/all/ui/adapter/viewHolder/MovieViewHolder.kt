package spm.androidworld.all.ui.adapter.viewHolder

import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatRatingBar
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import spm.androidworld.all.R
import spm.androidworld.all.network.model.Result


/**
 * Created by Sibaprasad Mohanty on 25/05/20.
 * Spm Limited
 * sp.dobest@gmail.com
 */

class MovieViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val imageViewMovie = itemView.findViewById<AppCompatImageView>(R.id.imageViewMovie)
    val tvMovieName = itemView.findViewById<AppCompatTextView>(R.id.tvMovieName)
    val tvReleaseDate = itemView.findViewById<AppCompatTextView>(R.id.tvReleaseDate)
    val ratingMovie = itemView.findViewById<AppCompatRatingBar>(R.id.ratingMovie)
    val tvLanguage = itemView.findViewById<AppCompatTextView>(R.id.tvLanguage)

    fun setData(result: Result) {
        tvMovieName.text = result.original_title
        tvReleaseDate.text = result.first_air_date
        ratingMovie.rating = (result.popularity as? Float)!!
        tvLanguage.text = result.original_language
    }

}