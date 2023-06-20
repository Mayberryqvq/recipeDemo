package com.mayberry.recipedemo.data.local

import android.content.Context
import com.mayberry.recipedemo.data.local.entity.FavoriteEntity
import com.mayberry.recipedemo.data.local.entity.RecipeEntity
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

    /********-------- favorite --------********/
    //查询所有收藏的食谱
    fun getAllFavorites(): Flow<List<FavoriteEntity>> {
        return recipeDao.getAllFavorites()
    }

    //插入收藏食谱
    suspend fun insertFavorite(favoriteEntity: FavoriteEntity) {
        recipeDao.insertFavorite(favoriteEntity)
    }

    //删除收藏
    suspend fun deleteFavorite(favoriteEntity: FavoriteEntity) {
        recipeDao.deleteFavorite(favoriteEntity)
    }
}