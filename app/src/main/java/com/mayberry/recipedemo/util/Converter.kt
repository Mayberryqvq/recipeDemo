package com.mayberry.recipedemo.util

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.mayberry.recipedemo.data.model.Recipe

class Converter {
    //Recipe -> String
    @TypeConverter
    fun recipeToString(recipe: Recipe): String {
        return Gson().toJson(recipe)
    }

    //String -> Recipe
    @TypeConverter
    fun stringToRecipe(str: String): Recipe {
        return Gson().fromJson(str, Recipe::class.java)
    }
}