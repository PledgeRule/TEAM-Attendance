package jb.com.teamattnd.ui.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.NavigationView
import android.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import jb.com.teamattnd.R
import jb.com.teamattnd.ui.fragment.HomeFragment
import jb.com.teamattnd.util.Constant
import org.jetbrains.anko.defaultSharedPreferences
import org.jetbrains.anko.selector
import org.jetbrains.anko.toast

class HomeActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    lateinit var usp: Constant
    var isAdmin : Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        usp = Constant(this)
        isAdmin = defaultSharedPreferences.getBoolean(usp.ISADMIN, false)

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

        val homeFragment : HomeFragment = HomeFragment()
        addFragmentOnScreen(homeFragment, false,true,"home")
    }

    fun addFragmentOnScreen(fragment: Fragment, isReplace: Boolean, isBackstack: Boolean, screenTag: String) {
        val fragmentManager = fragmentManager
        println("adding fragment - " + screenTag)
        val fragmentTransaction = fragmentManager.beginTransaction()
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

        if (id == R.id.nav_events) {
            // Handle the camera action
        } else if (id == R.id.nav_admin) {

        } else if (id == R.id.nav_contacts) {

        } else if (id == R.id.nav_notification) {

        } else if (id == R.id.nav_profile) {

        } else if (id == R.id.nav_calendar) {

        }

        val drawer = findViewById(R.id.drawer_layout) as DrawerLayout
        drawer.closeDrawer(GravityCompat.START)
        return true
    }


}
