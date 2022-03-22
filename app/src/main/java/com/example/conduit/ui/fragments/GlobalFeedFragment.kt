package com.example.conduit.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.conduit.R
import com.example.conduit.adapters.FeedAdapter
import com.example.conduit.customClickListener.CellClickListener
import com.example.conduit.databinding.FragmentGlobalFeedBinding
import com.example.conduit.databinding.FragmentSplashScreenBinding
import com.example.conduit.ui.MainActivity
import com.example.conduit.utils.Constants.GLOBAL_FEED
import com.example.conduit.viewModels.FeedViewModel


class GlobalFeedFragment : Fragment(),CellClickListener {

    private lateinit var binding: FragmentGlobalFeedBinding
    private lateinit var feedViewModel: FeedViewModel
    private lateinit var token : String
    private lateinit var adapter: FeedAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGlobalFeedBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        token = (activity as MainActivity).token.toString()

        feedViewModel = (activity as MainActivity).feedViewModel

        adapter = FeedAdapter(GLOBAL_FEED,this)

        binding.globalFeedRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.globalFeedRecyclerView.adapter = adapter

        feedViewModel.globalArticlesResponse.observe(requireActivity()){
            Log.d("articles",it.articles.toString())
            adapter.submitList(it.articles)
        }

        feedViewModel.feedError.observe(requireActivity()){
            if(it != null)
            {
                Toast.makeText(requireActivity(), it , Toast.LENGTH_SHORT).show()
            }
        }

        feedViewModel.favoritedError.observe(requireActivity()){
            Toast.makeText(requireActivity(), it.toString(), Toast.LENGTH_SHORT).show()
        }

    }

    private fun updateArticles(){
        feedViewModel.getArticles("Token $token")
        feedViewModel.globalArticlesResponse.observe(requireActivity()){
            Log.d("articlesGlobalFeed",it.articles.toString())
            adapter.submitList(it.articles)
        }
    }

    override fun onFeedItemClickListener() {
        Toast.makeText(requireActivity(), "itemClicked", Toast.LENGTH_SHORT).show()
    }

    override fun onFeedFavoriteButtonClickListener(slug : String,isFavorited : Boolean) {
        if(isFavorited)
        {
            feedViewModel.unfavoritedArticle("Token $token",slug)
        }
        else
        {
            feedViewModel.favoriteArticle("Token $token",slug)
        }
        updateArticles()
    }

    override fun onFeedEditButtonClickListener() {
        Toast.makeText(requireActivity(), "editClicked", Toast.LENGTH_SHORT).show()
    }

}