package com.example.contactbook

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ContactFragment: Fragment() {
    private lateinit var userList:ArrayList<UserData>
    private var contactAdapter: RecyclerView.Adapter<RecyclerAdapter.ViewHolder>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        userList= ArrayList()
        contactAdapter = RecyclerAdapter(userList)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view: View = inflater.inflate(R.layout.fragment_contact,container,false)
        val recyclerView:RecyclerView= view.findViewById(R.id.rcv_contacts)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter= contactAdapter
        userList.add(UserData("Mr Thanh", "0987", "alo@123", "fb.com", R.drawable.anh_thanh ))
        contactAdapter?.notifyDataSetChanged()
        val btnAdd = view.findViewById<ImageButton>(R.id.btn_add)
        btnAdd.setOnClickListener{
            Navigation.findNavController(view).navigate(R.id.action_contactFragment_to_addNewFragment)
        }
        return view
    }
}