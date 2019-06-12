package com.iamdamjanmiloshevski.makedoniko.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity;
import android.view.Menu
import android.view.MenuItem
import android.view.WindowManager
import androidx.core.content.ContextCompat
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.iamdamjanmiloshevski.makedoniko.R
import com.iamdamjanmiloshevski.makedoniko.adapters.ApplicationViewPagerAdapter
import com.iamdamjanmiloshevski.makedoniko.fragments.main.HomeFragment
import com.iamdamjanmiloshevski.makedoniko.fragments.main.AccountFragment
import com.iamdamjanmiloshevski.makedoniko.fragments.main.InfoFragment
import com.iamdamjanmiloshevski.makedoniko.fragments.main.PointsOfInterestFragment
import com.iamdamjanmiloshevski.makedoniko.fragments.main.RequestWordFragment

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var mAdapter: ApplicationViewPagerAdapter
    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                vp_pages.currentItem = 0
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_macedonia -> {
                vp_pages.currentItem = 1
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_about -> {
                vp_pages.currentItem = 2
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_request_word -> {
                vp_pages.currentItem = 3
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_account -> {
                vp_pages.currentItem = 4
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = ContextCompat.getColor(this,
            R.color.colorPrimaryDark
        )
        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        mAdapter = ApplicationViewPagerAdapter(supportFragmentManager)
       mAdapter.addFragment(HomeFragment.getInstance(), "Home")
        mAdapter.addFragment(PointsOfInterestFragment.getInstance(), "Points of Interest")
        mAdapter.addFragment(InfoFragment.getInstance(), "Info")
        mAdapter.addFragment(RequestWordFragment.getInstance(), "Request word")
        mAdapter.addFragment(AccountFragment.getInstance(), "Account")
        vp_pages.adapter = mAdapter
        navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}