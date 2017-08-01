package jb.com.teamattnd.util

import android.app.Application
import jb.com.teamattnd.R

/**
 * Created by juhi.baranwal on 31-Jul-17.
 */
class Controller : Application() {

    lateinit var packName: String

    companion object {

        var APP_NAME: String? = null


        private val APPLICATION_ID: String? = null


        private val isClientUserEnabled: Boolean = false
    }



    override fun onCreate() {
        super.onCreate()
        packName = this.packageName
        //        mContext = getApplicationContext();
        //        loadConfigProperties();
        //        //APP Name
        APP_NAME = Constant!!.APP_NAME

        setConfigProperties()
    }

    private fun setConfigProperties() {

//        USP.init(this)

    }

}
