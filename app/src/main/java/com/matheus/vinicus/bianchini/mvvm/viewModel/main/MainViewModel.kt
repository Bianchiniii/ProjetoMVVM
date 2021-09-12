package com.matheus.vinicus.bianchini.mvvm.viewModel.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.matheus.vinicus.bianchini.mvvm.model.Live
import com.matheus.vinicus.bianchini.mvvm.repository.MainRepository
import retrofit2.Call
import retrofit2.Response

class MainViewModel constructor(private val repository: MainRepository) : ViewModel() {
    val liveList = MutableLiveData<List<Live>>()
    val liveErroMessage = MutableLiveData<String>()

    fun getAllLives() {
        val request = repository.getAllLives()
        request.enqueue(object : retrofit2.Callback<List<Live>> {
            override fun onResponse(call: Call<List<Live>>, response: Response<List<Live>>) {
                liveList.postValue(response.body())
            }

            override fun onFailure(call: Call<List<Live>>, t: Throwable) {
                liveErroMessage.postValue(t.message)
            }
        })
    }
}