package com.example.modul4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val fragmentSatu = fragment_satu()
        val fragment = supportFragmentManager.findFragmentByTag(fragment_satu::class.java.simpleName)

        if(fragment !is fragment_satu){
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragmentSatu, fragment_satu::class.java.simpleName)
                .commit()
        }
    }
}