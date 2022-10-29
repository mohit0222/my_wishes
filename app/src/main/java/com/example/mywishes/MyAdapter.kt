package com.example.mywishes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter

class MyAdapter(private val list: ArrayList<Model>) : RecyclerView.Adapter<MyAdapter.WishesViewHolder>() {
    class WishesViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val image: ImageView
        val name: TextView
        val detail: TextView

        init {
            image = ItemView.findViewById(R.id.img_list)
            name = ItemView.findViewById(R.id.txt_mohit)
            detail = ItemView.findViewById(R.id.txt_detail)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WishesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_home_list, parent, false)

        return WishesViewHolder(view)

    }

    override fun onBindViewHolder(holder: WishesViewHolder, position: Int) {
        holder.image.setImageResource(R.drawable.otp)
        holder.name.text = list[position].name
        holder.detail.text = list[position].detail

    }

    override fun getItemCount(): Int {
        return list.size
    }
}
