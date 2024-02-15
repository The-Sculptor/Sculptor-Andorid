package com.umc.sculptor.ui.home

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.umc.sculptor.R
import com.umc.sculptor.apiManager.ServicePool
import com.umc.sculptor.data.model.remote.home.Data
import com.umc.sculptor.data.model.remote.home.FollowingsStone
import com.umc.sculptor.data.model.remote.home.PressLike
import com.umc.sculptor.databinding.ItemFriendStatueCardBinding
import com.umc.sculptor.login.LocalDataSource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FriendStatueAdapter(itemList: List<Data>) :
    RecyclerView.Adapter<FriendStatueAdapter.ViewHolder>() {

    var friendStatueList: List<Data> = itemList
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    // 아이템 클릭 리스너 인터페이스
    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    private var onItemClickListener: OnItemClickListener? = null

    // 클릭 리스너 설정 메서드
    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.onItemClickListener = listener
    }


    inner class ViewHolder(itemViewBinding: ItemFriendStatueCardBinding)
        : RecyclerView.ViewHolder(itemViewBinding.root){
        val statueName = itemViewBinding.tvStatueName
        val friendStone = itemViewBinding.layoutFriendStone
        val friendNickname = itemViewBinding.tvNickname
        val dDay = itemViewBinding.tvDDay
        val achievement = itemViewBinding.tvAchievementRate
        val goal = itemViewBinding.tvGoal
        val date = itemViewBinding.tvGoalDate
        val heartNumber = itemViewBinding.tvHeartNumber

        val heart = itemViewBinding.ivHeartStatus
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder(
            ItemFriendStatueCardBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = friendStatueList.size

    override fun onBindViewHolder(holder: ViewHolder, @SuppressLint("RecyclerView") position: Int) {
        holder.friendStone.setOnClickListener {
            onItemClickListener?.onItemClick(position)
        }

        holder.friendNickname.text = friendStatueList[position].nickname
        holder.statueName.text = friendStatueList[position].stoneName
        holder.date.text = friendStatueList[position].startDate.take(10)
        holder.dDay.text = friendStatueList[position].stoneDDay
        holder.achievement.text = friendStatueList[position].achieveRate.toString()+"%"
        holder.goal.text = friendStatueList[position].stoneGoal
        holder.heartNumber.text = friendStatueList[position].like.toString()


        updateHeartImage(holder.heart, friendStatueList[position].isLike)

        holder.heart.setOnClickListener {
            val call: Call<PressLike> = ServicePool.homeService.like("JSESSIONID="+LocalDataSource.getAccessToken().toString(),friendStatueList[position].stoneId)

            // 비동기적으로 요청 수행
            call.enqueue(object : Callback<PressLike> {
                override fun onResponse(call: Call<PressLike>, response: Response<PressLike>) {
                    if (response.isSuccessful) {
                        Log.d("홈 서버","서버 통신 성공")
                        friendStatueList[position].isLike = !friendStatueList[position].isLike
                        if(response.body()?.data?.isLike == true)
                            friendStatueList[position].like= friendStatueList[position].like+1
                        else
                            friendStatueList[position].like= friendStatueList[position].like-1
                        notifyItemChanged(position)
                    } else {
                        // 서버에서 오류 응답을 받은 경우 처리
                        Log.d("홈 서버","서버통신 오류")
                    }
                }

                override fun onFailure(call: Call<PressLike>, t: Throwable) {
                    // 통신 실패 처리
                    Log.d("홈 서버",t.message.toString())
                }
            })

        }

    }

    private fun updateHeartImage(imageView: ImageView, isLiked: Boolean) {
        if (isLiked) {
            imageView.setBackgroundResource(R.drawable.icon_heart_fill)
        } else {
            imageView.setBackgroundResource(R.drawable.icon_heart)
        }
    }

}