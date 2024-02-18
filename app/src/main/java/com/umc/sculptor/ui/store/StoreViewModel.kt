package com.umc.sculptor.ui.store

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.umc.sculptor.data.model.remote.store.Item
import com.umc.sculptor.data.model.remote.store.ItemX
import com.umc.sculptor.data.model.remote.store.Stone

class StoreViewModel : ViewModel() {
    private val _selectedStatue = MutableLiveData<Stone>()// 선택된 아이템을 저장_ LiveData
    private val _selectedItem = MutableLiveData<Item>()
    private val _wantedItem = MutableLiveData<ItemX>()

    // 여러 아이템을 저장할 리스트 추가
    var _selectedItemsList = MutableLiveData<MutableList<String>>()

    val selectedStatue: LiveData<Stone> get() = _selectedStatue
    val selectedItem: LiveData<Item> get() = _selectedItem
    val wantedItem: LiveData<ItemX> get() = _wantedItem

    init {
        _selectedItemsList.value = mutableListOf()
    }
    val selectedItemsList: LiveData<MutableList<String>> get() = _selectedItemsList
    // 변경된 부분: 선택된 아이템 아이디를 추가하는 메소드
    fun addSelectedItemId(itemId: String) {
        _selectedItemsList.value?.add(itemId)
        _selectedItemsList.value = _selectedItemsList.value // LiveData 업데이트를 위한 임시 변경
    }

    // 변경된 부분: 선택된 아이템 아이디를 제거하는 메소드
    fun removeSelectedItemId(itemId: String) {
        _selectedItemsList.value?.remove(itemId)
        _selectedItemsList.value = _selectedItemsList.value // LiveData 업데이트를 위한 임시 변경
    }


    fun updateSelectedStatue(stone: Stone) {// 선택된 조각상 업데이트
        _selectedStatue.value = stone
        stone.isSelected = true
    }
    fun updatereleasedStatue(stone: Stone) {// 선택 안 된 조각상 업데이트
        _selectedStatue.value = stone
        stone.isSelected = false
    }

    fun updateSelectedItem_item(item:Item){//선택된 아이템(원석) 업데이트
        _selectedItem.value = item
        item.isSelected = true
        Log.d("viewModel", " ${item.itemName}-> ${item.isSelected}")
    }

    fun updatereleasedItem_item(item:Item) {// 선택된 조각상 업데이트
        _selectedItem.value = item
        item.isSelected = false
    }

    fun updateWantedItem_item(item:ItemX){//선택된 아이템-> 구매목록으로 업데이트
        _wantedItem.value = item
        Log.d("viewModel", " ${item.id}")
    }



}