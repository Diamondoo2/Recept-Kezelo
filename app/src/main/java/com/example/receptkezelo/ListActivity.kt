package com.example.receptkezelo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.receptkezelo.databinding.ActivityListBinding

class ListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityListBinding

    companion object {
        const val TYPE_RENDES = 1
        const val TYPE_GLUTENMENTES = 2
        const val TYPE_VEGAN = 3
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnRendes.setOnClickListener {
            val intent = Intent(this, DetailsActivity::class.java)
            intent.putExtra(DetailsActivity.KEY_FOOD_TYPE, TYPE_RENDES)
            startActivity(intent)
        }

        binding.btnGlutenmentes.setOnClickListener {
            val intent = Intent(this, DetailsActivity::class.java)
            intent.putExtra(DetailsActivity.KEY_FOOD_TYPE, TYPE_GLUTENMENTES)
            startActivity(intent)
        }
        binding.btnVegan.setOnClickListener {
            val intent = Intent(this, DetailsActivity::class.java)
            intent.putExtra(DetailsActivity.KEY_FOOD_TYPE, TYPE_VEGAN)
            startActivity(intent)
        }
    }
}