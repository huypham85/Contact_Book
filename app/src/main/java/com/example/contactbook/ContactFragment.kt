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

class ContactFragment: Fragment() {
    private val userList:ArrayList<UserData> = ArrayList()
    private var contactAdapter: RecyclerView.Adapter<RecyclerAdapter.ViewHolder>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        userList.add(UserData("Mr Thanh", "0987", "alo@123", "fb.com", R.drawable.anh_thanh ))
        userList.add(UserData("Mr Kiet", "09871", "alo@1234", "fb.com", R.drawable.anh_kiet ))
        userList.add(UserData("Ms Mai", "09872", "alo@1235", "fb.com", R.drawable.chi_mai ))
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

        contactAdapter = RecyclerAdapter(userList, {
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
                    (contactAdapter as RecyclerAdapter).getFilter().filter(query)
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                (contactAdapter as RecyclerAdapter).getFilter().filter(newText)
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
            userList.add(newUser!!)
            contactAdapter?.notifyDataSetChanged()
        }
        Log.e("User List size : ", userList.size.toString())
        return view
    }
}