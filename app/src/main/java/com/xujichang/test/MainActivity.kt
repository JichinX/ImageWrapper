package com.xujichang.test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.xujichang.image.preview.ImagePreview

class MainActivity : AppCompatActivity() {
    private val images = arrayOf(
        "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fcdn.duitang.com%2Fuploads%2Fitem%2F201511%2F12%2F20151112144901_etMf5.thumb.700_0.jpeg&refer=http%3A%2F%2Fcdn.duitang.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1614246792&t=22f987758600b3e8178375db5498dff9",
        "https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=3397537705,1180362904&fm=26&gp=0.jpg",
        "222222",
        "https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=1762427954,3355296629&fm=26&gp=0.jpg"
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