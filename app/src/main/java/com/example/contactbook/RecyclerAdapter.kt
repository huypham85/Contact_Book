package com.example.contactbook

import android.app.AlertDialog
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.internal.ContextUtils.getActivity
import java.util.*
import kotlin.collections.ArrayList

class RecyclerAdapter(var userList:ArrayList<UserData>, val getData:(UserData)->Unit): RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {
    val initUserDataList = ArrayList<UserData>().apply {
        userList?.let{
            addAll(it)
        }
    }
    init {
        Log.d("Init List", initUserDataList.size.toString())
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
                Log.d("Result", initUserDataList.size.toString())
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
        var menuMore : ImageView
        init {
            itemImage = itemView.findViewById(R.id.imgContact)
            itemName = itemView.findViewById(R.id.txtContactName)
            itemPhone = itemView.findViewById(R.id.txtContactPhone)
            menuMore = itemView.findViewById(R.id.img_more_menu)
            menuMore.setOnClickListener { popUpMenu(it) }
        }

        // pop up menu to edit or delete items
        private fun popUpMenu(v: View) {
            val position = userList[adapterPosition]
            val popupMenu = PopupMenu(v.context,v)
            popupMenu.inflate(R.menu.pop_up_menu)
            popupMenu.setOnMenuItemClickListener {
                when(it.itemId){
                    R.id.editOption->{
                        val view = LayoutInflater.from(v.context).inflate(R.layout.edit_info,null)
                        val newName = view.findViewById<EditText>(R.id.edt_UpdateName)
                        val newPhone = view.findViewById<EditText>(R.id.edt_UpdatePhone)
                        val newEmail = view.findViewById<EditText>(R.id.edt_UpdateEmail)
                        val newFacebook = view.findViewById<EditText>(R.id.edt_UpdateFacebook)
                        AlertDialog.Builder(v.context)
                                .setView(view)
                                .setPositiveButton("OK"){
                                    dialog,_->
                                    if(newName.text.toString().trim()!="") position.name = newName.text.toString().trim()
                                    if(newPhone.text.toString().trim()!="") position.phone = newPhone.text.toString().trim()
                                    if(newEmail.text.toString().trim()!="") position.email = newEmail.text.toString().trim()
                                    if(newPhone.text.toString().trim()!="") position.facebook = newFacebook.text.toString().trim()
                                    notifyDataSetChanged()
                                    Toast.makeText(v.context,"User's Information is Edited",Toast.LENGTH_SHORT).show()
                                    dialog.dismiss()

                                }
                                .setNegativeButton("Cancel"){
                                    dialog,_->
                                    dialog.dismiss()
                                }
                                .create()
                                .show()
                        true
                    }
                    R.id.deleteOption->{
                        AlertDialog.Builder(v.context)
                        .setTitle("Delete")
                                .setIcon(R.drawable.ic_baseline_warning_24)
                                .setMessage("Are you sure to delete this Information")
                                .setPositiveButton("Yes"){
                                    dialog,_->
                                    userList.removeAt(adapterPosition)
                                    notifyDataSetChanged()
                                    Toast.makeText(v.context,"Deleted this Information",Toast.LENGTH_SHORT).show()
                                    dialog.dismiss()
                                }
                                .setNegativeButton("No"){
                                    dialog,_->
                                    dialog.dismiss()
                                }
                                .create()
                                .show()

                        true
                    }
                    else -> true
                }
            }
            popupMenu.show()
            // show icon of items in menu
            val popUp = PopupMenu::class.java.getDeclaredField("mPopup")
            popUp.isAccessible = true
            val menu = popUp.get(popupMenu)
            menu.javaClass.getDeclaredMethod("setForceShowIcon",Boolean::class.java)
                    .invoke(menu,true)
        }

        fun SendData(userData: UserData){
            itemView.setOnClickListener{
                getData(userData)
            }
        }
    }
}