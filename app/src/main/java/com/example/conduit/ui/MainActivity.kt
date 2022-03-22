package com.example.conduit.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.conduit.R
import com.example.conduit.dao.OfflineSharedPreference
import com.example.conduit.databinding.ActivityMainBinding
import com.example.conduit.viewModels.AuthViewModel
import com.example.conduit.viewModels.FeedViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    var token: String? = null
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navController : NavController
    private lateinit var binding: ActivityMainBinding
    val authViewModel: AuthViewModel by viewModels()
    val feedViewModel : FeedViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        token = OfflineSharedPreference(this).getToken()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.mainToolbar)

        val navHostFragment = supportFragmentManager.findFragmentById(binding.mainFragmentContainerView.id) as NavHostFragment

        navController = navHostFragment.navController

        binding.mainBottomView.setupWithNavController(navController)

        appBarConfiguration = AppBarConfiguration(
            topLevelDestinationIds = setOf(
                R.id.feedFragment,
                R.id.globalFeedFragment,
                R.id.addFeedFragment,
                R.id.profileFragment,
                R.id.signUpFragment,
                R.id.loginFragment
            )
        )
        setupActionBarWithNavController(navController,appBarConfiguration)

        binding.mainToolbar.setupWithNavController(navController,appBarConfiguration)

        visibilityItems()

        preLoadData()

    }

    private fun preLoadData() {
        if(token != null)
        {
            val tokenFormatted = "Token $token"
            authViewModel.getCurrentUser(tokenFormatted)
            feedViewModel.getArticles(tokenFormatted)
        }
    }

    override fun onSupportNavigateUp() = navController.navigateUp(appBarConfiguration)

    private fun visibilityItems(){
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.splashScreenFragment -> {
                    binding.mainToolbar.visibility = View.GONE
                    binding.mainBottomView.visibility = View.GONE
                }
                R.id.signUpFragment->{
                    binding.mainBottomView.visibility = View.GONE
                }
                R.id.loginFragment->{
                    binding.mainBottomView.visibility = View.GONE
                }
                else -> {
                    binding.mainToolbar.visibility = View.VISIBLE
                    binding.mainBottomView.visibility = View.VISIBLE
                }
            }
        }
    }
}