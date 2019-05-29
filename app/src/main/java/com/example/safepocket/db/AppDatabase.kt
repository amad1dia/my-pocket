package com.example.safepocket.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.safepocket.vo.Recipe

@Database(entities = arrayOf(Recipe::class), version = 1)
abstract class AppDatabase: RoomDatabase() {

    abstract fun recipeDao(): RecipeDao

    companion object {
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase? {
            if (INSTANCE == null) {
                synchronized(AppDatabase::class) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                        AppDatabase::class.java, "pocket.db")
                        .allowMainThreadQueries()
                        .build()
                }
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }


}