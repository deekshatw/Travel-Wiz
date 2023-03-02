package com.example.travelwiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeActivity : AppCompatActivity() {
    private lateinit var navbar : BottomNavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        replaceFragment(HomeFragment())

        navbar = findViewById(R.id.navbar)

        navbar.setOnItemSelectedListener {item->
            when(item.itemId)  {
                R.id.home->  {
                    replaceFragment( HomeFragment())
                }
                R.id.hotels->  {
                    replaceFragment( HotelsFragment())
                }
                R.id.explore ->{
                    replaceFragment( ExploreFragment())
                }
                else -> {
                    replaceFragment( ProfileFragment())
                }
            }


            return@setOnItemSelectedListener true
        }
    }

    private fun replaceFragment(fragment: Fragment){
        val fragmentManager : FragmentManager = supportFragmentManager
        val fragmentTransaction : FragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frameLayout, fragment)
        fragmentTransaction.commit()
    }
}