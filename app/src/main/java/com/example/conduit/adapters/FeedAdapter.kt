package com.example.conduit.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.conduit.R
import com.example.conduit.models.entities.Article

class FeedAdapter : RecyclerView.Adapter<FeedAdapter.FeedViewHolder>(){
    inner class FeedViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

    }

    private val diffCallback = object : DiffUtil.ItemCallback<Article>(){
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.createdAt == newItem.createdAt
        }
    }

    private val differ = AsyncListDiffer(this,diffCallback)

    fun submitList(list: List<Article>) = differ.submitList(list)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        FeedViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.feed_single_item_row,parent,false))

    override fun onBindViewHolder(holder: FeedViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }


}