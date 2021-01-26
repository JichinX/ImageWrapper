package com.xujichang.image

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.WindowManager
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.xujichang.image.databinding.ActivityImageWrapperBinding
import com.xujichang.image.preview.ImagePreview
import com.xujichang.image.preview.ImagePreviewFragment

class ImageWrapperActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityImageWrapperBinding.inflate(layoutInflater)
        setContentView(binding.root)
        configureOpt()
    }

    private fun configureOpt() {
        when (intent.getStringExtra(IMAGE_OPT)) {
            ImagePreviewFragment.FLAG -> {
                val bundle = Bundle().also {
                    it.putStringArrayList(
                        ImagePreviewFragment.URL,
                        intent.getStringArrayListExtra(ImagePreviewFragment.URL)
                    )
                }
                obtainNavController(R.id.fragment_host).setGraph(R.navigation.nav_preview, bundle)
            }
            else -> {
                obtainNavController(R.id.fragment_host).setGraph(R.navigation.nav_error)
            }
        }
    }

    private fun obtainNavController(fragmentHost: Int): NavController =
        (supportFragmentManager.findFragmentById(fragmentHost) as NavHostFragment).navController


    companion object {
        private const val IMAGE_OPT = "image_opt"

        fun createPreviewIntent(context: Context, builder: ImagePreview.Builder): Intent {
            return Intent(context, ImageWrapperActivity::class.java)
                .also { intent ->
                    intent.putExtra(IMAGE_OPT, ImagePreviewFragment.FLAG)
                    intent.putStringArrayListExtra(ImagePreviewFragment.URL, builder.urlList)
                }
        }
    }
}