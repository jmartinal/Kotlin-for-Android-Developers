package com.jmartinal.kotlinakademy.media

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jmartinal.kotlinakademy.R
import com.jmartinal.kotlinakademy.inflate
import com.jmartinal.kotlinakademy.loadUrl
import com.jmartinal.kotlinakademy.toast
import kotlinx.android.synthetic.main.view_media_item.view.*

class MediaAdapter(private val items: List<MediaItem>) :
    RecyclerView.Adapter<MediaAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = parent.inflate(R.layout.view_media_item)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: MediaItem) {
            itemView.mediaThumb.loadUrl(item.url)
            itemView.mediaTitle.text = item.title
            itemView.mediaVideoIndicator.visibility = when(item.type) {
                Type.VIDEO -> View.VISIBLE
                Type.PHOTO -> View.GONE
            }
            itemView.setOnClickListener { toast(item.title) }
        }
    }
}