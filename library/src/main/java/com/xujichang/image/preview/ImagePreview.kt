package com.xujichang.image.preview

import android.content.Context
import android.content.Intent
import com.xujichang.image.ImageWrapperActivity

class ImagePreview private constructor(private val builder: Builder) {

    fun start(context: Context) {
        context.startActivity(ImageWrapperActivity.createPreviewIntent(context, builder))
    }

    class Builder {
        val urlList = arrayListOf<String>()
        fun list(list: List<String>): Builder {
            urlList.clear()
            urlList.addAll(list)
            return this
        }

        fun build(): ImagePreview = ImagePreview(this)
    }
}