package com.example.notes.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.notes.R
import com.example.notes.data.User
import kotlinx.android.synthetic.main.fragment_add.view.*
import kotlinx.android.synthetic.main.list_item.view.*

class ListAdapter : RecyclerView.Adapter<ListAdapter.ViewHolder>() {

    private var userList = emptyList<User>()

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = userList[position]

        holder.itemView.forID.text = currentItem.id.toString()
        holder.itemView.forFirstName.text = currentItem.firstName
        holder.itemView.forLastName.text = currentItem.lastName
        holder.itemView.forAge.text = currentItem.age.toString()

    }

    override fun getItemCount(): Int {
        return userList.size
    }

    fun setData(user: List<User>){
        this.userList = user
        notifyDataSetChanged()
    }
}