package com.wikilift.aprendeasumar

import android.content.Context
import android.content.SharedPreferences

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle



import com.google.gson.Gson
import com.wikilift.aprendeasumar.core.IOnBackPressed


import com.wikilift.aprendeasumar.data.model.User



class MainActivity : AppCompatActivity() {
    private lateinit var gson: Gson

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        gson = Gson()
        prefs = Prefs(applicationContext)



    }

    companion object {
        lateinit var prefs: Prefs
        var user: User? = null

    }

   override fun onBackPressed() {
        val navHost = supportFragmentManager.findFragmentById(R.id.nav_host_fragment)
        navHost?.let { navFragment ->
            navFragment.childFragmentManager.primaryNavigationFragment?.let { fragment ->
                (fragment as? IOnBackPressed)?.onBackPressed()?.not()?.let { isCanceled: Boolean ->

                    if (!isCanceled) {
                        super.onBackPressed()
                    }
                }
            }
        }
    }

}
class Prefs (context: Context) {
    private val PREFS_NAME = "com.wikilift.aprendeasumar"
    private val SHARED_NAME = "shared_name"
    private val prefs: SharedPreferences = context.getSharedPreferences(PREFS_NAME, 0)
     var name: String?
        get() = prefs.getString(SHARED_NAME, "")
        set(value) = prefs.edit().putString(SHARED_NAME, value).apply()
}
