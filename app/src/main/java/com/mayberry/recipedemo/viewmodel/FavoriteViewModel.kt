package com.mayberry.recipedemo.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.mayberry.recipedemo.data.local.LocalRepository
import com.mayberry.recipedemo.data.local.entity.FavoriteEntity
import com.mayberry.recipedemo.data.model.Result
import kotlinx.coroutines.launch

class FavoriteViewModel(application: Application): AndroidViewModel(application) {
    private val localRepository = LocalRepository(application)
    val favoriteRecipes: MutableLiveData<List<FavoriteEntity>> = MutableLiveData()

    //查询所有收藏的食谱
    fun readFavorites(){
        viewModelScope.launch {
            localRepository.getAllFavorites().collect {
                favoriteRecipes.value = it
            }
        }
    }

    //插入收藏食谱
    fun insertFavorite(result: Result) {
        viewModelScope.launch {
            val favoriteEntity = FavoriteEntity(0, result)
            localRepository.insertFavorite(favoriteEntity)
        }
    }

    //删除收藏
    fun deleteFavorite(favoriteEntity: FavoriteEntity) {
        viewModelScope.launch {
            localRepository.deleteFavorite(favoriteEntity)
        }
    }
}