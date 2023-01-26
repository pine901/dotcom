package com.example.farmfooddeliveryapp.utils.common

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences

 object SharedPref {

    private var mSharedPref: SharedPreferences? = null


    private fun SharedPref() {}

    fun init(context: Context) {
        if (mSharedPref == null) mSharedPref =
            context.getSharedPreferences(context.packageName, Activity.MODE_PRIVATE)
    }

    fun getString(key: String?, defValue: String?): String? {
        return mSharedPref!!.getString(key, defValue)
    }

    fun saveString(key: String?, value: String?) {
        val prefsEditor = mSharedPref!!.edit()
        prefsEditor.putString(key, value)
        prefsEditor.apply()
    }

    fun getBool(key: String?, defValue: Boolean): Boolean {
        return mSharedPref!!.getBoolean(key, defValue)
    }

    fun setBool(key: String?, value: Boolean) {
        val prefsEditor = mSharedPref!!.edit()
        prefsEditor.putBoolean(key, value)
        prefsEditor.apply()
    }
    fun setInt(key: String?, defValue: Int): Int? {
        return mSharedPref!!.getInt(key, defValue)
    }

    fun getInt(key: String?, value: Int?) {
        val prefsEditor = mSharedPref!!.edit()
        prefsEditor.putInt(key, value!!).apply()
    }
     fun clear() {
         val pref: SharedPreferences.Editor = mSharedPref!!.edit()
         pref.clear()
         pref.apply()

     }

}