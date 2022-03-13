package com.example.conduit.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.conduit.models.requests.LoginRequest
import com.example.conduit.models.requests.SignUpRequest
import com.example.conduit.models.responses.UserResponse
import com.example.conduit.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val repository: MainRepository,
    ) : ViewModel(){

    val signUpUser = MutableLiveData<UserResponse>()
    val loginUser = MutableLiveData<UserResponse>()
    val currentUser = MutableLiveData<UserResponse>()
    val error = MutableLiveData<String>()

    fun signUpUser(userSignUpRequest: SignUpRequest)= viewModelScope.launch{
            repository.signUpUser(userSignUpRequest).let {
                if(it.isSuccessful)
                {
                    signUpUser.postValue(it.body())
                }
                else
                {
                    error.postValue(it.message())
                }
            }
        }

    fun loginUser(userLoginRequest: LoginRequest) = viewModelScope.launch {
        repository.loginUser(userLoginRequest).let {
            if(it.isSuccessful)
            {
                loginUser.postValue(it.body())
            }
            else
            {
                error.postValue(it.message())
            }
        }
    }

    fun getCurrentUser(token : String) = viewModelScope.launch {
        repository.getCurrentUser(token).let {
            if(it.data == null)
            {

            }
        }
    }

}