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
//            if (item.isSelected==true) {
//                binding.itemUsingbgImg.setImageResource(R.drawable.store_wearing_item_r_selected)
//            } else {
//                binding.itemUsingbgImg.setImageResource(R.drawable.store_wearingitem_r)
//            }
            binding.itemUsingbgCircle.setImageResource(R.drawable.store_checkimg)
            binding.howmuchTv.isInvisible
            binding.itemUsingitemImg.setImageResource(R.drawable.bell)
        }
    }
}