package com.mayberry.recipedemo.util

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.widget.Toast

fun internetConnection(application: Application): Boolean {
    //获取系统的网络连接管理器
    val connectivityManager = application.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetwork = connectivityManager.activeNetwork ?: return false
    val capability = connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false
    return when {
        capability.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
        capability.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
        capability.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
        else -> false
    }
}

fun showToast(context: Context, message: String) {
    Toast.makeText(context, "Get recipes failed: $message", Toast.LENGTH_LONG).show()
}