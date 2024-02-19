package com.umc.sculptor.ui.store

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.umc.sculptor.R
import com.umc.sculptor.data.model.remote.store.Stone
import com.umc.sculptor.databinding.StoreItemStatueBinding


class ItemRVAdapter(itemList: List<Stone>):RecyclerView.Adapter<ItemRVAdapter.ViewHolder>() {

    private lateinit var viewModel: StoreViewModel
    var itemList: List<Stone> = itemList
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    interface MyItemClickListener{
        fun onItemCLick(position: Int)
    }
    private lateinit var myItemClickListener: MyItemClickListener
    fun setMyItemClickListener(itemClickListener : MyItemClickListener){
        myItemClickListener = itemClickListener
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val binding: StoreItemStatueBinding =
            StoreItemStatueBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup,false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemRVAdapter.ViewHolder, position: Int) {
        val stone = itemList[position]
        holder.bind(stone)

        holder.itemView.setOnClickListener {
            myItemClickListener.onItemCLick(position)
        }
    }

    override fun getItemCount(): Int = itemList.size
    inner class ViewHolder(val binding: StoreItemStatueBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(stone: Stone){
            binding.statueNameTv.text = stone.name
            if (stone.isSelected==true) {
                binding.itemStatueBackImg.setImageResource(R.drawable.store_space_picked)
            } else {
                binding.itemStatueBackImg.setImageResource(R.drawable.store_space)
            }
            binding.itemStatueImg.setImageResource(changeImg(stone))
        }
    }

    fun changeImg(stone: Stone): Int {
        return when (stone.category) {
            "WORKOUT" -> R.drawable.work_b
            "STUDY" -> R.drawable.study_b
            "DAILY" -> R.drawable.daily_b
            else -> R.drawable.work_b //기본 이미지
        }
    }

}