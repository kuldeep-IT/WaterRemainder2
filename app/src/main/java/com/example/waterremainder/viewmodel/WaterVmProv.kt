package com.example.waterremainder.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.waterremainder.repositories.WaterRepo


class WaterVmProv(val repo: WaterRepo) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return WaterViewModel(repo) as T
//        return modelClass.getConstructor(WaterViewModel::class.java).newInstance(repo)
    }
}

