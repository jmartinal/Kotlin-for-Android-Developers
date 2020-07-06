package com.jmartinal.kotlinakademy.media

object MediaProvider {
    fun getItems() = (1..10).map {
        Thread.sleep(1000)
        MediaItem(
            "Title $it",
            "https://placekitten.com/200/200?image=$it",
            if (it % 3 == 0) Type.VIDEO else Type.PHOTO
        )
    }
}
