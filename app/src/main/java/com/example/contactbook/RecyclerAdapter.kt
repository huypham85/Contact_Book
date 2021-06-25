package com.example.contactbook

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerAdapter(var userList:ArrayList<UserData>): RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.row_contact,parent,false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: RecyclerAdapter.ViewHolder, position: Int) {
        holder.itemName.text = userList[position].name
        holder.itemPhone.text =userList[position].phone
        userList[position].photo?.let { holder.itemImage.setImageResource(it) }
    }

    override fun getItemCount(): Int {
        return userList.size
    }
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var itemImage: ImageView
        var itemName: TextView
        var itemPhone: TextView
        init {
            itemImage = itemView.findViewById(R.id.imgContact)
            itemName = itemView.findViewById(R.id.txtContactName)
            itemPhone = itemView.findViewById(R.id.txtContactPhone)
        }
    }
}