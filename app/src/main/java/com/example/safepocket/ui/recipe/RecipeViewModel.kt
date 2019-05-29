package com.example.safepocket.ui.recipe

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.example.safepocket.db.AppDatabase
import com.example.safepocket.repository.RecipeRepository
import com.example.safepocket.vo.Recipe

class RecipeViewModel(application: Application): AndroidViewModel(application) {

    private val recipeRepository: RecipeRepository
    private val observableRecipes: MediatorLiveData<List<Recipe>>

    init {
        //get the database instance
        val appDatabase = AppDatabase.getInstance(application)
        //get RecipeRepository instance
        recipeRepository = RecipeRepository(appDatabase)
        //initialize recipes with null
        observableRecipes = MediatorLiveData()
        observableRecipes.value = null
        //get recipes from database
        val recipes: LiveData<List<Recipe>> = recipeRepository.getRecipes()

        // observe the changes of the products from the database and forward them
        observableRecipes.addSource(recipes, observableRecipes::setValue)
    }

    /**
     * Expose the LiveData Products query so the UI can observe it.
     */
    fun getRecipes(): LiveData<List<Recipe>> {
        return observableRecipes
    }

    fun createRecipe(recipe: Recipe) {
        recipeRepository.createRecipe(recipe)
    }

    fun totalAmount() = recipeRepository.getTotalAmount()
}