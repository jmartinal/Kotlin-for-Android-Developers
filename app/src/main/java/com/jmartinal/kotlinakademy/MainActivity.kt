package com.jmartinal.kotlinakademy

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.jmartinal.kotlinakademy.databinding.ActivityMainBinding
import com.jmartinal.kotlinakademy.media.MediaAdapter
import com.jmartinal.kotlinakademy.media.MediaItem
import com.jmartinal.kotlinakademy.media.MediaProvider
import com.jmartinal.kotlinakademy.media.Type
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class MainActivity : AppCompatActivity() {

    private val mediaAdapter = MediaAdapter { startActivity<DetailActivity>(DetailActivity.EXTRA_ITEM_ID to it.id) }
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recycler.adapter = mediaAdapter

        updateItems()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        updateItems(item.itemId)
        return true
    }

    private fun updateItems(filter: Int = R.id.filter_all) {
        lifecycleScope.launch {
            binding.recycler.visibility = View.GONE
            binding.progress.visibility = View.VISIBLE
            mediaAdapter.mediaItems = withContext(Dispatchers.IO) { getFilteredItems(filter) }
            binding.progress.visibility = View.GONE
            binding.recycler.visibility = View.VISIBLE
        }
    }

    private fun getFilteredItems(filter: Int): List<MediaItem> {
        return MediaProvider.getItems().let { media ->
            when(filter) {
                R.id.filter_all -> media
                R.id.filter_videos -> media.filter { it.type == Type.VIDEO }
                R.id.filter_photos -> media.filter { it.type == Type.PHOTO }
                else -> emptyList()
            }
        }
    }
}