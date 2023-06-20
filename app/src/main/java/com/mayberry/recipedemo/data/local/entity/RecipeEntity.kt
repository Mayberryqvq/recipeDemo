package com.mayberry.recipedemo.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mayberry.recipedemo.data.model.Recipe

@Entity(tableName = "recipeTable")
class RecipeEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val type: String,
    val recipe: Recipe
    )