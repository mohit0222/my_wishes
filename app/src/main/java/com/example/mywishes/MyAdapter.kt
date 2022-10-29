package com.example.mywishes

import android.view.Display.Mode
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder

const val Wishes = 1
const val TypePost = 2
class MyAdapter(private val list: ArrayList<Any>) : RecyclerView.Adapter<ViewHolder>() {

    class WishesViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val image: ImageView
        val name: TextView
        val detail: TextView

        init {
            image = ItemView.findViewById(R.id.img_list)
            name = ItemView.findViewById(R.id.txt_mohit)
            detail = ItemView.findViewById(R.id.txt_detail)
        }

        fun bindData(data: Model){
            image.setImageResource(R.drawable.otp)
            name.text = data.name
            detail.text = data.detail
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        if ( viewType == Wishes){
            val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_home_list, parent, false)
            return WishesViewHolder(view)
        }else{
            val view = LayoutInflater.from(parent.context).inflate(R.layout.friend_detail_post, parent, false)
            return PostWishesViewHolder(view)
        }
    }

    override fun getItemViewType(position: Int): Int {
        val data = list.get(position)
        when(data){
            is Model -> {
                return Wishes
            }
            is Post -> {
                return TypePost
            }
        }
        return -1
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data  = list.get(position)
        if (data is Model){
            (holder as WishesViewHolder).bindData(data)
        }else if (data is Post){
            (holder as PostWishesViewHolder).bindData(data)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }


    class PostWishesViewHolder(itemView: View): ViewHolder(itemView){


        fun bindData(data: Post){

        }
    }
}
