package com.app.aries.fakeinstagramapp.home

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.aries.fakeinstagramapp.R
import com.app.aries.fakeinstagramapp.model.FakeModel
import com.app.aries.fakeinstagramapp.model.PostData
import com.app.aries.fakeinstagramapp.model.StoryData
import com.app.aries.fakeinstagramapp.utilities.ToolbarGetTitle
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.target.Target
import com.bumptech.glide.request.transition.Transition
import kotlinx.android.synthetic.main.fragment_home.view.*
import kotlinx.android.synthetic.main.post.view.*
import kotlinx.android.synthetic.main.story.view.*
import kotlinx.android.synthetic.main.story_container.view.*

class PostAdapter (
    val context: Context,
    private val itemList: List<PostData>,
    private val callbacks : Callbacks,
    private val hasPrefix : Boolean = false
): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var prefixSize = 0
    init {
        if (hasPrefix) prefixSize = 1
    }

    interface Callbacks{
        fun clickUserTitle()
    }

    override fun getItemViewType(position: Int): Int {
        return if (!hasPrefix){1}
        else when(position){
            0->0
            else->1
        }
    }

    override fun onCreateViewHolder(p0: ViewGroup, type: Int): RecyclerView.ViewHolder {
        return when(type){
            0->StoryContainerViewHolder(
                LayoutInflater.from(p0.context).inflate(R.layout.story_container, p0, false)
            )
            1->PostViewHolder(
                LayoutInflater.from(p0.context).inflate(R.layout.post, p0, false)
            )
            else->{throw Exception("no such kind of viewHolder~~~")}
        }
    }

    override fun getItemCount(): Int = this.itemList.count() + prefixSize

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        when(viewHolder){
            is StoryContainerViewHolder->viewHolder.bind()
            is PostViewHolder->viewHolder.bind(itemList[position - prefixSize])
        }
    }

    inner class StoryContainerViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        init{
            itemView.storyRecyclerView.adapter = StoryAdapter(context, FakeModel.getHomeStory(),0)
            val layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
            itemView.storyRecyclerView.layoutManager = layoutManager
        }

        fun bind() {
            // Nothing
        }
    }

    inner class PostViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        init{
            Log.d("MainRecyc","viewHolder init: ${this.hashCode()}")
            itemView.postToolbar.inflateMenu(R.menu.post_menu)

            ToolbarGetTitle(itemView.postToolbar).findTitle()?.setOnClickListener {
                callbacks.clickUserTitle()
            }
        }

        fun bind(item: PostData) {
            //itemView.postToolbar.logo = Glide.with(context).load()
            itemView.postToolbar.title = "　" + item.user.userName
            itemView.postToolbar.subtitle = ""
            Glide.with(context).load(item.postImage).fitCenter().override(1440,3000)
                .placeholder(R.drawable.ic_photo_black_200dp)
                .into(itemView.postImage)

            Glide.with(context).asDrawable().load(item.user.userAvatar).circleCrop().override(150,150)
                .placeholder(R.drawable.ic_photo_black_200dp).into(object : CustomTarget<Drawable>(){
                    override fun onResourceReady(resource: Drawable, transition: Transition<in Drawable>?) {
                        itemView.postToolbar.logo = resource
                    }
                    override fun onLoadCleared(placeholder: Drawable?) {}
                })

            itemView.contentTextView.text = item.postContent
            itemView.likeNumberTextView.text = "${item.numberLikes} 個讚"
            itemView.numberCommentsTextView.text = "查看 ${item.numberComments} 則留言"
        }
    }

}