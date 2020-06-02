package com.jmartinal.kotlinakademy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Exercise 2: define a variable to retrieve the "Hello World!" TextView and modify its text
        val textView = findViewById<TextView>(R.id.message)
        textView.text = "Hello Kotliner!"

        toast("Hello world")
    }

    // Exercise 1: Create a fun to toast a message
    private fun toast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}