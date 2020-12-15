package com.codinginflow.navigationcomponenttutorial

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    private lateinit var appBarrConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment)
        navController = navHostFragment!!.findNavController()

        // assign the appBarrConfiguration and specify the top level fragment
        // so it will not have up (back) btn
        appBarrConfiguration =
            AppBarConfiguration(setOf(R.id.homeFragment, R.id.searchFragment), drawer_layout)

        // custom toolbar
        setSupportActionBar(toolbar)
        // 1st we added navController to setup the custom toolbar
        // 2ed we add appBarrConfiguration to change the default behavior
        // to the top level fragment (means remove up btn in the top level fragments)
        // 3rd we add drawer_layout (id) to have the navDrawer icon and do action when clicked
        setupActionBarWithNavController(navController, appBarrConfiguration)

        // bottom nav
        bottom_nav.setupWithNavController(navController)

        // nav drawer
        nav_drawer_slider.setupWithNavController(navController)
    }

    // override up btn (back btn) behavior in the toolbar >>> if false return the super call
    override fun onSupportNavigateUp(): Boolean {
        // appBarrConfiguration parameter is add because of the navDrawer
        return navController.navigateUp(appBarrConfiguration) || super.onSupportNavigateUp()
    }

    //----------------------------------------------------------------------------------------------
    // basic setting up option menu by onCreateOptionsMenu and detect clicks by onOptionItemSelected
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.options_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // navigate to the destination if the ids match
        // or return super call if something went wrong
        return when (item.itemId) {
            R.id.termsAndConditions -> {
                val action = NavGraphDirections.actionGlobalTermsFragment()
                navController.navigate(action)
                true
            }
            else -> item.onNavDestinationSelected(navController) || super.onOptionsItemSelected(item)
        }
    }
}