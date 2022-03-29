package com.example.conduit.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.conduit.adapters.FeedAdapter
import com.example.conduit.customClickListener.CellClickListener
import com.example.conduit.databinding.FragmentFavoriteArtcilesBinding
import com.example.conduit.ui.MainActivity
import com.example.conduit.utils.Constants
import com.example.conduit.utils.Constants.FAVORITE_FEED
import com.example.conduit.viewModels.AuthViewModel
import com.example.conduit.viewModels.FeedViewModel
import okhttp3.internal.userAgent

class FavoriteArticlesFragment : Fragment(),CellClickListener {
    private lateinit var binding : FragmentFavoriteArtcilesBinding
    private lateinit var feedViewModel: FeedViewModel
    private lateinit var token : String
    private lateinit var adapter: FeedAdapter
    private lateinit var authViewModel: AuthViewModel
    private lateinit var currentUserName : String
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoriteArtcilesBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        token = (activity as MainActivity).token.toString()

        feedViewModel = (activity as MainActivity).feedViewModel
        authViewModel = (activity as MainActivity).authViewModel

        adapter = FeedAdapter(FAVORITE_FEED,this)

        binding.FavoriteFeedsFragmentRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.FavoriteFeedsFragmentRecyclerView.adapter = adapter

        authViewModel.currentUser.observe(requireActivity()){
            currentUserName = it.user.username
        }

        feedViewModel.getMyFavoriteArticles(token,currentUserName)

        feedViewModel.favoritedArticlesList.observe(requireActivity()){
            adapter.submitList(it.articles)
        }

        feedViewModel.favoritedListError.observe(requireActivity()){
            // Toast.makeText(requireActivity(), it , Toast.LENGTH_SHORT).show()
        }
    }

    override fun onFeedItemClickListener() {
        TODO("Not yet implemented")
    }

    override fun onFeedFavoriteButtonClickListener(slug: String, isFavorited: Boolean) {
        TODO("Not yet implemented")
    }

    override fun onFeedEditButtonClickListener() {
        TODO("Not yet implemented")
    }

}