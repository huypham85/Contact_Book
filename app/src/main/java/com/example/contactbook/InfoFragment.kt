package com.example.contactbook

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
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
        var user : UserData? = null
        var recentUser : UserData? = null
//        arguments?.let {
//            user = it.getSerializable("user_data") as UserData
//            Log.d("User name", user.toString())
//            binding.txtNameInfo.text = user.name
//            binding.txtPhoneInfo.text = user.phone
//            binding.txtEmailInfo.text = user.email
//            binding.txtFbInfo.text = user.facebook
//            user.photo?.let { binding.imgInfo.setImageResource(it) }
//        }
//
//        arguments?.let {
//            recentUser = it.getSerializable("recent_user_data") as UserData
//            Log.d("Recent_User name", recentUser.toString())
//            binding.txtNameInfo.text = recentUser.name
//            binding.txtPhoneInfo.text = recentUser.phone
//            binding.txtEmailInfo.text = recentUser.email
//            binding.txtFbInfo.text = recentUser.facebook
//            recentUser.photo?.let { binding.imgInfo.setImageResource(it) }
//        }
        //arguments?.getBundle("user_data")
        val bundle = this.arguments
        if (bundle != null) {
            user = bundle.getSerializable("user_data") as? UserData
        }
        Log.d("User name", user.toString())
        if(user!=null){
            binding.txtNameInfo.text = user.name
            binding.txtPhoneInfo.text = user.phone
            binding.txtEmailInfo.text = user.email
            binding.txtFbInfo.text = user.facebook
            user.photo.let { binding.imgInfo.setImageResource(it) }
        }


        val bundle_recent = this.arguments
        if (bundle_recent != null) {
            recentUser = bundle_recent.getSerializable("recent_user_data") as? UserData
        }
        Log.d("recent User name", recentUser.toString())
        if(recentUser !=null){
            binding.txtNameInfo.text = recentUser.name
            binding.txtPhoneInfo.text = recentUser.phone
            binding.txtEmailInfo.text = recentUser.email
            binding.txtFbInfo.text = recentUser.facebook
            recentUser.photo.let { binding.imgInfo.setImageResource(recentUser.photo) }
        }

        val userName = binding.txtNameInfo.text.toString().trim()
        val userPhone = binding.txtPhoneInfo.text.toString().trim()
        val userEmail = binding.txtEmailInfo.text.toString().trim()
        val userFaceBook = binding.txtFbInfo.text.toString().trim()
        var userPhoto:ImageView?
        userPhoto = binding.imgInfo
        userPhoto?.let { userPhoto = binding.imgInfo }
        binding.imgPhoneInfo.setOnClickListener{
        //val recentUserData = UserData(userName,userPhone,userEmail,userFaceBook, userPhoto!!.id)
            Log.d("New Recent User", user.toString())
            if (user != null) {
                (activity as MainActivity).recentListUser.add(user)
            }
            val number = binding.txtPhoneInfo.text.toString().trim()
            val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+ Uri.encode(number)))
            startActivity(intent)
        }

        binding.imgEmailInfo.setOnClickListener {
            binding.webView.loadUrl("https://"+ userEmail)
        }

        binding.imgFbInfo.setOnClickListener {
            binding.webView.loadUrl("https://"+ userFaceBook)
        }
        return view
    }

}