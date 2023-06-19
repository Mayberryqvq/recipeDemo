package com.mayberry.recipedemo.data.local

import android.content.Context
import kotlinx.coroutines.flow.Flow

class LocalRepository(context: Context) {
    private val recipeDao = RecipeDatabase.getInstance(context).getRecipeDao()

    //插入数据
    suspend fun insertRecipe(recipeEntity: RecipeEntity) {
        recipeDao.insertRecipe(recipeEntity)
    }

    //查询数据
    fun getRecipe(type: String): Flow<List<RecipeEntity>> {
        return recipeDao.getRecipe(type)
    }

    //更新数据
    suspend fun updateRecipe(recipeEntity: RecipeEntity) {
        recipeDao.updateRecipe(recipeEntity)
    }
}