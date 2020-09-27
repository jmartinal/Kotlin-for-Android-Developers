package com.jmartinal.kotlinakademy

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.jmartinal.kotlinakademy.databinding.ActivityMainBinding
import com.jmartinal.kotlinakademy.media.*
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
        val filter = when(item.itemId) {
            R.id.filter_photos -> Filter.ByType(Type.PHOTO)
            R.id.filter_videos -> Filter.ByType(Type.VIDEO)
            R.id.filter_all -> Filter.None
            else -> Filter.None
        }
        updateItems(filter)
        return true
    }

    private fun updateItems(filter: Filter = Filter.None) {
        lifecycleScope.launch {
            binding.recycler.visibility = View.GONE
            binding.progress.visibility = View.VISIBLE
            mediaAdapter.mediaItems = withContext(Dispatchers.IO) { getFilteredItems(filter) }
            binding.progress.visibility = View.GONE
            binding.recycler.visibility = View.VISIBLE
        }
    }

    private fun getFilteredItems(filter: Filter): List<MediaItem> {
        return MediaProvider.getItems().let { media ->
            when(filter) {
                Filter.None -> media
                is Filter.ByType -> media.filter { it.type == filter.type }
            }
        }
    }
}