package com.mayberry.recipedemo.data.remote

import com.mayberry.recipedemo.data.model.Recipe
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface FoodApi {
    //基地址 https://api.spoonacular.com/
    @GET("recipes/complexSearch?addRecipeInformation=true&fillIngredients=true&apiKey=aebbbe717f6741f7a65b5a9c2e6a9856&number=50")
    suspend fun fetchRecipes(@Query("type")type: String): Response<Recipe>
}