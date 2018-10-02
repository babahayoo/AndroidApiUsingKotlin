package com.example.administator.footbalapi.View.Activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import com.example.administator.footbalapi.R
import com.example.administator.footbalapi.View.Fragment.MatchFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.title = "Prev Match"
        val matchFragment = MatchFragment.newInstance("eventspastleague.php")
        openFragment(matchFragment)

        val bottomNav : BottomNavigationView = findViewById(R.id.main_bottom_nav)
        bottomNav.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    private val mOnNavigationItemSelectedListener= BottomNavigationView.OnNavigationItemSelectedListener{
        item -> when(item.itemId){
            R.id.prev_match ->{
                supportActionBar?.title="Prev Match"
                val matchFragment = MatchFragment.newInstance("eventspastleague.php")
                openFragment(matchFragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.next_match ->{
                supportActionBar?.title="Next Match"
                val matchFragment = MatchFragment.newInstance("eventsnextleague.php")
                openFragment(matchFragment)

                return@OnNavigationItemSelectedListener true
            }

        }
        return@OnNavigationItemSelectedListener false
    }

    private fun openFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frame_container,fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }


}
