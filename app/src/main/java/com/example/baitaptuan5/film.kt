package com.example.baitaptuan5

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

class film{
    @Parcelize
    data class ResultArray(
        val results: List<Results>
    ): Parcelable
    @Parcelize
    data class Results(
        val vote_count : Int,
        val id : Int,
        val video : Boolean,
        val vote_average : Double,
        val title : String,
        val popularity : Double,
        val poster_path : String,
        val original_language : String,
        val original_title : String,
        val genre_ids : List<Int>,
        val backdrop_path : String,
        val adult : Boolean,
        val overview : String,
        val release_date : String
    ): Parcelable
//    private val  gson = Gson()
//    private val data = MovieModel.getMovieRaw()
//    private val dataGson = gson.fromJson(data, ResultArray::class.java)
//    fun getMovieModel() : ResultArray{
//        return dataGson
//    }
}