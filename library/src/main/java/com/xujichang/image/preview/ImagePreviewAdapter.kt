package com.xujichang.image.preview

import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.xujichang.image.R
import com.xujichang.image.databinding.ItemImagePreviewBinding
import kotlin.jvm.internal.ReflectionFactory

class ImagePreviewAdapter : RecyclerView.Adapter<ImagePreviewHolder>() {
    private val imageList = arrayListOf<String>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImagePreviewHolder =
        ImagePreviewHolder(
            ItemImagePreviewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: ImagePreviewHolder, position: Int) =
        holder.bind(imageList[position])

    override fun getItemCount(): Int = imageList.size

    fun updateList(list: List<String>) {
        imageList.clear()
        imageList.addAll(list)
        notifyDataSetChanged()
    }
}

class ImagePreviewHolder(private val binding: ItemImagePreviewBinding) :
    RecyclerView.ViewHolder(binding.root) {
    private val TAG = "ImagePreviewHolder"

    fun bind(url: String) {
        Glide.with(itemView).load(url).error(R.drawable.ic_image_preview_error).into(binding.image)
    }
}
