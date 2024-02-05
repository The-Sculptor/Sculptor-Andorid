package com.umc.sculptor.ui.museum

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.umc.sculptor.databinding.ItemMuseumSculptorCommentBinding
import java.sql.Connection

class MuseumCommentRVAdapter(private val commentList: ArrayList<Comment>):
        RecyclerView.Adapter<MuseumCommentRVAdapter.ViewHolder>(){
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

        }


    override fun onBindViewHolder(holder: MuseumCommentRVAdapter.ViewHolder, position: Int) {
        holder.commentProfileImage.setImageResource(commentList[position].profileImage!!)
        holder.commentNickname.text = commentList[position].nickname
        holder.commentComment.text = commentList[position].comment
    }

    override fun getItemCount(): Int =commentList.size

}

        

