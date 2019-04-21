package com.example.safepocket.recipe.show

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.safepocket.R
import com.example.safepocket.recipe.Recipe
import java.text.SimpleDateFormat
import java.util.*

class RecipeAdapter(recipes: List<Recipe>) : RecyclerView.Adapter<RecipeAdapter.ViewHolderRecipes>() {
    var recipesList: List<Recipe> = recipes

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolderRecipes(
        LayoutInflater.from(parent.context).inflate(
            R.layout.recipe_items,
            parent, false
        )
    )


    override fun getItemCount() = recipesList.size

    override fun onBindViewHolder(holder: ViewHolderRecipes, position: Int) {
        holder.titleTv.text = recipesList.get(position).title
        holder.dateTv.text = SimpleDateFormat(
            "ddd-MM-YYYY HH:mm:ss",
            Locale.FRANCE
        ).format(Date(recipesList.get(position).date))
        holder.amountTv.text = recipesList.get(position).amount?.toString()


    }


    class ViewHolderRecipes(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTv = itemView.findViewById<TextView>(R.id.item_title)
        val dateTv = itemView.findViewById<TextView>(R.id.item_date)
        val amountTv = itemView.findViewById<TextView>(R.id.item_amount)


    }
}