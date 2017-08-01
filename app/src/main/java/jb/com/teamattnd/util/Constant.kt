package jb.com.teamattnd.util

import android.R.id.edit
import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import kotlin.reflect.KProperty


/**
 * Created by juhi.baranwal on 31-Jul-17.
 */

class Constant(mContext: Context?) {

    var sharedPreferences: SharedPreferences? = null
    var context: Context? = mContext

    var USERNAME = "admin_username"
    var PASSWORD = "admin_password"

    var ISADMIN = "isAdmin"
    var LOGGED_IN = true

    var CLIENT_USERNAME = "client_username"
    var CLIENT_PASSWORD = "client_password"

    companion object {
        val APP_NAME = "TEAM_ATTENDANCE"
        val ADMIN_USERNAME = "@"
        val ADMIN_PASSWORD = "@@@@@"
    }


    fun setStringSharedPref() {
        sharedPreferences = context!!.getSharedPreferences("sharedPreferences", Context.MODE_PRIVATE)
        val editor = sharedPreferences?.edit()
        editor!!.putString("key", "name")
        editor!!.apply()
    }

    fun getStringSharedPref() {
        sharedPreferences = context!!.getSharedPreferences("sharedPreferences", Context.MODE_PRIVATE)
        val editor = sharedPreferences?.edit()
        editor!!.putString("key", "name")
        editor!!.apply()
    }

    fun setBooleanSharedPref(key: String, value:Boolean) {
        sharedPreferences = context!!.getSharedPreferences("sharedPreferences", Context.MODE_PRIVATE)
        val editor = sharedPreferences?.edit()
        editor!!.putString("key", "name")
        editor!!.apply()
    }

    fun getBooleanSharedPref(key: String, value:Boolean) {
        sharedPreferences = context!!.getSharedPreferences("sharedPreferences", Context.MODE_PRIVATE)
        val editor = sharedPreferences?.edit()
        editor!!.putString("key", "name")
        editor!!.apply()
    }
}





/*
 * Android Shared Preferences Delegate for Kotlin
 *
 * Usage:
 *
 * PrefDelegate.init(context)
 * ...
 * var accessToken by stringPref(PREFS_ID, "access_token")
 * var appLaunchCount by intPref(PREFS_ID, "app_launch_count", 0)
 * var autoRefreshEnabled by booleanPref("auto_refresh enabled") // using Default Shared Preferences
 *
 *//*


abstract class UserSharedPreference<T>(val prefName: String?, val prefKey: String) {

    companion object {
        private var context: Context? = null

        */
/**
         * Initialize PrefDelegate with a Context reference
         * !! This method needs to be called before any other usage of PrefDelegate !!
         *//*

        fun init(context: Context) {
            this.context = context
        }
    }

    protected val prefs: SharedPreferences by lazy {
        if (context != null)
            if (prefName != null) context!!.getSharedPreferences(prefName, Context.MODE_PRIVATE) else PreferenceManager.getDefaultSharedPreferences(context!!)
        else
            throw IllegalStateException("Context was not initialized. Call PrefDelegate.init(context) before using it")
    }

    abstract operator fun getValue(thisRef: Any?, property: KProperty<*>): T
    abstract operator fun setValue(thisRef: Any?, property: KProperty<*>, value: T)
}

fun stringPref(prefKey: String, defaultValue: String? = null) = StringPrefDelegate(null, prefKey, defaultValue)
fun stringPref(prefName: String, prefKey: String, defaultValue: String? = null) = StringPrefDelegate(prefName, prefKey, defaultValue)
class StringPrefDelegate(prefName: String?, prefKey: String, val defaultValue: String?) : UserSharedPreference<String?>(prefName, prefKey) {
    override fun getValue(thisRef: Any?, property: KProperty<*>) = prefs.getString(prefKey, defaultValue)
    override fun setValue(thisRef: Any?, property: KProperty<*>, value: String?) = prefs.edit().putString(prefKey, value).apply()
}

fun intPref(prefKey: String, defaultValue: Int = 0) = IntPrefDelegate(null, prefKey, defaultValue)
fun intPref(prefName: String, prefKey: String, defaultValue: Int = 0) = IntPrefDelegate(prefName, prefKey, defaultValue)
class IntPrefDelegate(prefName: String?, prefKey: String, val defaultValue: Int) : UserSharedPreference<Int>(prefName, prefKey) {
    override fun getValue(thisRef: Any?, property: KProperty<*>) = prefs.getInt(prefKey, defaultValue)
    override fun setValue(thisRef: Any?, property: KProperty<*>, value: Int) = prefs.edit().putInt(prefKey, value).apply()
}

fun floatPref(prefKey: String, defaultValue: Float = 0f) = FloatPrefDelegate(null, prefKey, defaultValue)
fun floatPref(prefName: String, prefKey: String, defaultValue: Float = 0f) = FloatPrefDelegate(prefName, prefKey, defaultValue)
class FloatPrefDelegate(prefName: String?, prefKey: String, val defaultValue: Float) : UserSharedPreference<Float>(prefName, prefKey) {
    override fun getValue(thisRef: Any?, property: KProperty<*>) = prefs.getFloat(prefKey, defaultValue)
    override fun setValue(thisRef: Any?, property: KProperty<*>, value: Float) = prefs.edit().putFloat(prefKey, value).apply()
}

fun booleanPref(prefKey: String, defaultValue: Boolean = false) = BooleanPrefDelegate(null, prefKey, defaultValue)
fun booleanPref(prefName: String, prefKey: String, defaultValue: Boolean = false) = BooleanPrefDelegate(prefName, prefKey, defaultValue)
class BooleanPrefDelegate(prefName: String?, prefKey: String, val defaultValue: Boolean) : UserSharedPreference<Boolean>(prefName, prefKey) {
    override fun getValue(thisRef: Any?, property: KProperty<*>) = prefs.getBoolean(prefKey, defaultValue)
    override fun setValue(thisRef: Any?, property: KProperty<*>, value: Boolean) = prefs.edit().putBoolean(prefKey, value).apply()
}

fun longPref(prefKey: String, defaultValue: Long = 0L) = LongPrefDelegate(null, prefKey, defaultValue)
fun longPref(prefName: String, prefKey: String, defaultValue: Long = 0L) = LongPrefDelegate(prefName, prefKey, defaultValue)
class LongPrefDelegate(prefName: String?, prefKey: String, val defaultValue: Long) : UserSharedPreference<Long>(prefName, prefKey) {
    override fun getValue(thisRef: Any?, property: KProperty<*>) = prefs.getLong(prefKey, defaultValue)
    override fun setValue(thisRef: Any?, property: KProperty<*>, value: Long) = prefs.edit().putLong(prefKey, value).apply()
}

fun stringSetPref(prefKey: String, defaultValue: Set<String> = HashSet<String>()) = StringSetPrefDelegate(null, prefKey, defaultValue)
fun stringSetPref(prefName: String, prefKey: String, defaultValue: Set<String> = HashSet<String>()) = StringSetPrefDelegate(prefName, prefKey, defaultValue)
class StringSetPrefDelegate(prefName: String?, prefKey: String, val defaultValue: Set<String>) : UserSharedPreference<Set<String>>(prefName, prefKey) {
    override fun getValue(thisRef: Any?, property: KProperty<*>) = prefs.getStringSet(prefKey, defaultValue)
    override fun setValue(thisRef: Any?, property: KProperty<*>, value: Set<String>) = prefs.edit().putStringSet(prefKey, value).apply()
}*/
