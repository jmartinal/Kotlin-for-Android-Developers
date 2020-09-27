package com.jmartinal.kotlinakademy.media

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jmartinal.kotlinakademy.R
import com.jmartinal.kotlinakademy.databinding.ViewMediaItemBinding
import com.jmartinal.kotlinakademy.inflate
import com.jmartinal.kotlinakademy.loadUrl
import kotlin.properties.Delegates

    typealias Listener = (MediaItem) -> Unit

class MediaAdapter(items: List<MediaItem> = emptyList(), private val onClick: Listener) :
    RecyclerView.Adapter<MediaAdapter.ViewHolder>() {

    var mediaItems: List<MediaItem> by Delegates.observable(items) { _, new, old ->
        if (new != old) {
            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = parent.inflate(R.layout.view_media_item)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mediaItems[position]
        holder.bind(item)
        holder.itemView.setOnClickListener { onClick(item) }
    }

    override fun getItemCount() = mediaItems.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ViewMediaItemBinding.bind(itemView)

        fun bind(item: MediaItem) {
            with(binding) {
                mediaThumb.loadUrl(item.url)
                mediaTitle.text = item.title
                mediaVideoIndicator.visibility = when (item.type) {
                    Type.VIDEO -> View.VISIBLE
                    Type.PHOTO -> View.GONE
                }
            }
        }
    }
}