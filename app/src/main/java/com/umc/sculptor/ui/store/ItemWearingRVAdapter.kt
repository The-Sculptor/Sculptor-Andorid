package com.umc.sculptor.ui.store

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isInvisible
import androidx.recyclerview.widget.RecyclerView
import com.umc.sculptor.R
import com.umc.sculptor.data.model.remote.store.DataXXXX
import com.umc.sculptor.data.model.remote.store.ItemXXX
import com.umc.sculptor.data.model.remote.store.StoneItemX
import com.umc.sculptor.databinding.StoreItemWearingBinding

class ItemWearingRVAdapter(itemList: ArrayList<DataXXXX>): RecyclerView.Adapter<ItemWearingRVAdapter.ViewHolder>() {

    var itemList: ArrayList<DataXXXX> = itemList
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

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ItemWearingRVAdapter.ViewHolder {
        val binding: StoreItemWearingBinding =
            StoreItemWearingBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup,false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemWearingRVAdapter.ViewHolder, position: Int) {

        val item = itemList[position]
        holder.bind(item)
        holder.itemView.setOnClickListener{
            myItemClickListener.onItemCLick(position)
            notifyDataSetChanged()
        }
    }

    override fun getItemCount(): Int = itemList.size
    inner class ViewHolder(val binding: StoreItemWearingBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(item: DataXXXX){
            when(item.itemId){//stone이미지
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
            binding.itemUsingbgCircle.setImageResource(R.drawable.store_checkimg)
            binding.howmuchTv.text=""
        }
    }
}