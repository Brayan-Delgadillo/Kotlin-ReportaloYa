package com.santisteban.mario.reportalo_ya.ui.view

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import com.santisteban.mario.reportalo_ya.R
import com.santisteban.mario.reportalo_ya.User
import com.santisteban.mario.reportalo_ya.util.SharedPreferenceUtil

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var sharedPreferenceUtil: SharedPreferenceUtil

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sharedPreferenceUtil = SharedPreferenceUtil()
        sharedPreferenceUtil.setSharedPreference(this)

        drawerLayout = findViewById<DrawerLayout>(R.id.drawer_layout)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        val navigationView = findViewById<NavigationView>(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(this)

        val toggle = ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_nav, R.string.close_nav)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, HomeFragment()).commit()
            navigationView.setCheckedItem(R.id.nav_home)
        }

        // Obtén la referencia al NavigationView y al header
        val headerView = navigationView.getHeaderView(0)

        // Obtén las referencias a los TextView del header
        val usernameTextView = headerView.findViewById<TextView>(R.id.nav_header_username)

        // Obtén los datos del usuario desde SharedPreferences
        val user: User? = sharedPreferenceUtil.getUser()

        // Establece los valores de usuario en los TextView del header
        usernameTextView.text = user?.name
    }
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_home -> supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, HomeFragment()).commit()

            R.id.nav_user -> supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, UserFragment()).commit()

            R.id.nav_report -> supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, ReportsFragment()).commit()

            R.id.nav_share -> shareContent()

            R.id.nav_about -> supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, AboutFragment()).commit()

            R.id.nav_logout -> logout()
        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            onBackPressedDispatcher.onBackPressed()
        }
    }

    private fun logout() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun shareContent() {
        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.type = "text/plain"
        shareIntent.putExtra(Intent.EXTRA_TEXT, "¡Cuentanos tu caso en ReportaloYa!")
        startActivity(Intent.createChooser(shareIntent, "Compartir con"))
    }

}