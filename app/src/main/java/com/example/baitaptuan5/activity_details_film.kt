package com.example.baitaptuan5

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_details_film.*

class activity_details_film : AppCompatActivity() {
    val TAG: String = activity_details_film::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details_film)

        getData()
    }

    private fun getData() {
        val data = intent.extras
        if (data != null) {

//            val  name = data.getString(MOVIE_TITLE_KEY)
//            val backdrop = data.getString(MOVIE_BACKDROP_KEY)
//            val release_Dates = data.getString(MOVIE_DATE_KEY)
//            val video = data.getBoolean(MOVIE_VIDEO_KEY)
//            val description = data.getString(MOVIE_DES_KEY)
//            val vote = data.getDouble(MOVIE_VOTE_KEY)


//            name_movie.text = name
//            release_date.text = release_Dates
//            overview.text = description

            val item: Item = data.getParcelable(CONSTANT_KEY) as Item
            val movie = data.getParcelable(MOVIE_KEY) as film.Results
            Log.e(TAG, item.toString())
            name_movie.text = movie.title
            release_date.text = movie.release_date
            overview.text = movie.overview
            Glide.with(this)
                .load("https://image.tmdb.org/t/p/w500/${movie.backdrop_path}")
                .centerCrop()
                .into(movie_view)
            if(movie.video){
                video_play.visibility = View.VISIBLE
            }
            else{
                video_play.visibility = View.INVISIBLE
            }
            vote_rating.rating = (movie.vote_average/2).toFloat()

        }
    }
}
