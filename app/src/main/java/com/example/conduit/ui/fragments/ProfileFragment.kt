package com.example.conduit.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.conduit.R
import com.example.conduit.databinding.FragmentProfileBinding
import com.example.conduit.models.requests.UpdateUserCreds
import com.example.conduit.models.requests.UpdateUserRequest
import com.example.conduit.models.responses.UserResponse
import com.example.conduit.ui.MainActivity
import com.example.conduit.viewModels.AuthViewModel
import com.google.android.material.snackbar.Snackbar

class ProfileFragment : Fragment() {
    private lateinit var binding : FragmentProfileBinding
    private lateinit var authViewModel : AuthViewModel
    private lateinit var userResponse : UserResponse
    private lateinit var token : String
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        authViewModel = (activity as MainActivity).authViewModel
        token = (activity as MainActivity).token.toString()
        authViewModel.currentUser.observe(requireActivity()){
            userResponse = it
            binding.apply {
                this.profileFragmentUserNameET.setText(userResponse.user.username)
                this.profileFragmentBioET.setText(userResponse.user.bio)
                this.profileFragmentEmailET.setText(userResponse.user.email)
                Glide.with(requireActivity())
                    .load(userResponse.user.image)
                    .placeholder(R.drawable.ic_profile_face)
                    .into(this.profileFragmentProfileImageIV)
            }
        }

        binding.profileFragmentFavoriteFeedsBtn.setOnClickListener {
            val action = ProfileFragmentDirections.actionProfileFragmentToFavoriteArticlesFragment()
            findNavController().navigate(action)
        }

        binding.profileFragmentMyFeedBtn.setOnClickListener {
            val action = ProfileFragmentDirections.actionProfileFragmentToMyFeedsFragment()
            findNavController().navigate(action)
        }

        binding.profileFragmentUpdateUserBtn.setOnClickListener {
            updateUser()
        }

    }

    private fun updateUser() {
        val email = binding.profileFragmentEmailET.text.toString()
        val bio = binding.profileFragmentBioET.text.toString()
        val password = if(binding.profileFragmentPasswordET.text.toString() == "")
        {
            null
        }
        else
        {
            binding.profileFragmentPasswordET.text.toString()
        }
        val username = binding.profileFragmentUserNameET.text.toString()
        val image = userResponse.user.image.toString()

        authViewModel.updateUser("Token $token",
            UpdateUserRequest(UpdateUserCreds(bio, email, image, password, username))
        )

        binding.profileFragmentPasswordET.text = null
    }

}