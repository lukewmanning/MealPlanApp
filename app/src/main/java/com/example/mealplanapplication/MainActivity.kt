package com.example.mealplanapplication

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.viewpager2.widget.ViewPager2
import com.example.mealplanapplication.Adapter.TabAdapter
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Set up toolbar
        val toolbar = findViewById<Toolbar>(R.id.toolbar_news_page)
        setSupportActionBar(toolbar)
        //layouts for tablayout and viewpager
        val tabLayout = findViewById<TabLayout>(R.id.tab_layout)
        val viewPager = findViewById<ViewPager2>(R.id.pager)
        val tabTitles = resources.getStringArray(R.array.tabTitles)

        viewPager.adapter = TabAdapter(this)
        TabLayoutMediator(tabLayout, viewPager) {tab, position ->
            when (position) {
                0 -> tab.text = tabTitles[0]
                1 -> tab.text = tabTitles[1]
            }
        }.attach()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate((R.menu.toolbar), menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val view = findViewById<View>(R.id.toolbar)

        when (item.itemId) {
            R.id.settingsButton -> {
                val snackbar = Snackbar.make(view, "settings", Snackbar.LENGTH_LONG)
                snackbar.show()
                //Code to bring you to settings activity
                /*
                val newIntent = Intent(this, SettingsActivity :: class.java)
                startActivity(newIntent)*/
                return true
            }
            R.id.logoutButton -> {
                val snackbar = Snackbar.make(view, "settings", Snackbar.LENGTH_LONG)
                snackbar.show()
                //Set up properly
                /*
                currentUser = mAuth.currentUser
                mAuth.signOut()
                update()
                val newIntent = Intent(this, LoginActivity :: class.java)
                startActivity(newIntent)
                return true*/
            }
        }

        return super.onOptionsItemSelected(item)
    }
}