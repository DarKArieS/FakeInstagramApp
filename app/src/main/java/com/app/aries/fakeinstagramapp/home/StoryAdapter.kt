package com.app.aries.fakeinstagramapp.home

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.aries.fakeinstagramapp.R
import com.app.aries.fakeinstagramapp.model.FakeModel
import com.app.aries.fakeinstagramapp.model.StoryData
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.story.view.*

class StoryAdapter (
    val context: Context,
    private val itemList: List<StoryData>,
    private val prefixType : Int = 0
): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemViewType(position: Int): Int {
        if (position==0 && prefixType==1) return 1
        return 0
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): RecyclerView.ViewHolder {
        return when(p1){
            1->StoryViewHolder(
                LayoutInflater.from(p0.context).inflate(R.layout.igtv_story, p0, false)
            )
            else->StoryViewHolder(
                LayoutInflater.from(p0.context).inflate(R.layout.story, p0, false)
            )
        }
    }

    override fun getItemCount(): Int = this.itemList.count()

    override fun onBindViewHolder(p0: RecyclerView.ViewHolder, p1: Int) {
        (p0 as StoryViewHolder).bind(itemList[p1])
    }

    inner class StoryViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        init{

        }

        fun bind(item: StoryData) {

            if(adapterPosition==0 && prefixType != 1){
                itemView.addIcon.visibility = View.VISIBLE
                Glide.with(context).load(FakeModel.selfUser.userAvatar)
                    .placeholder(R.drawable.ic_photo_black_200dp).circleCrop()
                    .into(itemView.storyIcon)
                itemView.userName.text = "你的限時動態"
            }else if(adapterPosition==0 && prefixType == 1){
                itemView.userName.text = "IGTV"
            }else{
                itemView.addIcon.visibility = View.INVISIBLE
                Glide.with(context).load(item.storyImage)
                    .placeholder(R.drawable.ic_photo_black_200dp).circleCrop()
                    .into(itemView.storyIcon)
                itemView.userName.text = item.storyName
            }
        }
    }
}