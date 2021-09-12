package com.matheus.vinicus.bianchini.mvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.matheus.vinicus.bianchini.mvvm.adapter.MainAdapter
import com.matheus.vinicus.bianchini.mvvm.databinding.ActivityMainBinding
import com.matheus.vinicus.bianchini.mvvm.repository.MainRepository
import com.matheus.vinicus.bianchini.mvvm.rest.RetrofitService
import com.matheus.vinicus.bianchini.mvvm.viewModel.main.MainViewModel
import com.matheus.vinicus.bianchini.mvvm.viewModel.main.MainViewModelFactory
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var viewModel: MainViewModel
    private val retrofitService = RetrofitService.getInstance()
    private val adapter = MainAdapter {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this, MainViewModelFactory(MainRepository(retrofitService)))
            .get(MainViewModel::class.java)

        binding.recyclerview.adapter = adapter
    }

    override fun onStart() {
        super.onStart()
        viewModel.liveList.observe(this, { lives ->
            adapter.setLiveList(lives)
        })


        viewModel.liveErroMessage.observe(this, { message ->
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        })
    }

    override fun onResume() {
        super.onResume()
        viewModel.getAllLives()
    }
}