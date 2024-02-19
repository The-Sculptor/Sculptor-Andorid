package com.umc.sculptor.ui.store

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.view.isGone
import androidx.recyclerview.widget.RecyclerView
import com.umc.sculptor.R
import com.umc.sculptor.data.model.remote.store.Item
import com.umc.sculptor.data.model.remote.store.ItemX
import com.umc.sculptor.databinding.StoreWearinglistItemBinding

class ItemListRVAdapter(itemList: List<ItemX>) : RecyclerView.Adapter<ItemListRVAdapter.ViewHolder>() {

    var itemList: List<ItemX> = itemList
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

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ItemListRVAdapter.ViewHolder {
        val binding = StoreWearinglistItemBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemListRVAdapter.ViewHolder, position: Int) {
        val item = itemList[position]
        holder.bind(item)
        holder.itemView.setOnClickListener {
            myItemClickListener.onItemCLick(position)
            notifyDataSetChanged()
            // 클릭에 따라 아이콘 업데이트
            //item.isChecked = !item.isChecked
        }
    }


    override fun getItemCount(): Int = itemList.size
    inner class ViewHolder(val binding: StoreWearinglistItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ItemX) {
            when(item.id){//stone이미지
                "1e5b03b8-ca6a-11ee-a240-0208b3f73ebc" -> binding.wearingListItemImg.setImageResource(R.drawable.stone_basic)
                "39e97a1b-ca6a-11ee-a240-0208b3f73ebc" -> binding.wearingListItemImg.setImageResource(R.drawable.stone_ruby)
                "39f48564-ca6a-11ee-a240-0208b3f73ebc" -> binding.wearingListItemImg.setImageResource(R.drawable.stone_sapphire)
                "39ff7844-ca6a-11ee-a240-0208b3f73ebc" -> binding.wearingListItemImg.setImageResource(R.drawable.stone_emerald)
                "3a09c2a1-ca6a-11ee-a240-0208b3f73ebc" -> binding.wearingListItemImg.setImageResource(R.drawable.stone_metal)
                "3a144606-ca6a-11ee-a240-0208b3f73ebc" -> binding.wearingListItemImg.setImageResource(R.drawable.stone_gold)
                "3a1f3498-ca6a-11ee-a240-0208b3f73ebc" -> binding.wearingListItemImg.setImageResource(R.drawable.stone_silver)
                "3a2a4295-ca6a-11ee-a240-0208b3f73ebc" -> binding.wearingListItemImg.setImageResource(R.drawable.stone_bronze)
                else -> binding.wearingListItemImg.setImageResource(R.drawable.stone_basic)
            }

            Log.d("ispurchased", "${item.isPurchased}")
            if(item.isPurchased==true){
                binding.wearingListItemGTv.text = "보유중"
            }else{
                binding.wearingListItemGTv.text = "${item.price}g"
            }
            if(item.isChecked==true){
                binding.wearingListCheckbtn.setImageResource(R.drawable.icon_solid_check)
            }else{
                binding.wearingListCheckbtn.setImageResource(R.drawable.icon_outline_check)
            }
        }

    }
}