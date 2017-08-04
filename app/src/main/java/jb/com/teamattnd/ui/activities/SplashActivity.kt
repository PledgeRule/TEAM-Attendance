package jb.com.teamattnd.ui.activities

import android.app.Application
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import jb.com.teamattnd.R
import jb.com.teamattnd.util.Constant
import jb.com.teamattnd.util.PreferenceHelper

class SplashActivity : AppCompatActivity() {

    internal var SPLASH_TIMEOUT = 3000
//    val mHandler = Handler()
//    val mRunnable = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed(
                {
                    if (PreferenceHelper.getBooleanPreference(this, Constant.IS_LOGGED_IN, false))
                        startActivity(Intent(this, HomeActivity::class.java))
                    else
                        startActivity(Intent(this, LoginActivity::class.java))
                    finish()
                }, SPLASH_TIMEOUT.toLong())

    }

}
