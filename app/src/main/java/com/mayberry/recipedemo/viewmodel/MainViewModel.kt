package com.mayberry.recipedemo.viewmodel

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.mayberry.recipedemo.data.local.LocalRepository
import com.mayberry.recipedemo.data.local.entity.RecipeEntity
import com.mayberry.recipedemo.data.model.Recipe
import com.mayberry.recipedemo.data.remote.RemoteRepository
import com.mayberry.recipedemo.util.NetworkResult
import com.mayberry.recipedemo.util.internetConnection
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {
    //网络请求对象
    private val remoteRepository = RemoteRepository()
    //数据库的操作对象
    private val localRepository = LocalRepository(getApplication())
    //需要给外部观察
    var recipes: MutableLiveData<NetworkResult<Recipe>> = MutableLiveData()

    //外部通过该方法发起网络请求
    fun fetchRecipes(type: String) {
        //处于loading状态
        recipes.value = NetworkResult.Loading()
        //判断网络是否有连接
        if (internetConnection(getApplication<Application>())) {
            viewModelScope.launch {
                //从数据库读取数据
                val result = localRepository.getRecipe(type)
                result.collect {
                    if (it.isNotEmpty()) {
                        val entity = it.first()
                        val data = entity.recipe
                        recipes.value = NetworkResult.Success(data)
                    } else {
                        try {
                            val response = remoteRepository.fetchRecipes(type)
                            if (response.isSuccessful) {
                                //获取数据成功 处于success状态
                                recipes.value = NetworkResult.Success(response.body()!!)
                                //需要将数据保存到数据库
                                localRepository.insertRecipe(RecipeEntity(0, type, response.body()!!))
                            } else {
                                //获取数据失败 处于error状态
                                recipes.value = NetworkResult.Error(response.message())
                            }
                        } catch (e: Exception) {
                            recipes.value = NetworkResult.Error("time out: ${e.message}")
                        }
                    }
                }
            }
        } else {
            //从数据库读取数据
            viewModelScope.launch {
                val result = localRepository.getRecipe(type)
                result.collect {
                    if (it.isNotEmpty()) {
                        val entity = it.first()
                        val data = entity.recipe
                        recipes.value = NetworkResult.Success(data)
                    } else {
                        recipes.value = NetworkResult.Error("Database is empty")
                    }
                }
            }
        }
    }

    fun refreshRecipe(type: String) {
        viewModelScope.launch {
            try {
                val response = remoteRepository.fetchRecipes(type)
                if (response.isSuccessful) {
                    //获取数据成功 处于success状态
                    recipes.value = NetworkResult.Success(response.body()!!)
                    //需要将数据保存到数据库
                    localRepository.insertRecipe(RecipeEntity(0, type, response.body()!!))
                } else {
                    //获取数据失败 处于error状态
                    recipes.value = NetworkResult.Error(response.message())
                }
            } catch (e: Exception) {
                recipes.value = NetworkResult.Error("time out: ${e.message}")
            }
        }
    }
}