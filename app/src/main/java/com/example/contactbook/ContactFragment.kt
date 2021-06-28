package com.example.contactbook

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.*
import kotlin.collections.ArrayList

class ContactFragment: Fragment() {
    private lateinit var userList: MutableList<UserData>
    private lateinit var contactAdapter: RecyclerAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_contact,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        userList = (activity as MainActivity).mainListUser
        val recyclerView:RecyclerView= view.findViewById(R.id.rcv_contacts)
        recyclerView.layoutManager = LinearLayoutManager(activity)

        contactAdapter = RecyclerAdapter(userList as ArrayList<UserData>, {
            val bundle = Bundle()
            bundle.putSerializable("user_data", it)
            Log.d("Send data", it.toString())
            Navigation.findNavController(view).navigate(R.id.action_contactFragment_to_infoFragment,bundle) // truyen bundle theo khi chuyen fragment
        })
        recyclerView.adapter= contactAdapter

        //search view
        val searchView = view.findViewById<SearchView>(R.id.searchView)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchView.clearFocus()
                if(userList.contains(query)){
                    contactAdapter.getFilter().filter(query)
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                contactAdapter.getFilter().filter(newText)
                return true
            }

        })

        val btnAdd = view.findViewById<ImageButton>(R.id.btn_add)
        btnAdd.setOnClickListener{
            Navigation.findNavController(view).navigate(R.id.action_contactFragment_to_addNewFragment)
        }

        var newUser : UserData? = null
        arguments?.let {
            newUser = it.getSerializable("new_user") as? UserData
            Log.d("New User", newUser.toString())
        }

        newUser?.let {
            userList.add(it)
            userList.sortBy { it.name }
            contactAdapter.notifyDataSetChanged()
        }
    }

//    override fun onPause() {
//        super.onPause()
////        Log.e("from on pause ${index}", userList.size.toString())
//    }
//
//    override fun onDestroyView() {
//        super.onDestroyView()
////        Log.e("from on DestroyView ${index++}", userList.size.toString())
//    }
//
//    override fun onDestroy() {
//        super.onDestroy()
////        Log.e("from on Destroy ${index++}", userList.size.toString())
//    }
}