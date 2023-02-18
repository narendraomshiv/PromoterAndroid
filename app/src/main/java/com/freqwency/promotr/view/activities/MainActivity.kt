package com.freqwency.promotr.view.activities

import android.app.Activity
import android.content.Intent
import android.content.res.ColorStateList
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.freqwency.promotr.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

import androidx.navigation.fragment.NavHostFragment
import com.bumptech.glide.Glide
import com.freqwency.promotr.R
import com.freqwency.promotr.utils.GlobalFunctions
import com.freqwency.promotr.view.fragments.PromoterProfileFragment
import com.google.android.material.floatingactionbutton.FloatingActionButton


class MainActivity : BaseActivity() {

    private lateinit var navController: NavController
    private lateinit var activity: Activity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val mainBinding: ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        activity = this

        GlobalFunctions.addUIPadding(mainBinding.root)

        mainBinding.bottomNavigationView.background = null //Removing the background shadow
        mainBinding.bottomNavigationView.menu.getItem(2).isEnabled = true

        // mainBinding.fab.visibility = View.GONE
        val navView: BottomNavigationView = mainBinding.bottomNavigationView

        val navHostFragment =
            supportFragmentManager.findFragmentById(com.freqwency.promotr.R.id.nav_host_fragment_activity_main) as NavHostFragment?
        navController = navHostFragment!!.navController
        navView.setupWithNavController(navController)

        val floatingActionButton: FloatingActionButton = mainBinding.fab
        floatingActionButton.setOnClickListener {
            /*Glide
                .with(activity)
                .load(R.drawable.add_promo_selected)
                .into(floatingActionButton)*/

            /*  navController.navigate(R.id.promoterProfileFragment)*/
            mainBinding.bottomNavigationView.selectedItemId = R.id.promoterProfileFragment
            //mainBinding.bottomNavigationView.itemActiveIndicatorColor = ColorStateList.valueOf(getColor(R.color.black))
            navController.navigate(R.id.promoterProfileFragment)
        }
    }
}


