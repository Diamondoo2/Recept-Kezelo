package com.example.receptkezelo.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface RendesItemDao {

    @Query("SELECT * FROM rendesitem WHERE type = :type")
    fun getAll(type: Int): List<RendesItem>

    @Insert
    fun insertrendes(rendesItems: RendesItem): Long

    @Update
    fun update(rendesItems: RendesItem)

    @Delete
    fun deleteItem(rendesItems: RendesItem)
}