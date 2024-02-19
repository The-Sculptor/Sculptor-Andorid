package com.umc.sculptor.ui.museum

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.umc.sculptor.R
import com.umc.sculptor.apiManager.ServicePool
import com.umc.sculptor.data.model.remote.museum.Comment
import com.umc.sculptor.data.model.remote.museum.CommentLike
import com.umc.sculptor.data.model.remote.museum.CommentLikeDto
import com.umc.sculptor.data.model.remote.museum.EditReqeustDto
import com.umc.sculptor.data.model.remote.museum.EditUserDto
import com.umc.sculptor.data.model.remote.museum.Stone
import com.umc.sculptor.databinding.ItemMuseumSculptorCommentBinding
import com.umc.sculptor.login.LocalDataSource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
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

                val call: Call<CommentLike> = ServicePool.museumService.changeCommentLike(
                    "JSESSIONID=" + LocalDataSource.getAccessToken().toString(), commentList[position].id
                )

                // 비동기적으로 요청 수행
                call.enqueue(object : Callback<CommentLike> {
                    override fun onResponse(
                        call: Call<CommentLike>,
                        response: Response<CommentLike>
                    ) {
                        if (response.isSuccessful) {
                            commentList[position].isLike=true
                            Log.d("방명록 서버", response.toString())


                        } else {

                            // 서버에서 오류 응답을 받은 경우 처리
                            Log.d("방명록 서버", "서버통신 오류")
                        }
                    }

                    override fun onFailure(call: Call<CommentLike>, t: Throwable) {
                        // 통신 실패 처리
                        Log.d("방명록 서버", t.message.toString())
                    }
                })
            }
            else {
                holder.commentHeartImage.setImageResource(R.drawable.icon_heart)

                val call2: Call<CommentLike> = ServicePool.museumService.changeCommentLike(
                    "JSESSIONID=" + LocalDataSource.getAccessToken().toString(), commentList[position].id
                )

                // 비동기적으로 요청 수행
                call2.enqueue(object : Callback<CommentLike> {
                    override fun onResponse(
                        call: Call<CommentLike>,
                        response: Response<CommentLike>
                    ) {
                        if (response.isSuccessful) {
                            commentList[position].isLike=false
                            Log.d("방명록 서버", response.toString())


                        } else {
                            // 서버에서 오류 응답을 받은 경우 처리
                            Log.d("방명록 서버", "서버통신 오류")
                        }
                    }

                    override fun onFailure(call: Call<CommentLike>, t: Throwable) {
                        // 통신 실패 처리
                        Log.d("방명록 서버", t.message.toString())
                    }
                })

            }
        }

    }

    override fun getItemCount(): Int =commentList.size

}

        

