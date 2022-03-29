package com.example.conduit.repository

import android.util.Log
import com.example.conduit.models.entities.User
import com.example.conduit.models.requests.*
import com.example.conduit.models.responses.ArticleResponse
import com.example.conduit.models.responses.ArticlesResponse
import com.example.conduit.models.responses.UserResponse
import com.example.conduit.services.ConduitAPI
import com.example.conduit.utils.Status
import retrofit2.Response
import javax.inject.Inject


class MainRepository @Inject constructor(
    // private val dao : OfflineDataDao,
    private val apiService : ConduitAPI
    ) {

    suspend fun signUpUser(signUpRequest: SignUpRequest) : Response<UserResponse> {
        val signupresponse = apiService.signUpUser(signUpRequest)
        Log.d("signupresponse",signupresponse.body()?.user.toString())
        Log.d("signupresponse",signupresponse.message())
        return signupresponse
    }

    suspend fun loginUser(loginRequest: LoginRequest): Response<UserResponse> {
        val loginresponse = apiService.loginUser(loginRequest)
        Log.d("loginresponse",loginresponse.body()?.user.toString())
        Log.d("signupresponse",loginresponse.message())
        return loginresponse
    }
    suspend fun getCurrentUser(token: String) : Response<UserResponse> {
        val response = apiService.getCurrentUser(token)
        return response
    }

    suspend fun getArticles(token: String) : Response<ArticlesResponse> {
        val response = apiService.getArticles(token)
        return response
    }

    suspend fun favoriteArticle(token: String , slug : String) : Response<ArticlesResponse> {
        Log.d("tokenMainRepositoryF",token)
        val response = apiService.favoriteArticle(slug,token)
        return response
    }

    suspend fun unfavoriteArticle(token: String , slug: String) : Response<ArticlesResponse>
    {
        Log.d("tokenMainRepositoryU",token)
        val response = apiService.unfavoriteArticle(slug,token)
        return response
    }

    suspend fun postArticle(token: String,article : UpsertArticleRequest) : Response<ArticleResponse>
    {
        Log.d("RepoReceivedArticle",article.toString())
        val response = apiService.postArticle(token,article)
        return response
    }

    suspend fun getMyFeed(token: String) : Response<ArticlesResponse>{
        Log.d("RepoMyFeed","articles were called here in main repo")
        val response = apiService.getFeedArticles(token)
        return response
    }

    suspend fun updateUser(token: String,user : UpdateUserRequest) : Response<UserResponse>
    {
        Log.d("RepoUpdateUser","user was updated in repo")
        val response = apiService.updateCurrentUser(token,user)
        return response
    }

    suspend fun getMyFavoritedArticles(token: String,userName : String) : Response<ArticlesResponse>{
        Log.d("RepofavoritedArticles","favorited articles are returned")
        val response = apiService.getMyFavoritedArticles(token, username = userName)
        return response
    }

}