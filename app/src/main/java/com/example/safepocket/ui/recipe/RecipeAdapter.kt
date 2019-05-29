package com.example.safepocket.ui.recipe

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.safepocket.R
import com.example.safepocket.utlis.dateFormat
import com.example.safepocket.vo.Recipe
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.ZoneId
import java.util.*

class RecipeAdapter() : RecyclerView.Adapter<RecipeAdapter.ViewHolderRecipes>() {
    var recipesList: List<Recipe> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            ViewHolderRecipes(
                    LayoutInflater.from(parent.context).inflate(
                            R.layout.recipe_items,
                            parent, false
                    )
            )


    override fun getItemCount() = recipesList.size

    internal fun setRecipes(recipes: List<Recipe>) {
        recipesList = recipes
        notifyDataSetChanged()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: ViewHolderRecipes, position: Int) {
        holder.titleTv.text = recipesList.get(position).title.capitalize()
        holder.dateTv.text = recipesList.get(position).date
        holder.amountTv.text = recipesList.get(position).amount?.toString()


    }

    class ViewHolderRecipes(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTv = itemView.findViewById<TextView>(R.id.item_title)
        val dateTv = itemView.findViewById<TextView>(R.id.item_date)
        val amountTv = itemView.findViewById<TextView>(R.id.item_amount)


    }
}