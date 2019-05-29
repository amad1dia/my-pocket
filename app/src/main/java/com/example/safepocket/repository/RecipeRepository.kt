package com.example.safepocket.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.safepocket.api.RetrofitClient
import com.example.safepocket.api.WebService
import com.example.safepocket.db.AppDatabase
import com.example.safepocket.vo.Recipe
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RecipeRepository(appDatabase: AppDatabase?) {

    var mAppDatabase:AppDatabase? = appDatabase
    var retrofitClient = RetrofitClient.getInstance()
    private var TAG = "RecipeRepository"
    private val webService = retrofitClient?.create(WebService::class.java)



    fun createRecipe(recipe: Recipe) {
        //TODO check internet existence before creation
        //mAppDatabase?.recipeDao()?.create(recipe)

        val call = webService?.createRecipe(recipe)
        call?.enqueue(object : Callback<Recipe> {
            override fun onResponse(call: Call<Recipe>, response: Response<Recipe>) {
                val recipe = response.body()
                response.body()?.let { mAppDatabase?.recipeDao()?.create(it) }
                Log.d(TAG, "network ${response.body()}")
            }

            override fun onFailure(call: Call<Recipe>, throwable: Throwable) {
               // println(throwable)
                Log.d(TAG, "${throwable.message}")
            }
        })

    }

    fun getRecipes(): LiveData<List<Recipe>> {
        val allRecipes: MutableLiveData<List<Recipe>> = MutableLiveData()
        allRecipes.value = mAppDatabase?.recipeDao()?.list()

        webService?.getRecipes()?.enqueue(object : Callback<List<Recipe>> {
            override fun onFailure(call: Call<List<Recipe>>, t: Throwable) {
                Log.d(TAG, t.message)
            }

            override fun onResponse(call: Call<List<Recipe>>, response: Response<List<Recipe>>) {
                Log.d(TAG, "api response ${response.body()}")
            }

        })



        return allRecipes

    }

    fun getTotalAmount():Double? {
        return mAppDatabase?.recipeDao()?.totalAmount()
    }

}