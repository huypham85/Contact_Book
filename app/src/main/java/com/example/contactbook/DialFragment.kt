package com.example.contactbook

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.contactbook.databinding.FragmentDialBinding
import com.example.contactbook.databinding.FragmentRecentBinding

class DialFragment: Fragment() {
    private lateinit var binding: FragmentDialBinding
    //var phoneNumber: String = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDialBinding.inflate(inflater,container,false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var phoneNumber = binding.edtNumber
        binding.btn1.setOnClickListener {
            phoneNumber.setText(phoneNumber.text.toString()+"1")
        }
        binding.btn2.setOnClickListener {
            phoneNumber.setText(phoneNumber.text.toString()+"2")
        }
        binding.btn3.setOnClickListener {
            phoneNumber.setText(phoneNumber.text.toString()+"3")
        }
        binding.btn4.setOnClickListener {
            phoneNumber.setText(phoneNumber.text.toString()+"4")
        }
        binding.btn5.setOnClickListener {
            phoneNumber.setText(phoneNumber.text.toString()+"5")
        }
        binding.btn6.setOnClickListener {
            phoneNumber.setText(phoneNumber.text.toString()+"6")
        }
        binding.btn7.setOnClickListener {
            phoneNumber.setText(phoneNumber.text.toString()+"7")
        }
        binding.btn8.setOnClickListener {
            phoneNumber.setText(phoneNumber.text.toString()+"8")
        }
        binding.btn9.setOnClickListener {
            phoneNumber.setText(phoneNumber.text.toString()+"9")
        }
        binding.btn0.setOnClickListener {
            phoneNumber.setText(phoneNumber.text.toString()+"0")
        }
        binding.btnStar.setOnClickListener {
            phoneNumber.setText(phoneNumber.text.toString()+"*")
        }
        binding.btnFly.setOnClickListener {
            phoneNumber.setText(phoneNumber.text.toString()+"#")

        }
        binding.btnDelete.setOnClickListener {
            phoneNumber.setText(phoneNumber.text.toString().dropLast(1))
        }

        // add this phone just dialed
        binding.btnCall.setOnClickListener {
            var number = phoneNumber.text.toString().trim()
            val newRecentUser = UserData("Unnamed User",number,"","",R.drawable.ic_baseline_person_24)
            Log.d("new Recent User Dial",newRecentUser.toString())
            (activity as MainActivity).recentListUser.add(newRecentUser)
            val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+ Uri.encode(number)))
            startActivity(intent)
        }

        binding.txtAddNumber.setOnClickListener {
            val bundle = Bundle()
            if(phoneNumber.text.toString()!=""){
                bundle.putString("new_user_number",phoneNumber.text.toString().trim())
                Navigation.findNavController(view).navigate(R.id.action_dialFragment_to_addNewFragment,bundle)
            }
        }
    }
}