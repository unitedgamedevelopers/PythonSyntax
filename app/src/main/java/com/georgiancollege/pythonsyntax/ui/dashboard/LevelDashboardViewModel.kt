package com.georgiancollege.pythonsyntax.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LevelDashboardViewModel : ViewModel() {

    private val _levels = MutableLiveData<List<String>>()
    val levels: LiveData<List<String>> = _levels
    
    init{
        _levels.value = listOf("Level_1", "Level_2", "Level_3", "Level_4", "Level_5", "Level_6", "Level_7", "Level_8")
    }

    fun getNames(): List<String>{
        return _levels.value ?: emptyList()
    }
}