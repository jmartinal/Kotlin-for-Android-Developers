package com.jmartinal.kotlinakademy.media

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jmartinal.kotlinakademy.R

class MediaAdapter(private val items: List<MediaItem>) :
    RecyclerView.Adapter<MediaAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.view_media_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val mediaThumb: ImageView = itemView.findViewById(R.id.mediaThumb)
        private val mediaTitle: TextView = itemView.findViewById(R.id.mediaTitle)

        fun bind(item: MediaItem) {
            Glide.with(mediaThumb).load(item.url).into(mediaThumb)
            mediaTitle.text = item.title
        }
    }
}