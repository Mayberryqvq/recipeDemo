package com.mayberry.recipedemo.data.remote

import com.mayberry.recipedemo.data.model.Recipe
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RemoteRepository {
    //创建foodApi对象
    private val foodApi: FoodApi by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.spoonacular.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        retrofit.create(FoodApi::class.java)
    }

    //给外部提供访问接口
    suspend fun fetchRecipes(type: String) : Response<Recipe> {
        return foodApi.fetchRecipes(type)
    }
}