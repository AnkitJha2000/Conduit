package com.example.conduit.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.conduit.R
import com.example.conduit.adapters.FeedAdapter
import com.example.conduit.databinding.FragmentGlobalFeedBinding
import com.example.conduit.databinding.FragmentSplashScreenBinding
import com.example.conduit.ui.MainActivity
import com.example.conduit.utils.Constants.GLOBAL_FEED
import com.example.conduit.viewModels.FeedViewModel


class GlobalFeedFragment : Fragment() {

    private lateinit var binding: FragmentGlobalFeedBinding
    private lateinit var feedViewModel: FeedViewModel
    private lateinit var token : String
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

        val adapter = FeedAdapter(GLOBAL_FEED)

        binding.globalFeedRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.globalFeedRecyclerView.adapter = adapter

        feedViewModel.globalArticlesResponse.observe(requireActivity()){
            Log.d("articles",it.articles.toString())
            adapter.submitList(it.articles)
        }

    }


}