package jb.com.teamattnd.ui.acitvities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import jb.com.teamattnd.R
import jb.com.teamattnd.ui.fragment.HomeFragment

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        if (savedInstanceState == null) {
            val transaction = supportFragmentManager.beginTransaction()
            val fragment = HomeFragment()
            transaction.replace(R.id.sample_content_fragment, fragment)
            transaction.commit()
        }
    }


}
