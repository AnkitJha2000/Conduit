package com.example.conduit.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.conduit.dao.OfflineSharedPreference
import com.example.conduit.databinding.FragmentSignUpBinding
import com.example.conduit.models.requests.SignUpRequest
import com.example.conduit.models.requests.SignUpUserCreds
import com.example.conduit.ui.MainActivity
import com.example.conduit.viewModels.AuthViewModel

class SignUpFragment : Fragment() {

    private lateinit var binding: FragmentSignUpBinding
    private lateinit var authViewModel: AuthViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignUpBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        authViewModel = (activity as MainActivity).authViewModel

        binding.signUpPageAlreadyHaveAccount.setOnClickListener {
            val action = SignUpFragmentDirections.actionSignUpFragmentToLoginFragment()
            findNavController().navigate(action)
        }

        binding.SignUpBtn.setOnClickListener {
            val email : String = binding.signUpEmail.text.toString()
            val username : String = binding.signUpUsername.text.toString()
            val password : String = binding.signUpPassword.text.toString()

            if(email == "" || username == "" || password == "") return@setOnClickListener

            signUpUser(email,username,password)
        }

        authViewModel.error.observe(requireActivity()){
            Log.d("error sign Up",it.toString())
        }

        authViewModel.signUpUser.observe(requireActivity()) {
            it?.let { user ->
                Log.d("TEST",user.user.token!!)
                OfflineSharedPreference(requireActivity()).saveToken(user.user.token!!)
                Toast.makeText(requireContext(), "User Registered", Toast.LENGTH_SHORT).show()
                val action = SignUpFragmentDirections.actionSignUpFragmentToLoginFragment()
                findNavController().navigate(action)
            }
        }

    }

    private fun signUpUser(email: String, username: String, password: String) {
        authViewModel.signUpUser(SignUpRequest(SignUpUserCreds(username, email, password)))
    }



}