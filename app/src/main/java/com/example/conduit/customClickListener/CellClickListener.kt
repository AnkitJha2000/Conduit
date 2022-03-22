package com.example.conduit.customClickListener

interface CellClickListener {
    fun onFeedItemClickListener()
    fun onFeedFavoriteButtonClickListener(slug:String,isFavorited : Boolean)
    fun onFeedEditButtonClickListener()
}
