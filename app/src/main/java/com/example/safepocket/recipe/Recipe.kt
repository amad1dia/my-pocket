package com.example.safepocket.recipe

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class Recipe(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val amount: Double?,
    val date: String
)