package jb.com.teamattnd.ui.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import jb.com.teamattnd.R

class SplashActivity : AppCompatActivity() {

    internal var SPLASH_TIMEOUT = 3000
//    val mHandler = Handler()
//    val mRunnable = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed(
                {
                    startActivity(Intent(this, LoginActivity::class.java))
                    finish()
                }, SPLASH_TIMEOUT.toLong())

    }

}
