package com.example.safepocket.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.safepocket.recipe.Recipe

@Dao
interface RecipeDao {

    @Insert
    fun create(recipe: Recipe)

    @Query("SELECT * FROM Recipe")
    fun list(): List<Recipe>

    @Delete
    fun delete(recipe: Recipe)
}