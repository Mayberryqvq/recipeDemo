package com.mayberry.recipedemo.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.mayberry.recipedemo.data.model.Recipe
import com.mayberry.recipedemo.data.remote.FoodApi
import com.mayberry.recipedemo.data.remote.RemoteRepository
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {
    //网络请求对象
    private val remoteRepository = RemoteRepository()
    //需要给外部观察
    var recipes: MutableLiveData<Recipe> = MutableLiveData()

    //外部通过该方法发起网络请求
    fun fetchRecipes(type: String) {
        viewModelScope.launch {
            val response = remoteRepository.fetchRecipes(type)
            if (response.isSuccessful) {
                recipes.value = response.body()
            }
        }
    }
}