package com.example.notes.view.fragment

import android.app.AlertDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.notes.R
import com.example.notes.model.User
import com.example.notes.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.fragment_update.*
import kotlinx.android.synthetic.main.fragment_update.view.*

class UpdateFragment : Fragment() {

    private lateinit var yUserViewModel: UserViewModel
    private val args by navArgs<UpdateFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_update, container, false)

        yUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        view.updateFirstName.setText(args.currentUserDetails.firstName)
        view.updateLastName.setText(args.currentUserDetails.lastName)
        view.updateAge.setText(args.currentUserDetails.age.toString())

        view.updateUser.setOnClickListener {
            updateItem()
        }

        setHasOptionsMenu(true)

        return view
    }

    private fun updateItem() {
        val firstName = updateFirstName.text.toString()
        val lastName = updateLastName.text.toString()
        val age = Integer.parseInt(updateAge.text.toString())

        if (inputCheck(firstName, lastName, updateAge.text)) {
            val updatedUser = User(args.currentUserDetails.id, firstName, lastName, age)
            yUserViewModel.updateUser(updatedUser)
            Toast.makeText(requireContext(), "Successfully Updated", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        } else {
            Toast.makeText(requireContext(), "Please fill out all details.", Toast.LENGTH_SHORT)
                .show()
        }


    }

    private fun inputCheck(firsName: String, lastName: String, age: Editable): Boolean {
        return !(TextUtils.isEmpty(firsName) || TextUtils.isEmpty(lastName) || TextUtils.isEmpty(age))
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menuDelete) {
            deleteUser()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteUser() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes") { _, _ ->
            yUserViewModel.deleteUser(args.currentUserDetails)
            Toast.makeText(
                requireContext(),
                "Successfully removed ${args.currentUserDetails.firstName}",
                Toast.LENGTH_SHORT
            ).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }
        builder.setNegativeButton("No") { _, _ ->

        }
        builder.setTitle("Delete ${args.currentUserDetails.firstName}?")
        builder.setMessage("Are you sure ${args.currentUserDetails.firstName}?")
        builder.create().show()
    }
}