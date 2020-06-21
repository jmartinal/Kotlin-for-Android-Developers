package com.jmartinal.kotlinakademy

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.jmartinal.kotlinakademy.databinding.ActivityMainBinding
import com.jmartinal.kotlinakademy.media.MediaAdapter
import com.jmartinal.kotlinakademy.media.Type
import com.jmartinal.kotlinakademy.media.getItems

class MainActivity : AppCompatActivity() {

    private val mediaAdapter: MediaAdapter by lazy { MediaAdapter(getItems()) { toast(it.title) } }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recycler.adapter = mediaAdapter
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.filter_all -> mediaAdapter.mediaItems = getItems()
            R.id.filter_photos -> mediaAdapter.mediaItems = getItems().filter { it.type == Type.PHOTO }
            R.id.filter_videos -> mediaAdapter.mediaItems = getItems().filter { it.type == Type.VIDEO }
        }
        return true
    }
}