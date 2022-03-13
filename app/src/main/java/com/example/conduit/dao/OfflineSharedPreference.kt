package com.example.conduit.dao

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import javax.inject.Inject

class OfflineSharedPreference @Inject constructor(activity: Activity) {

    private val sharedPreferences: SharedPreferences = activity.getSharedPreferences("sharedPreference", Context.MODE_PRIVATE)

    private var editor: SharedPreferences.Editor = sharedPreferences.edit()

    fun saveToken(token : String){
        editor.putString("token Auth",token)
    }

    fun getToken() = sharedPreferences.getString("token Auth",null)

}