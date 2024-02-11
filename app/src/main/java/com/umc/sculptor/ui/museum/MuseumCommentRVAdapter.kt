package com.umc.sculptor.ui.museum

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.umc.sculptor.R
import com.umc.sculptor.databinding.ItemMuseumSculptorCommentBinding
import java.sql.Connection

class MuseumCommentRVAdapter(private val commentList: ArrayList<Comment>):
        RecyclerView.Adapter<MuseumCommentRVAdapter.ViewHolder>(){

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

    fun addItem(comment: Comment){
        commentList.add(comment)
        notifyDataSetChanged()
    }

    inner class ViewHolder(val binding: ItemMuseumSculptorCommentBinding)
        : RecyclerView.ViewHolder(binding.root){
            val commentProfileImage=binding.museumSculptorProfile
            val commentNickname=binding.museumSculptorName
            val commentComment=binding.museumSculptorCommentText
            val commentHeartImage=binding.museumCommentLike
        }


    override fun onBindViewHolder(holder: MuseumCommentRVAdapter.ViewHolder, position: Int) {
        holder.commentProfileImage.setImageResource(commentList[position].profileImage!!)
        holder.commentNickname.text = commentList[position].nickname
        holder.commentComment.text = commentList[position].comment
        if (commentList[position].heartImg==true) {
            holder.commentHeartImage.setImageResource(R.drawable.icon_heart_fill)
        }
        else {
            holder.commentHeartImage.setImageResource(R.drawable.icon_heart)
        }

        holder.commentHeartImage.setOnClickListener{
            myItemClickListener.onItemClick(position)
            if (commentList[position].heartImg==false) {
                holder.commentHeartImage.setImageResource(R.drawable.icon_heart_fill)
                commentList[position].heartImg=true
            }
            else {
                holder.commentHeartImage.setImageResource(R.drawable.icon_heart)
                commentList[position].heartImg=false

            }
        }

    }

    override fun getItemCount(): Int =commentList.size

}

        

