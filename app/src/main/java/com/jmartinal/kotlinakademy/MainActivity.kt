package com.jmartinal.kotlinakademy

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Exercise 3: Create a button and an EditText ant show a toast with the text when clicked
        val nameET = findViewById<EditText>(R.id.et_name)
        val button = findViewById<Button>(R.id.btn_greeting)

        button.setOnClickListener { toast("Hello ${nameET.text}") }
    }

    // Exercise 1: Create a fun to toast a message
    private fun toast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}