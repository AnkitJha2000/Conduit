package com.example.conduit.services

import com.example.conduit.models.requests.LoginRequest
import com.example.conduit.models.requests.SignUpRequest
import com.example.conduit.models.requests.UpdateUserRequest
import com.example.conduit.models.requests.UpsertArticleRequest
import com.example.conduit.models.responses.*
import retrofit2.Response
import retrofit2.http.*

// this interface is for API calling
// it will contain all the queries required for api calling
// we have to define it as an interface
// the @query we have defined is for calling articles by authors or favorited or tags
// to make it use coroutines we have made the function suspend function
// and we have to wrap the result obtained by retrofit in response<> because of coroutines
// coroutines work with response<> tag

interface ConduitAPI {

    // signUp the user
    @POST("users/?")
    suspend fun signUpUser(
        @Body signUpRequest: SignUpRequest
    ) : Response<UserResponse>

    // login the user
    @POST("users/login?")
    suspend fun loginUser(
        @Body loginRequest: LoginRequest
    ) : Response<UserResponse>

    // get articles by author favorited and tags
    @GET("articles")
    suspend fun getArticles(
        @Header ("Authorization") token:String?,
        @Query("author") author : String? = null,
        @Query("favorited") favorited : String? = null,
        @Query("tag") tag : String? = null
        ) : Response<ArticlesResponse>

    // get articles by slugs
    @GET("articles/{slug}")
    suspend fun getArticleBySlugs(
        @Path("slug") slug : String
    ) : Response<ArticleResponse>

    // get articles by tags
    @GET("tags")
    suspend fun getTags() : Response<TagsResponse>

    // get articles in my feed
    @GET("articles/feed")
    suspend fun getArticlesByFeed() : Response<ArticlesResponse>

    // get the current user
    @GET("user")
    suspend fun getCurrentUser(
        @Header("Authorization") token : String
    ) : Response<UserResponse>

    // update the current user
    @PUT("user")
    suspend fun updateCurrentUser(
        @Header("Authorization") token : String,
        @Body updateUserRequest: UpdateUserRequest
    ) : Response<UserResponse>

    // get the profile
    @GET("profiles/{username}")
    suspend fun getProfile(
        @Path("username") username : String
    ) : Response<ProfileResponse>

    // follow a user
    @POST("profiles/{username}/follow")
    suspend fun followProfile(
        @Header("Authorization") token : String,
        @Path("username") username: String
    ) : Response<ProfileResponse>

    // unfollow user
    @DELETE("profiles/{username}/follow")
    suspend fun unfollowProfile(
        @Header("Authorization") token : String,
        @Path("username") username: String
    ) : Response<ProfileResponse>

    // get articles from feed
    @GET("articles/feed")
    suspend fun getFeedArticles() : Response<ArticlesResponse>

    // add article to favorite
    @POST("articles/{slug}/favorite")
    suspend fun favoriteArticle(
        @Path("slug") slug : String,
        @Header("Authorization") token : String?
    ) : Response<ArticlesResponse>

    // delete article from favorite
    @DELETE("articles/{slug}/favorite")
    suspend fun unfavoriteArticle(
        @Path("slug") slug : String,
        @Header("Authorization") token : String?
    ) : Response<ArticlesResponse>

    // for adding comment
    @POST("/articles/{slug}/comments")
    suspend fun postComment(
        @Header("Authorization") token : String,
        @Path("slug") slug : String, @Body comment: String?
    ) : Response<CommentResponse>

    // for getting comments
    @GET("/articles/{slug}/comments")
    suspend fun getComments(
        @Header("Authorization") token : String,
        @Path("slug")slug: String
    ) : Response<CommentsResponse>

    // deleting comment
    @DELETE("/articles/{slug}/comments/{id}")
    suspend fun deleteComment(
        @Header("Authorization") token : String,
        @Path("slug") slug : String,
        @Path("id") id : String
    )

    // post article
    @POST("articles")
    suspend fun postArticle(
        @Header("Authorization") token : String,
        @Body article : UpsertArticleRequest
    ) : Response<ArticleResponse>

    // update article
    @PUT("articles/{slug}")
    suspend fun updateArticle(
        @Path("slug") slug:String?,
        @Header ("Authorization") token:String?,
        @Body articleRequest: UpsertArticleRequest
    ) : Response<ArticlesResponse>

    //delete article
    @DELETE("articles/{slug}")
    suspend fun deleteArticle(
        @Path("slug") slug:String?,
        @Header ("Authorization") token:String?
    ) : Response<ArticlesResponse>

    // get favorited articles
    @GET("articles")
    suspend fun getMyFavouritedArticles(
        @Header ("Authorization") token:String?,
        @Query ("limit") limit:Int? = null,
        @Query ("favorited") authorName: String?,
    ) : Response<ArticlesResponse>

}