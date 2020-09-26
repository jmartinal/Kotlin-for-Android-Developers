package com.jmartinal.kotlinakademy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import com.jmartinal.kotlinakademy.databinding.ActivityDetailBinding
import com.jmartinal.kotlinakademy.media.MediaProvider
import com.jmartinal.kotlinakademy.media.Type
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_ITEM_ID = "DetailActivity:id"
    }

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val itemId = intent.getIntExtra(EXTRA_ITEM_ID, -1)

        binding.progress.visibility = View.VISIBLE
        lifecycleScope.launch {
            val items = withContext(Dispatchers.IO) { MediaProvider.getItems() }
            val mediaItem = items.find { it.id == itemId }
            mediaItem?.let {
                supportActionBar?.title = it.title
                binding.detailThumb.loadUrl(it.url)
                binding.detailVideoIndicator.visibility = when (it.type) {
                    Type.PHOTO -> View.GONE
                    Type.VIDEO -> View.VISIBLE
                }
            }
            binding.progress.visibility = View.GONE
        }

    }
}