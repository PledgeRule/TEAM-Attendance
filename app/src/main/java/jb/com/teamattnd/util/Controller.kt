package jb.com.teamattnd.util

import android.app.Activity
import android.app.Application
import android.content.Context
import jb.com.teamattnd.util.PreferenceHelper

/**
 * Created by juhi.baranwal on 31-Jul-17.
 */
class Controller : Application() {

    lateinit var packName: String

    lateinit var mContext: Context

    companion object {

        var APP_NAME: String? = null

        var isAdmin: Boolean = false

        var pref:PreferenceHelper = PreferenceHelper

        fun setUserType(mContext: Context, username: String, password: String) {

            if (username == Constant.ADMIN_USERNAME && password == Constant.ADMIN_PASSWORD) {
                isAdmin = true
                pref.setBooleanPreference(mContext, Constant.IS_ADMIN, true)
            }
            else {
                isAdmin = false
                pref.setBooleanPreference(mContext, Constant.IS_ADMIN, false)
            }

            pref.setStringPreference(mContext, Constant.CURRENT_USERNAME, username)
            pref.setStringPreference(mContext, Constant.CURRENT_PASSWORD, password)


        }
    }

    override fun onCreate() {
        super.onCreate()
        packName = this.packageName
//        mContext = getApplicationContext();
        //        loadConfigProperties();
        //        //APP Name
        APP_NAME = Constant!!.APP_NAME
        isAdmin = pref.getBooleanPreference(this, Constant.IS_ADMIN, false)

    }

}


