package com.example.conduit

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.conduit.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navController : NavController
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.mainToolbar)

        val navHostFragment = supportFragmentManager.findFragmentById(binding.mainFragmentContainerView.id) as NavHostFragment

        navController = navHostFragment.navController

        binding.mainBottomView.setupWithNavController(navController)

        // AppBarConfiguration with the correct top-level destinations
        appBarConfiguration = AppBarConfiguration(
            topLevelDestinationIds = setOf(
                R.id.feedFragment,
                R.id.globalFeedFragment,
                R.id.addFeedFragment,
                R.id.profileDetailFragment,
                R.id.signUpFragment,
                R.id.loginFragment
            )
        )
        setupActionBarWithNavController(navController,appBarConfiguration)

        binding.mainToolbar.setupWithNavController(navController,appBarConfiguration)

        visibilityItems()

    }

    override fun onSupportNavigateUp() = navController.navigateUp(appBarConfiguration)

    private fun visibilityItems(){
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.splashScreenFragment -> {
                    binding.mainToolbar.visibility = View.GONE
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