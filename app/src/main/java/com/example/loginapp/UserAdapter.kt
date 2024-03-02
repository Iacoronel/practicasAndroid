package com.example.loginapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class UserAdapter (private val list: List<User>): RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_user_viewholder, parent,false)
        return UserViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.tvUserId.text = list[position].id.toString()
        holder.tvUserName.text = list[position].username
        holder.tvUserEmail.text = list[position].email

    }

    inner class UserViewHolder(view: View): RecyclerView.ViewHolder(view){
        lateinit var tvUserId: TextView
        lateinit var tvUserName: TextView
        lateinit var tvUserEmail: TextView

        init {
            tvUserId = view.findViewById(R.id.tv_value_id)
            tvUserName = view.findViewById(R.id.tv_value_username)
            tvUserEmail = view.findViewById(R.id.tv_value_email)


        }
    }

}
