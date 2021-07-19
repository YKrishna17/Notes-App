package com.example.notes

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Note::class),version = 1,exportSchema = false)
abstract class RoomDB: RoomDatabase() {
    abstract fun dao():Dao
    companion object{
        @Volatile
        private var INSTANCE: RoomDB? = null

        fun getDatabase(context: Context): RoomDB {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    RoomDB::class.java,
                    "note_database"
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}