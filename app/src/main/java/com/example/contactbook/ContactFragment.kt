package com.example.contactbook

import android.os.Binder
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
import com.example.contactbook.databinding.FragmentContactBinding
import java.util.*
import kotlin.collections.ArrayList

class ContactFragment: Fragment() {
    //private lateinit var userList: ArrayList<UserData>
    private lateinit var contactAdapter: RecyclerAdapter
    private lateinit var binding : FragmentContactBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = FragmentContactBinding.inflate(inflater,container,false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //userList = (activity as MainActivity).mainListUser
        val recyclerView:RecyclerView= binding.rcvContacts
        recyclerView.layoutManager = LinearLayoutManager(activity)

        contactAdapter = RecyclerAdapter((activity as MainActivity).mainListUser, {
            val bundle = Bundle()
            bundle.putSerializable("user_data", it)
            Log.d("Send data", it.toString())
            Navigation.findNavController(view).navigate(R.id.action_contactFragment_to_infoFragment,bundle) // truyen bundle theo khi chuyen fragment
        })
        recyclerView.adapter= contactAdapter
        Log.d("User List init",(activity as MainActivity).mainListUser.size.toString())


        val btnAdd = binding.btnAdd
        btnAdd.setOnClickListener{
            Navigation.findNavController(view).navigate(R.id.action_contactFragment_to_addNewFragment)
        }

        var newUser : UserData? = null
        arguments?.let {
            newUser = it.getSerializable("new_user") as? UserData
            Log.d("New User", newUser.toString())
        }

        newUser?.let {
            (activity as MainActivity).mainListUser.add(it)
            (activity as MainActivity).mainListUser.sortBy { it.name }
            contactAdapter.notifyDataSetChanged()
        }

        //search view
        val searchView = binding.searchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener, androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                contactAdapter.getFilter().filter(query)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                contactAdapter.getFilter().filter(newText)
                return true
            }

        })

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