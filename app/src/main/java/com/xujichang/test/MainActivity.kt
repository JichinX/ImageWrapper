package com.xujichang.test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.xujichang.image.preview.ImagePreview

class MainActivity : AppCompatActivity() {
    private val images = arrayOf(
        "11111",
        "https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=3397537705,1180362904&fm=26&gp=0.jpg",
        "222222"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun testPreview(view: View) {
        ImagePreview.Builder()
            .list(images.asList())
            .build()
            .start(this)
    }
}