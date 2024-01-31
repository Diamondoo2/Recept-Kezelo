package com.example.receptkezelo.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "rendesitem")
data class RendesItem(
    @ColumnInfo(name = "id") @PrimaryKey(autoGenerate = true) var id: Long? = null,
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "ingredients") var ingredients: String,
    @ColumnInfo(name = "description") var description: String,
    @ColumnInfo(name = "type") var type: Int
) {}