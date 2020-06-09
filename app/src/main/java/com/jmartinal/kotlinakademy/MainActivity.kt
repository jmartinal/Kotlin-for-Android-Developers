package com.jmartinal.kotlinakademy

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.jmartinal.kotlinakademy.media.MediaAdapter
import com.jmartinal.kotlinakademy.media.getItems

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recycler: RecyclerView = findViewById(R.id.recycler)
        recycler.adapter = MediaAdapter(getItems())
    }

}