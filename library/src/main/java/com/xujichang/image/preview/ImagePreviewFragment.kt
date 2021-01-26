package com.xujichang.image.preview

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
            urlList.clear()
            urlList.addAll(list)
            previewAdapter.updateList(urlList)
        }
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
        updateIndexTip(1)
    }

    private fun updateIndexTip(index: Int) {
        binding.vpTips.text = "$index/${previewAdapter.itemCount}"
    }
}