package com.jmartinal.kotlinakademy

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

fun Context.toast(message: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, duration).show()
}

fun RecyclerView.ViewHolder.toast(title: String) {
    itemView.context.toast(title)
}

fun ViewGroup.inflate(@LayoutRes layoutId: Int): View = LayoutInflater
    .from(context)
    .inflate(layoutId, this, false)

fun ImageView.loadUrl(url: String) {
    Glide.with(this).load(url).into(this)
}

inline fun <reified T : Activity> Context.startActivity(vararg pairs: Pair<String, Any>) {
    val bundle = bundleOf(*pairs)

    val intent = Intent(this, T::class.java)
    intent.putExtras(bundle)
    startActivity(intent)
}


