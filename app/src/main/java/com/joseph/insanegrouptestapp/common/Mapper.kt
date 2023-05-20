package com.joseph.insanegrouptestapp.common

interface Mapper<From, To> {

    fun map(from: From): To
}