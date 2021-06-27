package com.example.contactbook

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.navigation.Navigation
import com.example.contactbook.databinding.FragmentInfoBinding

class InfoFragment : Fragment() {
    private lateinit var binding: FragmentInfoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =  FragmentInfoBinding.inflate(inflater, container, false)
        val view = binding.root
        binding.btnBack.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_infoFragment_to_contactFragment)
        }
        var user : UserData = UserData("","","","",1)
        arguments?.let {
            user = it.getSerializable("user_data") as UserData
            Log.d("User name", user.toString())
        }
        binding.txtNameInfo.text = user.name
        binding.txtPhoneInfo.text = user.phone
        binding.txtEmailInfo.text = user.email
        binding.txtFbInfo.text = user.facebook
        user.photo?.let { binding.imgInfo.setImageResource(it) }
        return view
    }

}