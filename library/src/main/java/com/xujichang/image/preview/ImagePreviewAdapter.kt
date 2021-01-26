package com.xujichang.image.preview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.xujichang.image.R
import com.xujichang.image.databinding.ItemImagePreviewBinding

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
    fun bind(url: String) {
        Glide.with(itemView).load(url).into(binding.image)
    }
}
