package com.example.modul5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.modul5.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {
    val fragmentHome: Fragment = Home()
    val fragmentProfile: Fragment = Profile()
    val fragmentSettings : Fragment = Settings()
    val fm: FragmentManager = supportFragmentManager
    var active: Fragment= fragmentHome

    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var menu: Menu
    private lateinit var menuItem: MenuItem
    lateinit var binding: ActivityMainBinding
    lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        buttonNavigation()

        val drawerLayout : DrawerLayout = findViewById(R.id.drawer_layout)
        val navview: NavigationView = findViewById(R.id.nav_view)

        toggle = ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        navview.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.nav_message -> Toast.makeText(applicationContext, "Clicked Message", Toast.LENGTH_SHORT).show()
                R.id.nav_sync-> Toast.makeText(applicationContext, "Clicked Sync", Toast.LENGTH_SHORT).show()
                R.id.nav_trash -> Toast.makeText(applicationContext, "Clicked Trash", Toast.LENGTH_SHORT).show()
                R.id.nav_login -> Toast.makeText(applicationContext, "Clicked Login", Toast.LENGTH_SHORT).show()
                R.id.nav_share -> Toast.makeText(applicationContext, "Clicked Share", Toast.LENGTH_SHORT).show()
                R.id.nav_rate -> Toast.makeText(applicationContext, "Clicked Rate Us", Toast.LENGTH_SHORT).show()
            }
            true
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
       if (toggle.onOptionsItemSelected(item)){
           return true
       }
        return super.onOptionsItemSelected(item)
    }

    private fun buttonNavigation() {
        fm.beginTransaction().add(R.id.fragment_container, fragmentHome).show(fragmentHome).commit()
        fm.beginTransaction().add(R.id.fragment_container, fragmentProfile).hide(fragmentProfile).commit()
        fm.beginTransaction().add(R.id.fragment_container, fragmentSettings).hide(fragmentSettings).commit()

        bottomNavigationView = binding.botNav
        menu = bottomNavigationView.menu
        menuItem = menu.getItem(0)
        menuItem.isChecked = true

        bottomNavigationView.setOnNavigationItemSelectedListener {item ->

            when (item.itemId){
                R.id.home ->{
                    callFragment(0, fragmentHome)
                }
                R.id.profile ->{
                    callFragment(1, fragmentProfile)
                }
                R.id.settings ->{
                    callFragment(2, fragmentSettings)
                }
            }
            false
        }
    }

    private fun callFragment(index : Int, fragment: Fragment) {
        menuItem = menu.getItem(index)
        menuItem.isChecked = true
        fm.beginTransaction().hide(active).show(fragment).commit()
        active = fragment
    }
}