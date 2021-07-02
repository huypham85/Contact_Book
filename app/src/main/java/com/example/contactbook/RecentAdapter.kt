package com.example.contactbook


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import java.util.*
import kotlin.collections.ArrayList

class RecentAdapter(var recentUserList:ArrayList<UserData>, val getData:(UserData)->Unit): RecyclerView.Adapter<RecentAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecentAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.row_recent,parent,false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: RecentAdapter.ViewHolder, position: Int) {
        holder.itemName.text = recentUserList[position].name
        holder.itemPhone.text =recentUserList[position].phone
        holder.SendData(recentUserList[position])
    }

    override fun getItemCount(): Int {
        return recentUserList.size
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var itemName: TextView
        var itemPhone: TextView
        var moreInfo: ImageView
        init {
            itemName = itemView.findViewById(R.id.txtRecentName)
            itemPhone = itemView.findViewById(R.id.txtRecentPhone)
            moreInfo = itemView.findViewById(R.id.img_more_info)
        }
        fun SendData(userData: UserData){
            itemView.setOnClickListener{
                getData(userData)
            }
        }
    }

}