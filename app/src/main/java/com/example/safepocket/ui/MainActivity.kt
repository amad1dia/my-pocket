package com.example.safepocket.ui

import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.widget.TextView
import androidx.annotation.RequiresApi
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.safepocket.R
import com.example.safepocket.ui.recipe.RecipeListFragment
import kotlinx.android.synthetic.main.activity_main.*
import java.time.LocalDate

class MainActivity : AppCompatActivity(), RecipeListFragment.OnFragmentInteractionListener {
    override fun onFragmentInteraction(uri: Uri) {

    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_recipe -> {
                val recipeListFragment = RecipeListFragment()
                val transaction = supportFragmentManager.beginTransaction()
                transaction.replace(R.id.frame, recipeListFragment)
                transaction.addToBackStack(null)
                transaction.commit()

                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
               // message.setText(R.string.title_dashboard)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
              //  message.setText(R.string.title_notifications)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<Toolbar>(R.id.toolbar).title = "Ajout de recette"

        setSupportActionBar(toolbar)


        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }


}
