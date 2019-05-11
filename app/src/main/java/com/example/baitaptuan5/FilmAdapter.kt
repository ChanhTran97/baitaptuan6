package com.example.baitaptuan5

import android.support.v4.app.FragmentActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_item_film.view.*

class FilmAdapter(var items: ArrayList<film.Results>, val context: FragmentActivity?) : RecyclerView.Adapter<FilmAdapter.FilmViewHolder>() {
    lateinit var mListener: FilmItemClickListener

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): FilmViewHolder {
        return FilmViewHolder(LayoutInflater.from(context).inflate(R.layout.activity_item_film, parent, false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun clear() {
        items.clear()
        notifyDataSetChanged()
    }

    fun addAll(list: ArrayList<film.Results>) {
        items.addAll(list)
        notifyDataSetChanged()
    }

    fun setListener(listener: FilmItemClickListener) {
        this.mListener = listener
    }

    override fun onBindViewHolder(filmViewHolder: FilmViewHolder, position: Int) {
        filmViewHolder.tvTitle.text = "#$position ${items[position].title}"
        filmViewHolder.tvdescription.text = items[position].overview
        context?.let {
            Glide.with(it)
                .load("https://image.tmdb.org/t/p/w500/" + items[position].poster_path)
                .centerCrop()
                .into(filmViewHolder.ivAvatar)


            filmViewHolder.itemView.setOnClickListener {
                mListener.onItemCLicked(position)
            }

        }
    }


    class FilmViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var tvTitle = view.tvTitle
        var tvdescription = view.tvDes
        var ivAvatar = view.ivAvatar
    }
}
