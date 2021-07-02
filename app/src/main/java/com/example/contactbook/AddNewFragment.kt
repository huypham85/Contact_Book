package com.example.contactbook

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import androidx.navigation.Navigation
import com.example.contactbook.databinding.FragmentAddNewBinding

class AddNewFragment : Fragment() {
    private lateinit var binding: FragmentAddNewBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentAddNewBinding.inflate(inflater, container, false)
        val view = binding.root
        binding.btnAddNew.setOnClickListener{
            val userName: String = binding.edtName.text.toString().trim()
            val userPhone: String = binding.edtPhone.text.toString().trim()
            val userEmail: String = binding.edtEmail.text.toString().trim()
            val userFb: String = binding.edtFacebook.text.toString().trim()
            var userPhoto: Int = R.drawable.ic_baseline_person_24
            if(userName!=""&&userEmail!=""&&userPhone!=""&&userFb!="") {
                val bundle = Bundle()
                val userNew = UserData(userName, userPhone, userEmail, userFb, userPhoto)
                bundle.putSerializable("new_user", userNew)
                Navigation.findNavController(view).navigate(R.id.action_addNewFragment_to_contactFragment, bundle)
            }
            else{
                Toast.makeText(activity, "User's Information is not completed", Toast.LENGTH_LONG).show()
            }
        }
        binding.btnBackAddNew.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_addNewFragment_to_contactFragment)
        }
        arguments?.let {
            var newUserNumber = it.getString("new_user_number").toString()
            binding.edtPhone.setText(newUserNumber.trim())
        }
        return view
    }
}