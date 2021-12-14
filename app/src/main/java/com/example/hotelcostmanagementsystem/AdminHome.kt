package com.example.hotelcostmanagementsystem

import android.os.Bundle
import android.widget.Toolbar
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.hotelcostmanagementsystem.databinding.ActivityAdminHomeBinding
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.mytoolbar.*

class AdminHome : AppCompatActivity() {
    private lateinit var toolbar: Toolbar
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationView:NavigationView
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

//    private lateinit var binding: ActivityAdminHomeBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_home)
//        binding = ActivityAdminHomeBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//        toolbar=findViewById(R.id.toolbar)
        setSupportActionBar(findViewById(R.id.myToolbar))
        drawerLayout=findViewById(R.id.drawer)

        navigationView= findViewById(R.id.nav)
       navController = findNavController(R.id.nav_host_fragment_content_admin_home)

//        appBarConfiguration = AppBarConfiguration(navController.graph)
        appBarConfiguration = AppBarConfiguration(setOf(R.id.id_registration,R.id.id_viewadminsFragment,R.id.id_updatepasswordFragment,R.id.id_adminMainActivity,R.id.id_cashiers,R.id.id_salesFragment,R.id.id_purchasesFragment,R.id.id_netFragment),drawerLayout)
        setupActionBarWithNavController(navController, appBarConfiguration)
        navigationView.setupWithNavController(navController)
//        binding.fab.setOnClickListener { view ->
//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                .setAction("Action", null).show()
//        }
    }
//
//    private fun replacefragment(fragment: Fragment,title:String){
//        val fragmentmanager= supportFragmentManager
//        var fragmnentTransaction=fragmentmanager.beginTransaction()
//        fragmnentTransaction.replace(R.id.nav_host_fragment_content_admin_home,fragment)
//        fragmnentTransaction.commit()
//        drawerLayout.closeDrawers()
//        setTitle(title)
//    }
    override fun onSupportNavigateUp(): Boolean {
//        val navController = findNavController(R.id.nav_host_fragment_content_admin_home)
//        return navController.navigateUp(appBarConfiguration)
//                || super.onSupportNavigateUp()
        val navController = findNavController(R.id.nav_host_fragment_content_admin_home)
      return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}