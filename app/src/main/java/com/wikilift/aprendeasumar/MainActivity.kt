package com.wikilift.aprendeasumar

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.gson.Gson
import com.wikilift.aprendeasumar.data.model.User


class MainActivity : AppCompatActivity() {
    private lateinit var gson: Gson
    private var jsonString:String?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        gson = Gson()
        prefs = Prefs(applicationContext)



    }



    companion object {
        lateinit var prefs: Prefs
         var user: User?=null

    }

}
class Prefs (context: Context) {
    val PREFS_NAME = "com.wikilift.aprendeasumar"
    val SHARED_NAME = "shared_name"
    val prefs: SharedPreferences = context.getSharedPreferences(PREFS_NAME, 0)
    var name: String?
        get() = prefs.getString(SHARED_NAME, "")
        set(value) = prefs.edit().putString(SHARED_NAME, value).apply()
}
