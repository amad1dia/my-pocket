package com.example.safepocket.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.safepocket.vo.Recipe

@Dao
interface RecipeDao {

    @Insert
    fun create(recipe: Recipe)

    @Query("SELECT * FROM Recipe")
    fun list(): List<Recipe>

    @Delete
    fun delete(recipe: Recipe)

    @Query("SELECT SUM(amount) FROM RECIPE")
    fun totalAmount(): Double

    @Insert
    fun insertMany(recipes: List<Recipe>?)
}