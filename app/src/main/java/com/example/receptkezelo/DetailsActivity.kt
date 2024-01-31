package com.example.receptkezelo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.receptkezelo.adapter.RendesAdapter
import com.example.receptkezelo.data.RendesItem
import com.example.receptkezelo.data.RendesListDatabase

import com.example.receptkezelo.databinding.ActivityMainBinding
import com.example.receptkezelo.fragments.NewItemDialogFragment
import kotlin.concurrent.thread


class DetailsActivity : AppCompatActivity(), RendesAdapter.RendesItemClickListener, NewItemDialogFragment.NewItemDialogListener {
    companion object {
        const val KEY_FOOD_TYPE = "KEY_FOOD_TYPE"
    }

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: RendesAdapter
    private lateinit var database: RendesListDatabase
    private var foodType = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        foodType = this.intent.getIntExtra(KEY_FOOD_TYPE, -1)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        database = RendesListDatabase.getDatabase(applicationContext)

        binding.fab.setOnClickListener {

            NewItemDialogFragment(foodType).show(
                supportFragmentManager,
                NewItemDialogFragment.TAG
            )
        }
        initRecyclerView()
    }

    private fun initRecyclerView() {
        adapter = RendesAdapter(this)
        binding.rvMain.layoutManager = LinearLayoutManager(this)
        binding.rvMain.adapter = adapter
        loadItemsInBackground()
    }

    private fun loadItemsInBackground() {
        thread {
            val items = database.rendesItemDao().getAll(foodType)
            runOnUiThread {
                adapter.update(items)
            }
        }
    }

    override fun onItemChanged(item: RendesItem) {
        thread {
            database.rendesItemDao().update(item)
        }
    }

    override fun onItemDelete(item: RendesItem) {
        thread {
            database.rendesItemDao().deleteItem(item)
        }
    }
    override fun onItemCreated(newItem: RendesItem) {
        thread {
            val insertId = database.rendesItemDao().insertrendes(newItem)
            newItem.id = insertId
            runOnUiThread {
                adapter.addItem(newItem)
            }
        }
    }
}