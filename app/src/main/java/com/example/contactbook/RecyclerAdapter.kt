package com.example.contactbook

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import java.util.*
import kotlin.collections.ArrayList

class RecyclerAdapter(var userList:ArrayList<UserData>, val getData:(UserData)->Unit): RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {
    val initUserDataList = ArrayList<UserData>().apply {
        userList.addAll(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.row_contact,parent,false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: RecyclerAdapter.ViewHolder, position: Int) {
        holder.itemName.text = userList[position].name
        holder.itemPhone.text =userList[position].phone
        userList[position].photo?.let { holder.itemImage.setImageResource(it) }
        holder.SendData(userList[position])
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    fun getFilter(): Filter{
        return userFilter
    }

    private val userFilter =object : Filter(){
        override fun performFiltering(constraint: CharSequence?): FilterResults {
            val filteredList: ArrayList<UserData> = ArrayList()
            if(constraint == null || constraint.isEmpty()){
                initUserDataList.let{filteredList.addAll(it)}
            }
            else{
                val query = constraint.toString().trim().toLowerCase()
                initUserDataList.forEach{
                    if(it.name.toLowerCase(Locale.ROOT).contains(query)){
                        filteredList.add(it)
                    }
                }
            }
            val results = FilterResults()
            results.values = filteredList
            return results
        }

        override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
            if(results?.values is ArrayList<*>){
                userList.clear()
                userList.addAll(results.values as ArrayList<UserData>)
                notifyDataSetChanged()
            }
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var itemImage: ImageView
        var itemName: TextView
        var itemPhone: TextView
        init {
            itemImage = itemView.findViewById(R.id.imgContact)
            itemName = itemView.findViewById(R.id.txtContactName)
            itemPhone = itemView.findViewById(R.id.txtContactPhone)
//            itemView.setOnClickListener{
//                Navigation.findNavController(it).navigate(R.id.action_contactFragment_to_infoFragment)
//            }
        }
        fun SendData(userData: UserData){
            itemView.setOnClickListener{
                getData(userData)
            }
        }
    }
}