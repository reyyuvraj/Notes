package com.example.notes.view.fragment

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.notes.R
import com.example.notes.model.User
import com.example.notes.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_add.view.*


class AddFragment : Fragment() {

    private lateinit var yUserViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.fragment_add, container, false)

        yUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        view.addUser.setOnClickListener {
            insertDataToDatabase()
        }

        return view
    }

    private fun insertDataToDatabase() {
        val firstName = firstName.text.toString()
        val lastName = lastName.text.toString()
        val age = age.text

        if (inputCheck(firstName, lastName, age)){
            val user = User(0, firstName, lastName, Integer.parseInt(age.toString()))
            yUserViewModel.addUser(user)
            Toast.makeText(requireContext(), "Successfully added", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        } else {
            Toast.makeText(requireContext(), "Enter data in all fields.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun inputCheck(firsName: String, lastName: String, age: Editable): Boolean {
        return !(TextUtils.isEmpty(firsName) || TextUtils.isEmpty(lastName) || TextUtils.isEmpty(age))
    }
}