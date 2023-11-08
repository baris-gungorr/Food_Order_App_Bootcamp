package com.barisgungorr.bootcamprecipeapp.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.barisgungorr.bootcamprecipeapp.data.entity.Yemekler
import com.barisgungorr.bootcamprecipeapp.data.repo.MealsRepository
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(private val mRepo: MealsRepository) : ViewModel() {
    private val cacheList = mutableListOf<Yemekler>()
    var mealList = MutableLiveData<List<Yemekler>>()

    init {
        getMeals()
    }

    private fun getMeals() {
        viewModelScope.launch {
            try {
                val meals = mRepo.getMeals()
                mealList.value = meals
                cacheList.addAll(meals)

            } catch (e: Exception) {

            }
        }
    }

    fun search(searchKeyword: String) {
        viewModelScope.launch {
            try {
                if (searchKeyword.isEmpty()) {
                    mealList.value = cacheList
                } else {
                    mealList.value = cacheList.filter {
                        it.meals_name.lowercase().contains(searchKeyword.lowercase())
                    }
                }

            } catch (_: Exception) {

            }
        }
    }

    fun signOut() {
        Firebase.auth.signOut()
    }
}
