package com.assignment.deviceinventorymanagementapp.view

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.assignment.deviceinventorymanagementapp.DeviceInventoryManagementApplication
import com.assignment.deviceinventorymanagementapp.R
import com.assignment.deviceinventorymanagementapp.databinding.ActivityMainBinding
import dagger.android.AndroidInjection
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.DaggerAppCompatActivity
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity(), HasSupportFragmentInjector {

    /*
   * Rather than injecting the ViewModelFactory
   * in the activity, we are going to implement the
   * HasActivityInjector and inject the ViewModelFactory
   * into our Fragments
   * */
    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment?>

    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var mainBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)

        super.onCreate(savedInstanceState)
        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.findNavController()

        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.device_list_fragment,
                R.id.employee_list_fragment,
                R.id.device_allotted_list_fragment
            ),
            drawer_layout
        )

        setSupportActionBar(toolbar)
        setupActionBarWithNavController(navController, appBarConfiguration)
        nav_view.setupWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun supportFragmentInjector(): DispatchingAndroidInjector<Fragment?>? {
        return dispatchingAndroidInjector
    }
}