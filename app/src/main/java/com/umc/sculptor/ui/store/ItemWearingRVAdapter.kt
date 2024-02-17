package com.umc.sculptor.ui.store

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.umc.sculptor.R
import com.umc.sculptor.data.model.remote.store.Item
import com.umc.sculptor.data.model.remote.store.Stone
import com.umc.sculptor.databinding.StoreItemWearingBinding

class ItemWearingRVAdapter(itemList: List<Item>): RecyclerView.Adapter<ItemWearingRVAdapter.ViewHolder>() {

    var itemList: List<Item> = itemList
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
        fun bind(item: Item){
            binding.itemUsingbgImg.setImageResource(R.drawable.store_wearingitem_r)
            binding.howmuchTv.setText("${item.itemPrice}")
            //binding.itemUsingitemImg.setImageResource(item.ItemImg!!)
        }
    }
}