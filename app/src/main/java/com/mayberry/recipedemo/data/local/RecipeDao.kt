package com.mayberry.recipedemo.data.local

import androidx.room.*
import com.mayberry.recipedemo.data.local.entity.FavoriteEntity
import com.mayberry.recipedemo.data.local.entity.RecipeEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RecipeDao {
    //插入数据
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecipe(recipeEntity: RecipeEntity)

    //查询数据
    @Query("select * from recipeTable where type=:type")
    fun getRecipe(type: String): Flow<List<RecipeEntity>>

    //更新数据
    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateRecipe(recipeEntity: RecipeEntity)

    /********-------- favorite --------********/
    //查询所有收藏的食谱
    @Query("select * from favorite_table")
    fun getAllFavorites(): Flow<List<FavoriteEntity>>

    //插入收藏食谱
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(favoriteEntity: FavoriteEntity)

    //删除收藏
    @Delete
    suspend fun deleteFavorite(favoriteEntity: FavoriteEntity)

}