package com.example.receptkezelo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle


class MainActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startActivity(Intent(this, ListActivity::class.java))
    }

    override fun onResume() {
        super.onResume()
        this.finish()
    }
}