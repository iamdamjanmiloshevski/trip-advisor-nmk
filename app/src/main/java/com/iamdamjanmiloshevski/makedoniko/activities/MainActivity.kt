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
import com.iamdamjanmiloshevski.makedoniko.fragments.main.AboutMacedoniaFragment
import com.iamdamjanmiloshevski.makedoniko.fragments.main.LandmarksFragment

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var mAdapter: ApplicationViewPagerAdapter
    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                vp_pages.currentItem = 0
                toolbar.title = "Phrases"
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_macedonia -> {
                toolbar.title = "Landmarks"
                vp_pages.currentItem = 1
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_about -> {
                toolbar.title = "About"
                vp_pages.currentItem = 2
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_account -> {
                toolbar.title = "Account"
                vp_pages.currentItem = 3
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        toolbar.title = "Phrases"
        setSupportActionBar(toolbar)
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = ContextCompat.getColor(
            this,
            R.color.colorPrimaryDark
        )
        initComponents()
        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
    }

    private fun initComponents() {
        mAdapter = ApplicationViewPagerAdapter(supportFragmentManager)
        mAdapter.addFragment(HomeFragment.getInstance(), getString(R.string.home_fragment_title))
        mAdapter.addFragment(
            LandmarksFragment.getInstance(),
            getString(R.string.points_of_interest_fragment_title)
        )
        mAdapter.addFragment(AboutMacedoniaFragment.getInstance(), getString(R.string.info_fragment_title))
        mAdapter.addFragment(AccountFragment.getInstance(), getString(R.string.account_fragment_title))
        vp_pages.adapter = mAdapter
        vp_pages.currentItem = 0
        nav_view.selectedItemId = R.id.navigation_home
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
