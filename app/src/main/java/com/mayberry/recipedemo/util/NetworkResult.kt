package com.mayberry.recipedemo.util

sealed class NetworkResult<T>(val data: T? = null, val message: String? = null) {

    class Loading<T>(): NetworkResult<T>()
    class Error<T>(errMsg: String): NetworkResult<T>(message = errMsg)
    class Success<T>(data: T?): NetworkResult<T>(data)

}

