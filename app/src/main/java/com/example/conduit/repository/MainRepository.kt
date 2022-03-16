package com.example.conduit.repository

import android.util.Log
import com.example.conduit.models.requests.LoginRequest
import com.example.conduit.models.requests.SignUpRequest
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
    suspend fun getCurrentUser(token: String) : Status<UserResponse> {
        val response = apiService.getCurrentUser(token)
        response.let {
            return if(it.isSuccessful) {
                Status.Success(it.body()!!)
            } else {
                Log.d("error",it.message())
                Status.Error("error occurred",it.body())
            }
        }
    }


}