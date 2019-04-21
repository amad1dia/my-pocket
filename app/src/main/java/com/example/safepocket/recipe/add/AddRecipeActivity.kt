package com.example.safepocket.recipe.add

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.safepocket.R
import com.example.safepocket.database.AppDatabase
import com.example.safepocket.recipe.Recipe
import com.google.android.material.textfield.TextInputEditText
import java.util.*

class AddRecipeActivity : AppCompatActivity() {
    var appDatabase: AppDatabase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_recipe)
        appDatabase = AppDatabase.getInstance(this)

        val recipeTitleEditText = findViewById<TextInputEditText>(R.id.recipe_title)
        val recipeAmountEditText = findViewById<TextInputEditText>(R.id.recipe_amount)
        val addRecipeButton = findViewById<Button>(R.id.save_recipe)

        addRecipeButton.setOnClickListener {
            val recipeTitle = recipeTitleEditText.text
            val recipeAmount = recipeAmountEditText.text?.toString()?.toDouble()

            appDatabase?.recipeDao()?.create(Recipe(title = recipeTitle.toString(), amount =  recipeAmount, date = Date().toString()))

            this.onBackPressed()

        }

       // Thread.sleep(3000)

        println(" liste des recettes ${appDatabase?.recipeDao()?.list()}")


    }
}
