package com.example.conduit.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.conduit.R
import com.example.conduit.adapters.FeedAdapter
import com.example.conduit.customClickListener.CellClickListener
import com.example.conduit.databinding.FragmentFeedBinding
import com.example.conduit.ui.MainActivity
import com.example.conduit.utils.Constants
import com.example.conduit.viewModels.FeedViewModel

class FeedFragment : Fragment(),CellClickListener {
    private lateinit var feedViewModel: FeedViewModel
    private lateinit var token : String
    private lateinit var adapter: FeedAdapter
    private lateinit var binding : FragmentFeedBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFeedBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        token = (activity as MainActivity).token.toString()

        feedViewModel = (activity as MainActivity).feedViewModel

        adapter = FeedAdapter(Constants.FOLLOWED_FEED,this)

        binding.myFeedRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.myFeedRecyclerView.adapter = adapter

        feedViewModel.myFeedList.observe(requireActivity()){
            Log.d("articles",it.articles.toString())
            adapter.submitList(it.articles)
        }

        feedViewModel.myFeedListError.observe(requireActivity()){
            if(it != null)
            {
                Toast.makeText(requireActivity(), it , Toast.LENGTH_SHORT).show()
            }
        }

        feedViewModel.favoritedError.observe(requireActivity()){
            Toast.makeText(requireActivity(), it.toString(), Toast.LENGTH_SHORT).show()
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