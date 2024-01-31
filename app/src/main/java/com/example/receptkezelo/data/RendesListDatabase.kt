package com.example.receptkezelo.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [RendesItem::class], version = 2)
abstract class RendesListDatabase : RoomDatabase() {
    abstract fun rendesItemDao(): RendesItemDao

    companion object {
        fun getDatabase(applicationContext: Context): RendesListDatabase {
            return Room.databaseBuilder(
                applicationContext,
                RendesListDatabase::class.java,
                "rendes-list"
            )
                .addMigrations(migration_1_2)
                .build()
        }
    }

}
val migration_1_2 = object : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("ALTER TABLE rendesitem ADD COLUMN type INTEGER NOT NULL DEFAULT 0")
    }
}