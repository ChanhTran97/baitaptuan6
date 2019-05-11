package com.example.baitaptuan5

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_fragment_now_playing.*
import okhttp3.*
import java.io.IOException

class NowPlaying : Fragment() {

    var film: ArrayList<film.Results> = ArrayList()
    lateinit var filmAdapter: FilmAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addMovie()
        filmAdapter = FilmAdapter(film,activity)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.activity_fragment_now_playing, container, false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        list_film.layoutManager = LinearLayoutManager(context)
        list_film.adapter = filmAdapter

        //set onclick item movie

        filmAdapter.setListener(filmItemCLickListener)
        swipeRefresh.setOnRefreshListener{
            loadItems()
        }
        swipeRefresh.setColorSchemeResources(android.R.color.holo_blue_bright,
            android.R.color.holo_green_light, android.R.color.holo_orange_light, android.R.color.holo_red_light)

    }
    var filmFresh:ArrayList<film.Results> = ArrayList()
    private fun loadItems() {
        filmAdapter.clear()
        ReFresh()
        ItemsLoadCompleted()
    }

    private fun ReFresh() {
        val client = OkHttpClient()
        val request = Request.Builder()
            .url("https://api.themoviedb.org/3/movie/now_playing?api_key=7519cb3f829ecd53bd9b7007076dbe23")
            .build()
        client.newCall(request)
            .enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    activity!!.runOnUiThread {
                        call.cancel()
                    }
                }
                override fun onResponse(call: Call, response: Response) {
                    val json = response.body()!!.string()
                    val datagson = Gson().fromJson(json, film.ResultArray::class.java)
                    activity!!.runOnUiThread {
                        for (i in datagson.results) {
                            filmFresh.add(i)
                        }
                        filmAdapter.addAll(filmFresh)

                    }
                }
            })
    }

    private fun ItemsLoadCompleted() {
        swipeRefresh.isRefreshing = false
    }

    private fun addMovie() {
        val client = OkHttpClient()
        val request = Request.Builder()
            .url("https://api.themoviedb.org/3/movie/now_playing?api_key=7519cb3f829ecd53bd9b7007076dbe23")
            .build()
        client.newCall(request)
            .enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    activity!!.runOnUiThread {
                        call.cancel()
                    }
                }
                override fun onResponse(call: Call, response: Response) {
                    val json = response.body()!!.string()
                    val datagson = Gson().fromJson(json, film.ResultArray::class.java)
                    activity!!.runOnUiThread {
                        film.clear()
                        for (i in datagson.results) {
                            film.add(i)
                            filmAdapter.notifyDataSetChanged()
                        }
                    }
                }
            })


    }
    private val filmItemCLickListener = object : FilmItemClickListener {

        override fun onItemCLicked(position: Int) {
            var category = Category(categoryId = 1, categoryName = "MacBook")
            var item = Item(
                imageId = 2,
                price = 30.0,
                title = "MacBook Pro",
                category = category
            )
            val intent = Intent(activity, activity_details_film::class.java)
            intent.putExtra(MOVIE_KEY, film[position])
            intent.putExtra(CONSTANT_KEY, item)
            startActivity(intent)
        }
    }
}
