package com.umc.sculptor.ui.store

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.umc.sculptor.R
import com.umc.sculptor.data.model.remote.store.UserItem
import com.umc.sculptor.databinding.StoreItemWearingBinding

class StoneRVAdapter(userItemList: List<UserItem>): RecyclerView.Adapter<StoneRVAdapter.ViewHolder>() {

    private lateinit var viewModel: StoreViewModel
    var userItemList: List<UserItem> = userItemList
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

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): StoneRVAdapter.ViewHolder {
        val binding: StoreItemWearingBinding =
            StoreItemWearingBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup,false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StoneRVAdapter.ViewHolder, position: Int) {

        val item = userItemList[position]
        holder.bind(item)
        holder.itemView.setOnClickListener{
            myItemClickListener.onItemCLick(position)
            notifyDataSetChanged()
        }
    }

    override fun getItemCount(): Int = userItemList.size
    inner class ViewHolder(val binding: StoreItemWearingBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(userItem: UserItem){
            when(userItem.itemId){//stone이미지
                    "1e5b03b8-ca6a-11ee-a240-0208b3f73ebc" -> binding.itemUsingitemImg.setImageResource(R.drawable.stone_basic)
                    "39e97a1b-ca6a-11ee-a240-0208b3f73ebc" -> binding.itemUsingitemImg.setImageResource(R.drawable.stone_ruby)
                    "39f48564-ca6a-11ee-a240-0208b3f73ebc" -> binding.itemUsingitemImg.setImageResource(R.drawable.stone_sapphire)
                    "39ff7844-ca6a-11ee-a240-0208b3f73ebc" -> binding.itemUsingitemImg.setImageResource(R.drawable.stone_emerald)
                "3a09c2a1-ca6a-11ee-a240-0208b3f73ebc" -> binding.itemUsingitemImg.setImageResource(R.drawable.stone_metal)
                "3a144606-ca6a-11ee-a240-0208b3f73ebc" -> binding.itemUsingitemImg.setImageResource(R.drawable.stone_gold)
                "3a1f3498-ca6a-11ee-a240-0208b3f73ebc" -> binding.itemUsingitemImg.setImageResource(R.drawable.stone_silver)
                "3a2a4295-ca6a-11ee-a240-0208b3f73ebc" -> binding.itemUsingitemImg.setImageResource(R.drawable.stone_bronze)
                else -> binding.itemUsingitemImg.setImageResource(R.drawable.stone_basic)
            }
            if (userItem.isSelected==true) {
                binding.itemUsingbgImg.setImageResource(R.drawable.store_wearing_item_r_selected)
            } else {
                binding.itemUsingbgImg.setImageResource(R.drawable.store_wearingitem_r)
            }
            binding.howmuchTv.setText("${userItem.itemPrice}")
            userItem.isSelected = false
        }
    }
}