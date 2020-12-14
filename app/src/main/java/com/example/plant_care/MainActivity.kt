package com.example.plant_care

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_add.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottom_navigation.setOnNavigationItemSelectedListener(onBottomNavListener)



        var frHome = supportFragmentManager.beginTransaction()
        frHome.add(R.id.frag_main, HomeFragment())
        frHome.commit()

    }

    private val onBottomNavListener = BottomNavigationView.OnNavigationItemSelectedListener { i ->
        var selectedFr: Fragment = HomeFragment()

        when(i.itemId){
            R.id.mn_list -> {
                selectedFr = HomeFragment()
            }
            R.id.mn_add -> {
                selectedFr = AddFragment()
            }
            R.id.mn_about -> {
                selectedFr = AboutFragment()
            }
        }

        var fr = supportFragmentManager.beginTransaction()
        fr.replace(R.id.frag_main, selectedFr)
        fr.commit()

        true
    }
}