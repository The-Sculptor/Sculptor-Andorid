package com.umc.sculptor.data.model.dto

import androidx.lifecycle.LiveData

import androidx.lifecycle.MutableLiveData

import androidx.lifecycle.ViewModel




class NewStoneViewModel : ViewModel() {
    private val newStone = MutableLiveData<Stone>()
    fun setNewStone(data: Stone) {
        newStone.value = data
    }

    fun setNewStoneCategory(category: Category) {
        newStone.value?.let {
            it.category = category
            newStone.value = it
        }
    }

    fun setNewStoneDate(date: String) {
        newStone.value?.let {
            it.start_date = date
            newStone.value = it
        }
    }

    fun setNewStoneGoal(goal: String) {
        newStone.value?.let {
            it.goal = goal
            newStone.value = it
        }
    }

    fun getNewStone(): LiveData<Stone> {
        return newStone
    }
}