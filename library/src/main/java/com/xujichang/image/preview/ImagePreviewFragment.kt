package com.xujichang.image.preview

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat.finishAfterTransition
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.xujichang.image.databinding.FragmentImagePreviewBinding

class ImagePreviewFragment : Fragment() {
    private val urlList = arrayListOf<String>()
    private lateinit var binding: FragmentImagePreviewBinding
    private val previewAdapter = ImagePreviewAdapter()

    companion object {
        const val TAG = "ImagePreviewFragment"
        const val FLAG = "image_preview"
        const val URL = "preview_urls"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.getStringArrayList(URL)?.also { list ->
            if (list.isEmpty()) {
                exitPreview("图片列表为空")
            } else {
                urlList.clear()
                urlList.addAll(list)
                previewAdapter.updateList(urlList)
            }
        } ?: exitPreview("未获取到图片列表")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = FragmentImagePreviewBinding.inflate(inflater, container, false).also {
        binding = it
        initView()
    }.root

    private fun initView() {
        binding.vpImage.also { viewPager2 ->
            viewPager2.adapter = previewAdapter
            viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageScrolled(
                    position: Int,
                    positionOffset: Float,
                    positionOffsetPixels: Int
                ) {
                    val index = if (positionOffset > 0.5) {
                        position + 2
                    } else {
                        position + 1
                    }
                    updateIndexTip(index)
                    Log.i(TAG, "onPageScrolled: $position $positionOffset $positionOffsetPixels")
                }
            })
        }
        binding.exitPreview.setOnClickListener {
            activity?.also {
                finishAfterTransition(it)
            }
        }
        if (urlList.isEmpty()) {
            binding.vpTips.visibility = View.GONE
        } else {
            binding.vpTips.visibility = View.VISIBLE
            updateIndexTip(1)
        }
    }

    private fun updateIndexTip(index: Int) {
        binding.vpTips.text = "$index/${previewAdapter.itemCount}"
    }

    private fun exitPreview(msg: String) {
        activity?.also { fActivity ->
            toast(fActivity, msg)
            Handler(Looper.getMainLooper()).postDelayed({ finishAfterTransition(fActivity) }, 1000)
        }
    }

    private fun toast(context: Context, msg: String) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    }
}