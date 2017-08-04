package jb.com.teamattnd.ui.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment

import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import jb.com.teamattnd.R
import jb.com.teamattnd.ui.fragment.*
import jb.com.teamattnd.util.Constant
import jb.com.teamattnd.util.Controller
import jb.com.teamattnd.util.PreferenceHelper
import kotlinx.android.synthetic.main.app_bar_home.*
import org.jetbrains.anko.*

class HomeActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    var pref:PreferenceHelper = PreferenceHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        if (savedInstanceState == null) {
            val fragment:Fragment
            if(Controller.isAdmin) {
                fragment = AdminFragment()
                fab.show()
            }else {
                fragment = ClientFragment()
                fab.hide()
            }
            addFragmentOnScreen(fragment, true, true, "bluetooth")
        }

        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

        val fab = findViewById(R.id.fab) as FloatingActionButton
        fab.setOnClickListener { view ->

            val Tasks = listOf("Event", "Team", "Conversation")
            selector("What do you want to create?", Tasks, { i ->
                if(i==0)
                    startActivity(Intent(this, CreateNewActivity::class.java))

                toast("So you want to create ${Tasks[i]}, right?")
            })
        }

        val drawer = findViewById(R.id.drawer_layout) as DrawerLayout
        val drawerToggle = ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer.setDrawerListener(drawerToggle)
        drawerToggle.syncState()

        val navigationView = findViewById(R.id.nav_view) as NavigationView
        navigationView.setNavigationItemSelectedListener(this)






    }

    fun addFragmentOnScreen(fragment: Fragment, isReplace: Boolean, isBackstack: Boolean, screenTag: String) {
       // val fragmentManager = supportFragmentManager
        println("adding fragment - " + screenTag)
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        if (isReplace)
            fragmentTransaction.replace(R.id.fragment_container, fragment, screenTag)
        else
            fragmentTransaction.add(R.id.fragment_container, fragment, screenTag)
        if (isBackstack)
            fragmentTransaction.addToBackStack(screenTag)
        fragmentTransaction.commit()
    }

    override fun onBackPressed() {
        val drawer = findViewById(R.id.drawer_layout) as DrawerLayout
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        val id = item.itemId

        if (id == R.id.nav_home) {
            val fragment:Fragment
            if(Controller.isAdmin)
                fragment = AdminFragment()
            else
                fragment = ClientFragment()
            addFragmentOnScreen(fragment, true,true,"home")
        } else if (id == R.id.nav_admin) {
            val adminFragment : AdminFunctionFragment = AdminFunctionFragment()
            addFragmentOnScreen(adminFragment, true,false,"admin")

        } else if (id == R.id.nav_profile) {
            val profileFragment : ProfileFragment = ProfileFragment()
            addFragmentOnScreen(profileFragment, true,false,"profile")
        } else if (id == R.id.nav_calendar) {
            val calendarFragment : CalendarFragment = CalendarFragment()
            addFragmentOnScreen(calendarFragment, true,false,"calendar")
        }
        else if (id == R.id.nav_logout) {

            alert("Are you sure ou want to logout?") {
                yesButton {loginScreen()}

                noButton { }
            }.show()

        }

        val drawer = findViewById(R.id.drawer_layout) as DrawerLayout
        drawer.closeDrawer(GravityCompat.START)
        return true
    }

    fun loginScreen()
    {
        pref.clearAllPreference(this)
        startActivity(Intent(this, LoginActivity::class.java))
        finish()

    }
}
