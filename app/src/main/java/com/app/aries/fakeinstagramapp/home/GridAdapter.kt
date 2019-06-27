package com.app.aries.fakeinstagramapp.home

import android.content.Context
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.app.aries.fakeinstagramapp.R
import com.app.aries.fakeinstagramapp.model.PostData
import com.bumptech.glide.Glide

class GridAdapter (val context: Context, private val imageWidth:Int , private val itemList: List<PostData>):
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemViewType(position: Int): Int {
        return 1
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): RecyclerView.ViewHolder {
        return GridImageViewHolder(ImageView(context))
    }

    override fun getItemCount(): Int = this.itemList.count()

    override fun onBindViewHolder(p0: RecyclerView.ViewHolder, p1: Int) {
        (p0 as GridImageViewHolder).bind(itemList[p1])
    }

    inner class GridImageViewHolder(private val mItemView: ImageView): RecyclerView.ViewHolder(mItemView) {
        init{
            mItemView.layoutParams = ViewGroup.LayoutParams(imageWidth,imageWidth)
        }

        fun bind(item: PostData) {
            Glide.with(context).load(item.postImage)
                .placeholder(R.drawable.ic_photo_black_200dp)
                .centerCrop().override(imageWidth,imageWidth).into(mItemView)
        }
    }
}