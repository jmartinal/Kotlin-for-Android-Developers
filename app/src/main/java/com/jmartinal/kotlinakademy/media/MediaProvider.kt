package com.jmartinal.kotlinakademy.media

object MediaProvider {
    fun getItems() = (1..10).map {
        Thread.sleep(500)
        MediaItem(
            it,
            "Title $it",
            "https://placekitten.com/200/200?image=$it",
            if (it % 3 == 0) Type.VIDEO else Type.PHOTO
        )
    }
}
