package com.example.conduit.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.conduit.models.responses.ArticlesResponse
import com.example.conduit.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FeedViewModel @Inject constructor(val repository : MainRepository) : ViewModel() {

    val globalArticlesResponse = MutableLiveData<ArticlesResponse>()
    val feedError = MutableLiveData<String?>()
    val favorited = MutableLiveData<ArticlesResponse>()
    val favoritedError = MutableLiveData<String>()

    fun getArticles(token: String) = viewModelScope.launch {
        repository.getArticles(token).let {
            if(it.isSuccessful)
            {
                feedError.postValue(null)
                globalArticlesResponse.postValue(it.body())
            }
            else
            {
                feedError.postValue(it.message())
            }
        }
    }

    fun favoriteArticle(token :String , slug : String) = viewModelScope.launch {
        repository.favoriteArticle(token,slug).let {
            if(it.isSuccessful)
            {
                favorited.postValue(it.body())
            }
            else{
                favoritedError.postValue(it.message())
            }
        }
    }

    fun unfavoritedArticle(token : String,slug: String) = viewModelScope.launch {
        repository.unfavoriteArticle(token,slug).let {
            if(it.isSuccessful)
            {
                favorited.postValue(it.body())
            }
            else{
                favoritedError.postValue(it.message())
            }
        }
    }



}