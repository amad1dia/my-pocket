package com.example.safepocket.api

import androidx.lifecycle.LiveData
import com.example.safepocket.utlis.Constants
import com.example.safepocket.utlis.Constants.Companion.API_RECIPES_URL
import com.example.safepocket.vo.Recipe
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface WebService {

    @POST("/recipes/add")
    fun createRecipe(@Body recipe: Recipe): Call<Recipe>

    @GET("/recipes/list")
    fun getRecipes(): Call<List<Recipe>>
}