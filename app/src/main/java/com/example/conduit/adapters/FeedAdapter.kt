package com.example.conduit.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.conduit.R
import com.example.conduit.customClickListener.CellClickListener
import com.example.conduit.models.entities.Article
import com.example.conduit.utils.Constants

class FeedAdapter(val feedType : String,private val cellClickListener: CellClickListener) : RecyclerView.Adapter<FeedAdapter.FeedViewHolder>(){
    inner class FeedViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val itemTitle: TextView = itemView.findViewById(R.id.item_feed_title)
        val itemAuthor: TextView = itemView.findViewById(R.id.item_feed_author)
        val itemDetails: TextView = itemView.findViewById(R.id.item_feed_details)
        val itemTags: TextView = itemView.findViewById(R.id.item_feed_tags)
        val itemCreatedAt: TextView = itemView.findViewById(R.id.item_feed_createdAt)
        val itemFavoriteCount: TextView = itemView.findViewById(R.id.item_favorite_count)
        val itemFavoriteButton: ImageButton = itemView.findViewById(R.id.item_favorite_button)
        val itemProfileImage: ImageView = itemView.findViewById(R.id.item_feed_profile_image)
        val itemEditButton : Button = itemView.findViewById(R.id.item_feed_edit_btn)
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
        val feed = differ.currentList[position]

        holder.itemTitle.text = feed.title
        holder.itemAuthor.text = feed.author.username
        holder.itemCreatedAt.text = feed.createdAt
        holder.itemDetails.text = feed.description
        holder.itemFavoriteCount.text = feed.favoritesCount.toString()
        holder.itemTags.text = feed.tagList.joinToString(" , ")
        Glide.with(holder.itemProfileImage.context)
            .load(holder.itemProfileImage)
            .placeholder(R.drawable.ic_profile_face)
            .into(holder.itemProfileImage)

        if(feedType == Constants.MY_FEED)
        {
            holder.itemEditButton.visibility = View.VISIBLE
        }
        else
        {
            holder.itemEditButton.visibility = View.GONE
        }

        holder.itemView.setOnClickListener {
            cellClickListener.onFeedItemClickListener()
        }

        holder.itemEditButton.setOnClickListener {
            cellClickListener.onFeedEditButtonClickListener()
        }

        if(feed.favorited!!)
        {
            holder.itemFavoriteButton.setImageResource(R.drawable.baseline_favorite_black_24dp)
        }
        else{
            holder.itemFavoriteButton.setImageResource(R.drawable.baseline_favorite_border_black_24dp)
        }

        holder.itemFavoriteButton.setOnClickListener{
            cellClickListener.onFeedFavoriteButtonClickListener(feed.slug, feed.favorited)
        }

    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }



}