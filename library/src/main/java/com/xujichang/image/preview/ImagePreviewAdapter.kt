package com.xujichang.image.preview

import android.util.Base64
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.xujichang.image.R
import com.xujichang.image.databinding.ItemImagePreviewBinding
import java.util.*
import java.util.concurrent.atomic.AtomicBoolean

class ImagePreviewAdapter : RecyclerView.Adapter<ImagePreviewHolder>() {
    private val imageList = arrayListOf<String>()
    private val isBase64 = AtomicBoolean(false)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImagePreviewHolder =
        ImagePreviewHolder(
            ItemImagePreviewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: ImagePreviewHolder, position: Int) =
        holder.bind(imageList[position], isBase64.get())

    override fun getItemCount(): Int = imageList.size

    fun updateList(list: List<String>) {
        imageList.clear()
        imageList.addAll(list)
        notifyDataSetChanged()
    }

    fun setPreviewType(boolean: Boolean) {
        isBase64.set(boolean)
    }
}

class ImagePreviewHolder(private val binding: ItemImagePreviewBinding) :
    RecyclerView.ViewHolder(binding.root) {
    private val TAG = "ImagePreviewHolder"

    fun bind(url: String, isBase64: Boolean) {
        val builder =
            if (isBase64) {
                val bytes =
                    if (url.startsWith("data:image")) {
                        Base64.decode(url.split(',')[1], Base64.DEFAULT)
                    } else {
                        Base64.decode(url, Base64.DEFAULT)
                    }
                Glide.with(itemView).load(bytes)
            } else {
                Glide.with(itemView).load(url)
            }
        builder.placeholder(R.drawable.ic_image_preview_holder)
            .error(R.drawable.ic_image_preview_error).into(binding.image)
    }
}
