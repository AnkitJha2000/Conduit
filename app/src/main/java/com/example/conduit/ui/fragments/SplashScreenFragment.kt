package com.example.conduit.ui.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.conduit.R
import com.example.conduit.dao.OfflineSharedPreference
import com.example.conduit.databinding.FragmentSplashScreenBinding
import com.example.conduit.ui.MainActivity
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("CustomSplashScreen")
class SplashScreenFragment : Fragment() {

    private var token : String? = null
    private lateinit var binding : FragmentSplashScreenBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSplashScreenBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        token = (activity as MainActivity).token
        splashWork()

    }

    private fun splashWork() {
        lifecycleScope.launch {
            while(true)
            {
                delay(1000)
                checkUser()
                break
            }
        }
    }

    private fun checkUser(){
        Log.d("tokenSplash",token.toString())
        if(token == null)
        {
            val action = SplashScreenFragmentDirections.actionSplashScreenFragmentToSignUpFragment()
            findNavController().navigate(action)
        }
        else
        {
            val action = SplashScreenFragmentDirections.actionSplashScreenFragmentToFeedFragment()
            findNavController().navigate(action)
        }
    }

}