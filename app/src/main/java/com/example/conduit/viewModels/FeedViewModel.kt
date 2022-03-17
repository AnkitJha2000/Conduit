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
    val feedError = MutableLiveData<String>()

    fun getArticles() = viewModelScope.launch {
        repository.getArticles().let {
            if(it.isSuccessful)
            {
                globalArticlesResponse.postValue(it.body())
            }
            else
            {
                feedError.postValue(it.message())
            }
        }
    }



}