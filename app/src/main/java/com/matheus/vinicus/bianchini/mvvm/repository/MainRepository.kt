package com.matheus.vinicus.bianchini.mvvm.repository

import com.matheus.vinicus.bianchini.mvvm.rest.RetrofitService

class MainRepository constructor(private val retrofitService: RetrofitService) {

    fun getAllLives() = retrofitService.getAllLives()
}