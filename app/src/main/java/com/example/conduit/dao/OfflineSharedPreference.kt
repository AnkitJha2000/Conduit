package com.example.conduit.dao

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.util.Log

class OfflineSharedPreference (activity: Activity) {

    private val sharedPreferences: SharedPreferences = activity.getSharedPreferences("sharedPreference", Context.MODE_PRIVATE)

    private var editor: SharedPreferences.Editor = sharedPreferences.edit()

    fun saveToken(token : String){
        Log.d("TEST",token)
        editor.putString("token",token).apply()
    }

    fun getToken() = sharedPreferences.getString("token",null)

}