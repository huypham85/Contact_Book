package com.example.contactbook

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.contactbook.databinding.FragmentContactBinding
import com.example.contactbook.databinding.FragmentRecentBinding

class RecentFragment: Fragment() {
    private lateinit var recentAdapter: RecentAdapter
    private lateinit var binding: FragmentRecentBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRecentBinding.inflate(inflater,container,false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView: RecyclerView = binding.rcvRecent
        recyclerView.layoutManager = LinearLayoutManager(activity).apply {
            stackFromEnd = true
            reverseLayout = true
        }

        recentAdapter = RecentAdapter((activity as MainActivity).recentListUser, {
            val bundle = Bundle()
            bundle.putSerializable("recent_user_data", it)
            Log.d("Send data", it.toString())
            Navigation.findNavController(view).navigate(R.id.action_recentFragment_to_infoFragment,bundle) // truyen bundle theo khi chuyen fragment
        })
        recyclerView.adapter= recentAdapter

//        var newRecentUser : UserData? = null
//        arguments?.let {
//            newRecentUser = it.getSerializable("new_user") as? UserData
//            Log.d("New Recent User", newRecentUser.toString())
//        }
//
//        newRecentUser?.let {
//            (activity as MainActivity).recentListUser.add(it)
//            recentAdapter.notifyDataSetChanged()
//        }
    }
}