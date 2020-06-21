package com.jmartinal.kotlinakademy

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jmartinal.kotlinakademy.databinding.ActivityMainBinding
import com.jmartinal.kotlinakademy.media.MediaAdapter
import com.jmartinal.kotlinakademy.media.getItems

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recycler.adapter = MediaAdapter(getItems())
    }

}