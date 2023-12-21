package com.asalcedo.utilities

sealed class ResourceState<T> {
    class Loading<T> : ResourceState<T>()

    //object Loading : ResourceState<Nothing>()
    data class Success<T>(val data: T) : ResourceState<T>()
    data class Error<T>(val error: Any) : ResourceState<T>()
}
