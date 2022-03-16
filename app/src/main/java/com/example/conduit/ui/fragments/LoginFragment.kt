package com.example.conduit.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.conduit.R
import com.example.conduit.dao.OfflineSharedPreference
import com.example.conduit.databinding.FragmentLoginBinding
import com.example.conduit.models.requests.LoginRequest
import com.example.conduit.models.requests.LoginUserCreds
import com.example.conduit.ui.MainActivity
import com.example.conduit.viewModels.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {
    private lateinit var binding : FragmentLoginBinding
    private lateinit var authViewModel: AuthViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        authViewModel = (activity as MainActivity).authViewModel

        binding.loginPageDontHaveAnAccount.setOnClickListener {
            val action = LoginFragmentDirections.actionLoginFragmentToSignUpFragment()
            findNavController().navigate(action)
        }

        binding.loginBtn.setOnClickListener {
            val email : String = binding.loginEmail.text.toString()
            val password : String = binding.loginPassword.text.toString()

            if(email == "" || password == "") return@setOnClickListener

            loginUser(email,password)
        }

        authViewModel.error.observe(requireActivity()){
            if(!it.equals(""))
            {
                Log.d("error log in", it.toString())
                Toast.makeText(requireContext(), it.toString(), Toast.LENGTH_SHORT).show()
            }
        }

        authViewModel.loginUser.observe(requireActivity()) {
            it?.let { user ->
                Log.d("TEST",user.user.token!!.toString())
                OfflineSharedPreference(requireActivity()).saveToken(user.user.token.toString())
                Toast.makeText(requireContext(), "User Logged In", Toast.LENGTH_SHORT).show()
                val action = LoginFragmentDirections.actionLoginFragmentToFeedFragment()
                findNavController().navigate(action)
            }
        }

    }

    private fun loginUser(email: String, password: String) {
        authViewModel.loginUser(LoginRequest(LoginUserCreds(email,password)))
    }

}