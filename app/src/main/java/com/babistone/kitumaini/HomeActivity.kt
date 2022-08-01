package com.babistone.kitumaini

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.babistone.kitumaini.R
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

                }
                else -> false
            }
            true

        }
    }
}