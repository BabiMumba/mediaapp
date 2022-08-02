package com.babistone.kitumaini

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.babistone.kitumaini.R
import com.babistone.kitumaini.fragement.MonCompte
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        navigation.setOnNavigationItemSelectedListener() {
            when(it.itemId){
                R.id.nav_home -> {

                }
                R.id.my_count -> {
                    replaceFragment(MonCompte())

                }
                else -> false
            }
            true

        }
    }
    private fun replaceFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().apply {
        replace(R.id.fragment_layout,fragment)
            commit()
        }
    }
}