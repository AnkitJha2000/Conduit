package com.example.conduit.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.conduit.databinding.FragmentAddFeedBinding
import com.example.conduit.models.requests.ArticleCreds
import com.example.conduit.models.requests.UpsertArticleRequest
import com.example.conduit.ui.MainActivity
import com.example.conduit.viewModels.FeedViewModel

class AddFeedFragment : Fragment() {

    private lateinit var binding : FragmentAddFeedBinding
    private lateinit var feedViewModel: FeedViewModel
    private var token : String? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddFeedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        feedViewModel = (activity as MainActivity).feedViewModel
        token = (activity as MainActivity).token

        binding.addArticleBtn.setOnClickListener {
            binding.apply {
                val title = addArticleTitleET.text.toString()
                val desc = addArticleDescriptionET.text.toString()
                val body = addArticleBodyET.text.toString()
                val tags = addArticleTagsET.text.toString()
                val tagList = tags.split(",").toList()
                createArticle(title,desc,body,tagList)
            }
        }

        feedViewModel.postArticleError.observe(requireActivity()){
            if(it != null)
            Toast.makeText(requireActivity(), it , Toast.LENGTH_SHORT).show()
        }

    }

    private fun createArticle(title: String, desc: String, body: String, tags: List<String>) {
        val articleCreds = ArticleCreds(body,desc,tags,title)
        val articleResponse = UpsertArticleRequest(articleCreds)
        feedViewModel.postArticle("Token $token", articleResponse)
    }

}