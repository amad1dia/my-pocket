package com.example.safepocket.ui.recipe

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProviders
import com.example.safepocket.R
import com.example.safepocket.db.AppDatabase
import com.example.safepocket.utlis.dateFormat
import com.example.safepocket.vo.Recipe
import com.google.android.material.textfield.TextInputEditText
import java.time.LocalDate
import java.util.*

class AddRecipeActivity : AppCompatActivity() {
    var appDatabase: AppDatabase? = null
    lateinit var viewModel: RecipeViewModel

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_recipe)
        appDatabase = AppDatabase.getInstance(this)

        viewModel = ViewModelProviders.of(this).get(RecipeViewModel::class.java)
        val recipeTitleEditText = findViewById<TextInputEditText>(R.id.recipe_title)
        val recipeAmountEditText = findViewById<TextInputEditText>(R.id.recipe_amount)
        val addRecipeButton = findViewById<Button>(R.id.save_recipe)

        addRecipeButton.setOnClickListener {
            val recipeTitle = recipeTitleEditText.text
            val recipeAmount = recipeAmountEditText.text?.toString()?.toDouble()
/*
            appDatabase?.recipeDao()?.create(
                    Recipe(
                            title = recipeTitle.toString(),
                            amount = recipeAmount, date = dateFormat(LocalDate.now())
                    )
            )
*/
            viewModel.createRecipe(recipe = Recipe(
                    title = recipeTitle.toString(),
                    amount = recipeAmount, date = dateFormat(LocalDate.now())
            ))

            this.onBackPressed()

        }

        println(" liste des recettes ${appDatabase?.recipeDao()?.list()}")


    }
}
