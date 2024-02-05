package com.umc.sculptor.ui.museum

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.umc.sculptor.databinding.FragmentMuseumSculptorBinding
import com.umc.sculptor.databinding.ItemMuseumProfileBinding

class MuseumSculptorRVAdapter(private val sculptorList: ArrayList<Sculptor>):
    RecyclerView.Adapter<MuseumSculptorRVAdapter.ViewHolder>() {
        interface MyItemClickListener{
            fun onItemClick(sculptor: Sculptor)

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
            ItemMuseumProfileBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }
    inner class ViewHolder(val binding: ItemMuseumProfileBinding)
        : RecyclerView.ViewHolder(binding.root){
        val sculptorDay=binding.museumSculptorDay
        val sculptorTitle=binding.museumSculptorTitle
        val sculptorDate=binding.museumSculptorDate
        val sculptorImage=binding.museumSculptorImg
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.sculptorDay.text = sculptorList[position].day
        holder.sculptorTitle.text = sculptorList[position].title
        holder.sculptorDate.text = sculptorList[position].date
        holder.sculptorImage.setImageResource(sculptorList[position].sculptorImg!!)
        holder.itemView.setOnClickListener{
            myItemClickListener.onItemClick(sculptorList[position])
        }
    }

    override fun getItemCount(): Int =sculptorList.size



}