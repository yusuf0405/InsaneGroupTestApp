package com.joseph.insanegrouptestapp.common

sealed class ResponseStatus<T> {

    class Success<T>(data: T) : ResponseStatus<T>()

    class Error<T>(message: String, error: Throwable) : ResponseStatus<T>()

    class Loading<T> : ResponseStatus<T>()

}