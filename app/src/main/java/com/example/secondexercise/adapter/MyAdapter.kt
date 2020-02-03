package com.example.secondexercise.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.secondexercise.R
import com.example.secondexercise.model.DetailedData
import com.squareup.picasso.Picasso

class MyAdapter(
    private val mUsers: MutableList<DetailedData>
) : RecyclerView.Adapter<MyAdapter.UserListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_list, parent, false)
        return UserListViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserListViewHolder, position: Int) {

        val data = mUsers[position]
        holder.bindData(data)
    }

    override fun getItemCount(): Int {
        return mUsers.size
    }

    class UserListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val userTextView = itemView.findViewById<TextView>(R.id.username)
        private val avatarImgView = itemView.findViewById<ImageView>(R.id.avatar_image)

        fun bindData(data: DetailedData) {
            userTextView.text = data.login

            Picasso.get()
                .load(data.avatarUrl)
                .into(avatarImgView)
        }
    }
}



