package com.mkrdeveloper.retrofitgetrecyclerviewexample.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mkrdeveloper.retrofitgetrecyclerviewexample.databinding.ItemLayoutBinding
import com.mkrdeveloper.retrofitgetrecyclerviewexample.models.UsersItem

class RvAdapter (private var userList: List<UsersItem>): RecyclerView.Adapter<RvAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
    }

    // item click listener
    lateinit var mData: ArrayList<UsersItem>
    lateinit var mListener: onItemClickListener


    interface onItemClickListener {
        fun onItemClick(position: Int)
    }


    fun setOnItemClickListener(
        listener: onItemClickListener,
        data: ArrayList<UsersItem>
    ) {
        mListener = listener
        mData = data
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        )
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    // method for filtering our recyclerview items.
    fun filterList(filterlist: List<UsersItem>) {
        userList = filterlist
        notifyDataSetChanged()
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = userList[position]


        holder.binding.apply{
            tvId.text = "Id: ${currentItem.id}"
            tvUserId.text = "User Id: ${currentItem.userId}"
            tvTitle.text = "Title: ${currentItem.title}"
            tvBody.text = "Body: ${currentItem.body}"
        }


    }

}