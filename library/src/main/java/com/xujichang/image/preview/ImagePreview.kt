package com.xujichang.image.preview

import android.content.Context
import android.content.Intent
import com.xujichang.image.ImageWrapperActivity
import java.util.*
import java.util.concurrent.atomic.AtomicBoolean

class ImagePreview private constructor(private val builder: Builder) {

    fun start(context: Context) {
        context.startActivity(ImageWrapperActivity.createPreviewIntent(context, builder))
    }

    class Builder {
        val urlList = arrayListOf<String>()
        val isBase64 = AtomicBoolean(false)
        fun list(list: List<String>, isBase64: Boolean = false): Builder {
            urlList.clear()
            urlList.addAll(list)
            this.isBase64.set(isBase64)
            return this
        }

        fun single(url: String, isBase64: Boolean = false): Builder {
            urlList.clear()
            urlList.add(url)
            this.isBase64.set(isBase64)
            return this
        }

        fun build(): ImagePreview = ImagePreview(this)
    }
}