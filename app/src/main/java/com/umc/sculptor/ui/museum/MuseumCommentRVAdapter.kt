package com.umc.sculptor.ui.museum

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.umc.sculptor.R
import com.umc.sculptor.data.model.remote.museum.Comment
import com.umc.sculptor.data.model.remote.museum.Stone
import com.umc.sculptor.databinding.ItemMuseumSculptorCommentBinding
import java.sql.Connection


class MuseumCommentRVAdapter(itemList: List<Comment>):
        RecyclerView.Adapter<MuseumCommentRVAdapter.ViewHolder>(){

    var commentList: List<Comment> = itemList
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    interface MyItemClickListener{
        fun onItemClick(position: Int)

    }
    private lateinit var myItemClickListener: MyItemClickListener
    fun setMyItemClickListener(itemClickListener: MyItemClickListener){
        myItemClickListener=itemClickListener
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder(
            ItemMuseumSculptorCommentBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }


    inner class ViewHolder(val binding: ItemMuseumSculptorCommentBinding)
        : RecyclerView.ViewHolder(binding.root){
            val commentProfileImage=binding.museumSculptorProfile
            val commentNickname=binding.museumSculptorName
            val commentComment=binding.museumSculptorCommentText
            val commentHeartImage=binding.museumCommentLike
            val commentTime=binding.commentTime
        }


    override fun onBindViewHolder(holder: MuseumCommentRVAdapter.ViewHolder, @SuppressLint("RecyclerView") position: Int) {
        Glide.with(holder.itemView.context)
            .load(commentList[position].writerProfileImage)
            .placeholder(R.drawable.ellipse) // 이미지 로딩 중에 표시될 placeholder 이미지
            .error(R.drawable.ellipse)
            .into(holder.commentProfileImage)
        holder.commentNickname.text = commentList[position].writerNickname
        holder.commentComment.text = commentList[position].content
        holder.commentTime.text=commentList[position].writeAt

        if (commentList[position].isLike==true) {
            holder.commentHeartImage.setImageResource(R.drawable.icon_heart_fill)
        }
        else {
            holder.commentHeartImage.setImageResource(R.drawable.icon_heart)
        }

        holder.commentHeartImage.setOnClickListener{
            myItemClickListener.onItemClick(position)
            if (commentList[position].isLike==false) {
                holder.commentHeartImage.setImageResource(R.drawable.icon_heart_fill)

            }
            else {
                holder.commentHeartImage.setImageResource(R.drawable.icon_heart)


            }
        }

    }

    override fun getItemCount(): Int =commentList.size

}

        

